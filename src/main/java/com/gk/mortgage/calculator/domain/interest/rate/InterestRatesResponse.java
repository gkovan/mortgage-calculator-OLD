package com.gk.mortgage.calculator.domain.interest.rate;

import java.util.List;

import lombok.Data;

@Data
public class InterestRatesResponse {
   List<InterestRate> interestRates;
}
