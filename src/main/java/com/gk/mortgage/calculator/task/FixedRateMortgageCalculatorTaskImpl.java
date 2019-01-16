package com.gk.mortgage.calculator.task;

import java.util.ArrayList;
import java.util.List;

import com.gk.mortgage.calculator.domain.AmortizationSchedule;

public class FixedRateMortgageCalculatorTaskImpl implements MortgageCalculatorTask {

	@Override
	public double calculateMonthlyPayment(double principal, double yearlyRate, int term) {
		System.out.println("In calculate method of FixedRate");
		// Monthly intertest rate
		double monthlyRate = yearlyRate / 100 / 12;

		// Term in months
		int termInMonths = term * 12;

		// calculate monthly payment
		double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
		
		// round to two decimals
		monthlyPayment = (double) Math.round(monthlyPayment * 100) / 100;
		return monthlyPayment;
	}
	
	@Override
	public  List<AmortizationSchedule> generateAmortizationSchedule(double principal, double annualInterestRate,int numYears) {
        double interestPaid, principalPaid, newBalance;
        double monthlyInterestRate, monthlyPayment;
        int month;
        int numMonths = numYears * 12;
        
        List<AmortizationSchedule> amortScheduleList = new ArrayList<AmortizationSchedule>();

        // Output monthly payment and total payment
        monthlyInterestRate = annualInterestRate / 12;
        monthlyPayment      = calculateMonthlyPayment(principal, annualInterestRate, numYears);
//        System.out.format("Monthly Payment: %8.2f%n", monthlyPayment);
//        System.out.format("Total Payment:   %8.2f%n", monthlyPayment * numYears * 12);



        for (month = 1; month <= numMonths; month++) {
            // Compute amount paid and new balance for each payment period
            interestPaid  = principal      * (monthlyInterestRate / 100);
            principalPaid = monthlyPayment - interestPaid;
            newBalance    = principal      - principalPaid;

            AmortizationSchedule amortSched = new AmortizationSchedule();
            amortSched.setPayment(month);
            amortSched.setInterestPaid(interestPaid);
            amortSched.setPrincipalPaid(principalPaid);
            amortSched.setBalance(newBalance);

            // Output the data it
            amortScheduleList.add(amortSched);
  //          printScheduleItem(month, interestPaid, principalPaid, newBalance);

            // Update the balance
            principal = newBalance;
        }
		return amortScheduleList;
	}


}
