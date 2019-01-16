package com.gk.mortgage.calculator.task;

public class InterestOnlyMortgageCalculatorTaskImpl implements MortgageCalculatorTask {

	@Override
	public double calculateMonthlyPayment(double principal, double yearlyRate, int term) {
		System.out.println("In calculate method of InterestOnly");
		
		double monthlyPayment = principal * (yearlyRate/100) / 12;
		
		// round to two decimals
		monthlyPayment = (double) Math.round(monthlyPayment * 100) / 100;
		
		return monthlyPayment;
	}

}
