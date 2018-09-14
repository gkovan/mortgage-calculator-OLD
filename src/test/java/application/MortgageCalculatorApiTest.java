package application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.gk.mortgage.calculator.utils.TestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SBApplication.class)
@WebAppConfiguration
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
    public void invokeMortgageCalculatorPostRequestShouldBeSuccess() throws Exception {

        String requestBody = TestUtils.loadSourceFile("__files/mortgage-calculator-request.json");
        
        String url = "/calculate";

        mockMvc.perform(post(url).headers(httpHeaders).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))                
                .andExpect(jsonPath("$.principal", Matchers.equalTo(100000.0)))
                .andExpect(jsonPath("$.interestRate", Matchers.equalTo(5.0)))
        		.andExpect(jsonPath("$.term", Matchers.equalTo(30)))
        		.andExpect(jsonPath("$.monthlyPayment", Matchers.equalTo(536.82)));
    }
    
    @Test
    public void invokeMortgageCalculatorGetRequestShouldBeSuccess() throws Exception {
        
        String url = "/calculate?principal=100000&interestrate=5.0&term=30";

        mockMvc.perform(get(url))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))                
                .andExpect(jsonPath("$.principal", Matchers.equalTo(100000.0)))
                .andExpect(jsonPath("$.interestRate", Matchers.equalTo(5.0)))
        		.andExpect(jsonPath("$.term", Matchers.equalTo(30)))
        		.andExpect(jsonPath("$.monthlyPayment", Matchers.equalTo(536.82)));
    }
}
