package com.gk.mortgage.calculator.task;

import java.util.List;

import com.gk.mortgage.calculator.domain.AmortizationSchedule;

public interface MortgageCalculatorTask {
	
	public double calculateMonthlyPayment (double principal, double yearlyRate, int term);
    public  List<AmortizationSchedule> generateAmortizationSchedule(double principal, double annualInterestRate,int numYears) ;

}
