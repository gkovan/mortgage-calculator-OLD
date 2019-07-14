package com.gk.mortgage.calculator.domain;

import lombok.Data;

@Data
public class MortgagePayment {
	Integer paymentNumber;
	Double interestPaid;
	Double principalPaid;
	Double balance;
}
