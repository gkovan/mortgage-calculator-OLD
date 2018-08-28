package com.gk.mortgage.calculator.domain;

import lombok.Data;

@Data
public class MortgageCalculatorRequest {
	
	Double principal;
	Double interestRate;
	Integer term;
}
