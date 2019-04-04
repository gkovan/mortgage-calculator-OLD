package com.gk.mortgage.calculator.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.service.InterestRateServiceImpl;
import com.gk.mortgage.calculator.domain.interest.rate.InterestRatesResponse;
import com.gk.mortgage.calculator.domain.interest.rate.InterestRate;

import application.SBApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SBApplication.class)

public class MortgageProcessorTaskImplTest {
		
	@MockBean
	InterestRateServiceImpl interestRateService;
	
	@MockBean
	@Qualifier("fixed")
	MortgageCalculatorTask mortgageCalculatorTask;
	
	@Autowired
	MortgageProcessorTaskImpl mortgageProcessorTask;
	
	@Test
	public void whenInvokingMortgageProcessorShouldReturnResponseWithMonthlyPaymentValue() {
		
		// given a valid request
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
											type("fixed").interestRate(5.5).principal(100000.0).term(30).build();

		// mock the response of the dependency
		when(mortgageCalculatorTask.calculateMonthlyPayment(100000, 5.5, 30)).thenReturn(100.0);
		
		// when we invoke the process method
		MortgageCalculatorResponse response = mortgageProcessorTask.process(request);
		
		// then we expect a valid monthly payment amount in the response object
		assertNotNull(response.getMonthlyPayment());
		assertEquals(new Double(100.0), new Double(response.getMonthlyPayment()));
	}
	
	@Test
	public void whenInvokingMortgageProcessor_WithoutInterestRateInRequest_ShouldReturnResponseWithMonthlyPaymentValue() {
		
		// given a valid request
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
											type("fixed").
											interestRate(5.5).
											principal(100000.0).
											term(30).build();

		
		// mock response from int rate service
		InterestRatesResponse intRatesResponse = new InterestRatesResponse();
		List<InterestRate> rateList = new ArrayList<InterestRate>();
		InterestRate rate30Year = new InterestRate();
		rate30Year.setRate(5.0);
		rate30Year.setType("30");
		rateList.add(rate30Year);
		InterestRate rate15Year = new InterestRate();
		rate15Year.setRate(3.5);
		rate15Year.setType("15");
		rateList.add(rate15Year);
		intRatesResponse.setInterestRates(rateList);

		// mock the response of the dependencies
		when(mortgageCalculatorTask.calculateMonthlyPayment(100000, 5.5, 30)).thenReturn(100.0);
		when(interestRateService.getRates()).thenReturn(intRatesResponse);
		
		// when we invoke the process method
		MortgageCalculatorResponse response = mortgageProcessorTask.process(request);
		
		// then we expect a valid monthly payment amount in the response object
		assertNotNull(response.getMonthlyPayment());
		assertEquals(new Double(100.0), new Double(response.getMonthlyPayment()));
	}
}
