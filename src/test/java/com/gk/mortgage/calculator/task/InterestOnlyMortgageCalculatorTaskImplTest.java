package com.gk.mortgage.calculator.task;

import static org.junit.Assert.*;

import org.junit.Test;

public class InterestOnlyMortgageCalculatorTaskImplTest {

	@Test
	public void testCalculate30YearMonthlyPayment() {
		// given
		double principal = 100000.0;
		double intRate = 6.0;
		int term = 30;
		
		// when we invoke calculate method
		InterestOnlyMortgageCalculatorTaskImpl calculator = new InterestOnlyMortgageCalculatorTaskImpl();
		double monthlyPayment = calculator.calculateMonthlyPayment(principal, intRate, term);
		
		// then
		assertEquals(new Double(500.0), new Double(monthlyPayment));
	}
	
	@Test
	public void testCalculate15YearMonthlyPayment() {
		// given
		double principal = 100000.0;
		double intRate = 6.0;
		int term = 15;
		
		// when we invoke calculate method
		InterestOnlyMortgageCalculatorTaskImpl calculator = new InterestOnlyMortgageCalculatorTaskImpl();
		double monthlyPayment = calculator.calculateMonthlyPayment(principal, intRate, term);
		
		// then
		assertEquals(new Double(500.0), new Double(monthlyPayment));
	}

}
