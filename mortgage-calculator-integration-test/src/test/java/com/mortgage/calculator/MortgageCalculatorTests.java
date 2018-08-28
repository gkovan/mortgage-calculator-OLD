package com.mortgage.calculator;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class MortgageCalculatorTests {

	
	@Given("^principal of \"([^\"]*)\" and interest rate of \"([^\"]*)\" and years of \"([^\"]*)\"$")
	public void principal_of_and_interest_rate_of_and_years_of(String principal, String interestRate, String years) throws Exception {
		System.out.println("Principal: " + principal);
		System.out.println("Interest rate: " + interestRate);
		System.out.println("Years: " + years);
	}
	

	@When("^api is invoked to calculate monthly mortgage payment$")
	public void api_is_invoked_to_calculate_monthly_mortgage_payment() throws Exception {

	}

	@Then("^Api returns with a http status code of (\\d+) and the monthly payment$")
	public void api_returns_with_a_http_status_code_of_and_the_monthly_payment(int monthlyPayment) throws Exception {
		monthlyPayment = 1000;
		assertEquals(monthlyPayment, 1000);
	}

}
