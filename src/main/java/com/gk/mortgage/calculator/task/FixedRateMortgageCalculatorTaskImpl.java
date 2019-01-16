package com.gk.mortgage.calculator.task;

public class FixedRateMortgageCalculatorTaskImpl implements MortgageCalculatorTask {

	@Override
	public double calculateMonthlyPayment(double principal, double yearlyRate, int term) {
		System.out.println("In calculate method of FixedRate");
		// Monthly intertest rate
		double monthlyRate = yearlyRate / 100 / 12;

		// Term in months
		int termInMonths = term * 12;

		double payment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
		
		// round to two decimals
		payment = (double) Math.round(payment * 100) / 100;
		return payment;
	}

}
