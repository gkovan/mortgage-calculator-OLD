package com.gk.mortgage.calculator.task;

public interface MortgageCalculatorTask {
	
	public double calculateMonthlyPayment (double principal, double yearlyRate, int term);

}
