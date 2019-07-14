package com.gk.mortgage.calculator.task;

import com.gk.mortgage.calculator.domain.MortgageAmortizationSchedule;

public interface MortgageAmortizationScheduleTask {
	
	public MortgageAmortizationSchedule mortgageAmortizationSchedule (double principal, double yearlyRate, int term);
}
