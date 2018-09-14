package com.gk.mortgage.calculator.task;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.gk.mortgage.calculator.task.MortgageTaskImpl;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;

@RunWith(JUnit4.class)
public class MortgageTaskTest {
	
	@Test
	public void testTaskCalculatesCorrectMonthlyMortgagePayment() {
		
		// given request object with principal $100,00, int rate of 5.0% and term is 30 years
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setPrincipal(100000.0);
		request.setInterestRate(5.0);
		request.setTerm(30);
		
		// when task is invoked
		MortgageTask mortgageTask = new MortgageTaskImpl();
		MortgageCalculatorResponse response = mortgageTask.calculate(request);
		
		// then 
		assertEquals(536.82, response.getMonthlyPayment(), 0.001);
		
	}

}
