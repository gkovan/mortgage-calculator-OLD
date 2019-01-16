package com.gk.mortgage.calculator.task;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;

public interface ValidateInputTask {
	
	public void validate(MortgageCalculatorRequest request);

}
