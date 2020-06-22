package com.gk.mortgage.calculator.task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterestOnlyMortgageCalculatorTaskImpl implements MortgageCalculatorTask {

	@Override
	public double calculateMonthlyPayment(double principal, double yearlyRate, int term) {
		log.info("In calculate method of InterestOnly");
		
		double monthlyPayment = principal * (yearlyRate/100) / 12;
		
		// round to two decimals
		monthlyPayment = (double) Math.round(monthlyPayment * 100) / 100;
		
		return monthlyPayment;
	}

}
