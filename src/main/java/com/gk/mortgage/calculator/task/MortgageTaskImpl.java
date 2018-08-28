package com.gk.mortgage.calculator.task;

import org.springframework.stereotype.Service;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;

@Service
public class MortgageTaskImpl implements MortgageTask {

	public MortgageCalculatorResponse calculate(MortgageCalculatorRequest request) {
		double principal = request.getPrincipal().doubleValue();
		double yearlyRate = request.getInterestRate().doubleValue();
		int term = request.getTerm().intValue();

		// Monthly intertest rate
		double monthlyRate = yearlyRate / 100 / 12;

		// Term in months
		int termInMonths = term * 12;

		double payment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
		
		// round to two decimals
		payment = (double) Math.round(payment * 100) / 100;

		MortgageCalculatorResponse response = new MortgageCalculatorResponse();
		response.setPrincipal(principal);
		response.setInterestRate(yearlyRate);
		response.setTerm(term);
		response.setMonthlyPayment(payment);
		return response;
		// return new MortgageCalculatorResponse(new Double(principal), new
		// Double(rate), new Integer(term), new Double(payment));
	}
}
