package application;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.gk.mortgage.calculator.utils.TestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SBApplication.class)
@WebAppConfiguration
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MortgageCalculatorApiTest {
    private MockMvc mockMvc;
    private HttpHeaders httpHeaders;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setup() throws Exception {

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void invokeMortgageCalculatorInterestOnlyPostRequestShouldBeSuccessHTTP200() throws Exception {

        String requestBody = TestUtils.loadSourceFile("__files/mortgage-calculator-interest-only-request.json");
        
        String url = "/calculate";

        mockMvc.perform(post(url).headers(httpHeaders).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))                
                .andExpect(jsonPath("$.principal", Matchers.equalTo(100000.0)))
                .andExpect(jsonPath("$.interestRate", Matchers.equalTo(5.0)))
        		.andExpect(jsonPath("$.term", Matchers.equalTo(30)))
        		.andExpect(jsonPath("$.type", Matchers.equalTo("interest")))
        		.andExpect(jsonPath("$.monthlyPayment", Matchers.equalTo(416.67)));
    }
    
    @Test
    public void invokeMortgageCalculatorFixedRatePostRequestShouldBeSuccessHTTP200() throws Exception {

        String requestBody = TestUtils.loadSourceFile("__files/mortgage-calculator-fixed-rate-request.json");
        
        String url = "/calculate";

        mockMvc.perform(post(url).headers(httpHeaders).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))                
                .andExpect(jsonPath("$.principal", Matchers.equalTo(100000.0)))
                .andExpect(jsonPath("$.interestRate", Matchers.equalTo(5.0)))
        		.andExpect(jsonPath("$.term", Matchers.equalTo(30)))
        		.andExpect(jsonPath("$.type", Matchers.equalTo("fixed")))
        		.andExpect(jsonPath("$.monthlyPayment", Matchers.equalTo(536.82)));
    }
    
    @Test
    public void invokeMortgageCalculatorWithBadInputNoPrincipalShouldFailWithHTTP400() throws Exception {

        String requestBody = TestUtils.loadSourceFile("__files/mortgage-calculator-bad-input-no-principal-request.json");
        
        String url = "/calculate";

        mockMvc.perform(post(url).headers(httpHeaders).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))                
                .andExpect(jsonPath("$.code", Matchers.equalTo("APPERR0002")))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Bad Input Request.")));
    }
    
    
    @Test
    @Ignore
    public void invokeMortgageCalculatorNoInterestInvokeInterestServiceShouldFailWithHTTP500() throws Exception {

        String requestBody = TestUtils.loadSourceFile("__files/mortgage-calculator-fixed-rate-no-interest-invoke-interest-service-request.json");
        
        String url = "/calculate";

        mockMvc.perform(post(url).headers(httpHeaders).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))                
                .andExpect(jsonPath("$.code", Matchers.equalTo("APPERR0001")))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Internal Server Error.")));
    }    
}
