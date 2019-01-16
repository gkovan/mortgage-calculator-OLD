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
@Ignore
public class MortgageCalculatorApiServerTest {

    @Autowired
    private TestRestTemplate server;

    @LocalServerPort
    private int port;
		
	@Test
	public void mortgageCalculatorShouldReturn200() {
		// given a server with endpoint /calculate

		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setInterestRate(5.5);
		request.setPrincipal(100000.0);
		request.setTerm(30);
		request.setType("fixed");
		
		
		// when we invoke /calculate endpoint
		String endpoint = "http://localhost:" + port + "/calculate";
		ResponseEntity<MortgageCalculatorResponse> re = server.postForEntity(endpoint, request, MortgageCalculatorResponse.class);
        System.out.println(re.getBody());
        
		// then
        assertEquals(HttpStatus.OK, re.getStatusCode());
	}
}
