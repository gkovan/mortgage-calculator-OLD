package com.gk.mortgage.calculator.task;

import java.util.ArrayList;
import java.util.List;

import com.gk.mortgage.calculator.domain.MortgageAmortizationSchedule;
import com.gk.mortgage.calculator.domain.MortgagePayment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixedRateMortgageCalculatorTaskImpl implements MortgageCalculatorTask, MortgageAmortizationScheduleTask {

	@Override
	public double calculateMonthlyPayment(double principal, double yearlyRate, int term) {
		log.info("In calculate method of FixedRate");
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
	
	public MortgageAmortizationSchedule mortgageAmortizationSchedule (double principal, double yearlyRate, int term) {
        double interestPaid, principalPaid, newBalance;
        double monthlyInterestRate, monthlyPayment;
        int month;
        int numMonths = term * 12;
        
        monthlyInterestRate = yearlyRate / 12;
        monthlyPayment      = monthlyPayment(principal, monthlyInterestRate, term);

        List<MortgagePayment> listOfPayments = new ArrayList<MortgagePayment>();
        
        
        for (month = 1; month <= numMonths; month++) {
            // Compute amount paid and new balance for each payment period
            interestPaid  = principal      * (monthlyInterestRate / 100);
            principalPaid = monthlyPayment - interestPaid;
            newBalance    = principal      - principalPaid;

            // Output the data item
            MortgagePayment mortPayment = new MortgagePayment();
            mortPayment.setPaymentNumber(month);
            mortPayment.setInterestPaid(interestPaid);
            mortPayment.setPrincipalPaid(principalPaid);
            mortPayment.setBalance(newBalance);
            
            listOfPayments.add(mortPayment);

            // Update the balance
            principal = newBalance;
        }
        MortgageAmortizationSchedule mortAmortSched = new MortgageAmortizationSchedule();
        mortAmortSched.setAmortizationSchedule(listOfPayments);
        return mortAmortSched;
	}
	
    /**
     * @param loanAmount
     * @param monthlyInterestRate in percent
     * @param numberOfYears
     * @return the amount of the monthly payment of the loan
     */
    private double monthlyPayment(double loanAmount, double monthlyInterestRate, int numberOfYears) {
        monthlyInterestRate /= 100;  // e.g. 5% => 0.05
        return loanAmount * monthlyInterestRate /
                ( 1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12) );
    }



}
