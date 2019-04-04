package com.gk.mortgage.calculator.task;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.exceptions.BadRequestInputException;

public class ValidateInputTaskImplTest {

	@Test
	public void testValidInputRequest() {
		//given
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				type("Fixed").
				interestRate(5.5).
				principal(100000.0).
				term(30).
				build();
		
		//when
		ValidateInputTask validateInputTask = new ValidateInputTaskImpl();
		validateInputTask.validate(request);
		
		// test past if no exception is thrown in validate
		assertTrue(true);
	}
	
	@Test(expected = BadRequestInputException.class)
	public void testRequestIsNullShouldThrowException() {
		//given
		MortgageCalculatorRequest request = null;
		
		//when
		ValidateInputTask validateInputTask = new ValidateInputTaskImpl();
		validateInputTask.validate(request);
		
		// test past if no exception is thrown in validate
		assertTrue(true);
	}
	
	@Test(expected = BadRequestInputException.class)
	public void testRequestNoPrincipalShouldThrowException() {
		//given
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				type("Fixed").
				interestRate(5.5).
				term(30).
				build();
		
		//when
		ValidateInputTask validateInputTask = new ValidateInputTaskImpl();
		validateInputTask.validate(request);
		
		// test past if no exception is thrown in validate
		assertTrue(true);
	}
	
	@Test(expected = BadRequestInputException.class)
	public void testRequestNoTermShouldThrowException() {
		//given
		MortgageCalculatorRequest request = MortgageCalculatorRequest.builder().
				type("fixed").
				interestRate(5.5).
				principal(100000.0).
				build();
		
		//when
		ValidateInputTask validateInputTask = new ValidateInputTaskImpl();
		validateInputTask.validate(request);
		
		// test past if no exception is thrown in validate
		assertTrue(true);
	}

}
