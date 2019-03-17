package com.gk.mortgage.calculator.domain;

import java.util.List;

import lombok.Data;

@Data
public class MortgageAmortizationSchedule {
	
	List<MortgagePayment> amortizationSchedule;

}
