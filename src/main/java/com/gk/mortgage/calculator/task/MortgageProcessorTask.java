package com.gk.mortgage.calculator.task;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;

public interface MortgageProcessorTask {
	
	public MortgageCalculatorResponse process(MortgageCalculatorRequest request);
	
	default MortgageCalculatorResponse buildResponseObject(MortgageCalculatorRequest request, double monthlyPayment) {
		MortgageCalculatorResponse response = new MortgageCalculatorResponse();
		response.setMonthlyPayment(monthlyPayment);
		response.setInterestRate(request.getInterestRate());
		response.setPrincipal(request.getPrincipal());
		response.setTerm(request.getTerm());
		response.setType(request.getType());
		return response;
	}

}
