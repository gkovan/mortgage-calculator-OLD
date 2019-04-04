package com.gk.mortgage.calculator.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MortgageCalculatorRequest {
	
	final Double principal;
	final Double interestRate;
	final Integer term;
	final String type;
	final String amortization;
}
