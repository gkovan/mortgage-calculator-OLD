package com.gk.mortgage.calculator.task;

import java.util.List;

import com.gk.mortgage.calculator.domain.AmortizationSchedule;

public interface MortgageAmortizationScheduleTask {
    public  List<AmortizationSchedule> generateAmortizationSchedule(double principal, double annualInterestRate,int numYears) ;
}
