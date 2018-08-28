package com.gk.mortgage.calculator.task;

import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;

public interface MortgageTask {
	public MortgageCalculatorResponse calculate(MortgageCalculatorRequest request);
}
