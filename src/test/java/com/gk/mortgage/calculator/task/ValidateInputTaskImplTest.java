package com.gk.mortgage.calculator.task;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.exceptions.BadRequestInputException;

public class ValidateInputTaskImplTest {

	@Test
	public void testValidInputRequest() {
		//given
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setInterestRate(5.5);
		request.setPrincipal(100000.0);
		request.setTerm(30);
		request.setType("Fixed");
		
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
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setInterestRate(5.5);
		request.setTerm(30);
		request.setType("Fixed");
		
		//when
		ValidateInputTask validateInputTask = new ValidateInputTaskImpl();
		validateInputTask.validate(request);
		
		// test past if no exception is thrown in validate
		assertTrue(true);
	}
	
	@Test(expected = BadRequestInputException.class)
	public void testRequestNoTermShouldThrowException() {
		//given
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setInterestRate(5.5);
		request.setPrincipal(100000.0);
		request.setType("Fixed");
		
		//when
		ValidateInputTask validateInputTask = new ValidateInputTaskImpl();
		validateInputTask.validate(request);
		
		// test past if no exception is thrown in validate
		assertTrue(true);
	}

}
