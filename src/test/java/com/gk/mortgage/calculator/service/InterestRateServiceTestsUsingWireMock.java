package com.gk.mortgage.calculator.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.gk.mortgage.calculator.domain.interest.rate.InterestRatesResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import application.SBApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SBApplication.class)
//@ActiveProfiles("tests")
public class InterestRateServiceTestsUsingWireMock {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();
    
    @Autowired
    private InterestRateService interestRateService;

	@Before
	public void setUpTogglesWireMock() throws Exception {
		WireMock.configureFor("localhost", wireMockRule.port());
    }
    
	@Test
	public void testInterestRateServiceShouldReturnSuccess() throws Exception {		
		stubFor(get(urlPathMatching("/interest-rates"))
		        .willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json")
				.withBodyFile("interest-rates.json")));

		System.out.println("GK");
		InterestRatesResponse response = interestRateService.getRates();
		assertNotNull(null);
	}
	
	@Test
	public void test2() {
		assertTrue(false);
	}
    
}
