package com.gk.mortgage.calculator.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.exceptions.BadRequestInputException;
import com.gk.mortgage.calculator.task.MortgageProcessorTask;
import com.gk.mortgage.calculator.task.ValidateInputTask;


@RunWith(MockitoJUnitRunner.class)
public class MortgageCalculatorControllerTest {
	
	@Mock
	MortgageProcessorTask mortgageProcessorTask;
	
	@Mock
	ValidateInputTask validateInputTask;
	
	@InjectMocks
	MortgageCalculatorController mortgageCalculatorController;

	@Test
	public void controllerShouldReturnProperResponseObject() {
		// setup mock mortgage task
		MortgageCalculatorResponse mockResponse = new MortgageCalculatorResponse();
		mockResponse.setInterestRate(5.0);
		mockResponse.setPrincipal(100000.0);
		mockResponse.setTerm(30);
		mockResponse.setMonthlyPayment(536.82);
		doNothing().when(validateInputTask).validate(any());
		when(mortgageProcessorTask.process(any())).thenReturn(mockResponse);
		
		// given mortgage request
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
											interestRate(5.0).
											principal(100000.0).
											term(30).
											build();
		
		// when controller is invoked
		MortgageCalculatorResponse response =  mortgageCalculatorController.calculateMonthlyPayment(request);
		
		// then response should be a MorgageCalculatorResponse object
		assertThat(response, instanceOf(MortgageCalculatorResponse.class) );		
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = BadRequestInputException.class)
	public void controllerShouldThrowBadRequestInputException() {

		doNothing().when(validateInputTask).validate(any());
		when(mortgageProcessorTask.process(any())).thenThrow(BadRequestInputException.class);
		
		// given mortgage request
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				interestRate(5.0).
				term(30).
				build();
		// set any values for the request fields
		
		// when controller is invoked
		MortgageCalculatorResponse response =  mortgageCalculatorController.calculateMonthlyPayment(request);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = RestClientException.class)
	public void controllerShouldThrowRestClientException() {

		doNothing().when(validateInputTask).validate(any());
		when(mortgageProcessorTask.process(any())).thenThrow(RestClientException.class);
		
		// given mortgage request
		// set any values for the request fields
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				interestRate(5.0).
				principal(100000.0).
				term(30).
				build();

		
		// when controller is invoked
		MortgageCalculatorResponse response =  mortgageCalculatorController.calculateMonthlyPayment(request);
	}
}
