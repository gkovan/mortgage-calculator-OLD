package com.gk.mortgage.calculator.domain;

import lombok.Data;

@Data
public class AmortizationSchedule {
	int payment;
	double interestPaid;
	double principalPaid;
	double balance;
}
