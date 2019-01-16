package com.gk.mortgage.calculator.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.task.MortgageTaskImpl;

@RunWith(MockitoJUnitRunner.class)
public class MortgageCalculatorControllerOldTest {
	
	@Mock
	MortgageTaskImpl mortgageTask;
	
	@InjectMocks
	MortgageCalculatorControllerOld mortgageCalculatorController;
	
	@Test
	public void calculateMonthlyPaymentSuccess() {
		// setup mock mortgage task
		MortgageCalculatorResponse mockResponse = new MortgageCalculatorResponse();
		mockResponse.setInterestRate(5.0);
		mockResponse.setPrincipal(100000.0);
		mockResponse.setTerm(30);
		mockResponse.setMonthlyPayment(536.82);
		when(mortgageTask.calculate(any())).thenReturn(mockResponse);
		
		// given mortgage request
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		// set any values for the request fields
		request.setInterestRate(5.0);
		request.setPrincipal(100000.0);
		request.setTerm(30);
		
		// when controller is invoked
		MortgageCalculatorResponse response =  mortgageCalculatorController.calculateMonthlyPayment(request);
		
		// then response should be a MorgageCalculatorResponse object
		assertThat(response, instanceOf(MortgageCalculatorResponse.class) );
		
	}

}
