package com.gk.mortgage.calculator.domain;

import java.util.List;

import lombok.Data;

@Data
public class MortgageCalculatorResponse {
	Double principal;
	Double interestRate;
	Integer term;
	String type;
	Double monthlyPayment;
	List<AmortizationSchedule> amortizationSchedule;
}
