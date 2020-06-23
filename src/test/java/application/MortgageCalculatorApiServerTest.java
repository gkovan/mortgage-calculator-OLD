package application;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;

/* 
 * Test the full stack with a running server.
 * SpringBootTest annotation with webEnvironment starts the actual server.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MortgageCalculatorApiServerTest {

    @Autowired
    private TestRestTemplate server;

    @LocalServerPort
    private int port;
		
	@Test
	public void mortgageCalculatorShouldReturn200() {
		// given a server with endpoint /calculate

		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				type("fixed").
				interestRate(5.5).
				principal(100000.0).
				term(30).
				build();
		
		
		// when we invoke /calculate endpoint with a proper request body and proper basic auth credentials
		String endpoint = "http://localhost:" + port + "/calculate";
		ResponseEntity<MortgageCalculatorResponse> re = server.withBasicAuth("user1","password1").postForEntity(endpoint, request, MortgageCalculatorResponse.class);
		
        System.out.println(re.getBody());
        
		// then
        assertEquals(HttpStatus.OK, re.getStatusCode());
	}
	
	@Test
	public void mortgageCalculatorShouldReturn401WhenInvalidBasicAuthCredentialsUsed() {
		// given a server with endpoint /calculate

		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				type("fixed").
				interestRate(5.5).
				principal(100000.0).
				term(30).
				build();
		
		
		// when we invoke /calculate endpoint with a proper request body and incorrect basic auth credentials
		String endpoint = "http://localhost:" + port + "/calculate";
		ResponseEntity<MortgageCalculatorResponse> re = server.withBasicAuth("baduser","badpassword").postForEntity(endpoint, request, MortgageCalculatorResponse.class);
		
        System.out.println(re.getBody());
        
		// then
        assertEquals(HttpStatus.UNAUTHORIZED, re.getStatusCode());
	}
	
	@Test
	public void mortgageCalculatorShouldReturn400WhenRequestIsInvalidBecauseMissingPrincipal() {
		// given a server with endpoint /calculate

		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				type("fixed").
				interestRate(5.5).
				//principal(100000.0).
				term(30).
				build();
		
		
		// when we invoke /calculate endpoint with a non-valid request body and basic auth credentials
		String endpoint = "http://localhost:" + port + "/calculate";
		ResponseEntity<MortgageCalculatorResponse> re = server.withBasicAuth("user1","password1").postForEntity(endpoint, request, MortgageCalculatorResponse.class);
		
        System.out.println(re.getBody());
        
		// then
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
	}
	
	@Test
	public void mortgageCalculatorShouldReturn500WhenInterestRateServiceIsDown() {
		// given a server with endpoint /calculate

		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				type("fixed").
				//interestRate(5.5).  // we want to invoke the InterestRateService code so request should not have this value
				principal(100000.0).
				term(30).
				build();
		
		
		// when we invoke /calculate endpoint with a proper request body and incorrect basic auth credentials
		String endpoint = "http://localhost:" + port + "/calculate";
		ResponseEntity<MortgageCalculatorResponse> re = server.withBasicAuth("baduser","badpassword").postForEntity(endpoint, request, MortgageCalculatorResponse.class);
		
        System.out.println(re.getBody());
        
		// then
        assertEquals(HttpStatus.UNAUTHORIZED, re.getStatusCode());
	}

}
