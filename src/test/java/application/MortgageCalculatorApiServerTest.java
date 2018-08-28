package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void testMortgageCalculatorEndpointShouldReturnSuccess200() {
		// given a server with endpoint /mortgage 
		
		// when we invoke /mortgage endpoint
        String endpoint = "http://localhost:" + port + "/hellomortgage";
        ResponseEntity<String> re = server.getForEntity(endpoint, String.class);

        // then
        assertEquals(200, re.getStatusCode().value());
        assertEquals("mortgage", re.getBody());
	}
	
	@Test
	public void mortgageCalculatorShouldReturn200() {
		// given a server with endpoint /calculate
		String principal = "10000";
		String interestRate = "5.0";
		String term = "30";
		
		// when we invoke /calculate endpoint
		String endpoint = "http://localhost:" + port + "/calculate"+"?principal=" + principal + "&interestrate=" + interestRate + "&term=" + term;
		ResponseEntity<String> re = server.getForEntity(endpoint, String.class);
        System.out.println(re.getBody());
        
		// then
        assertEquals(HttpStatus.OK, re.getStatusCode());
	}
}
