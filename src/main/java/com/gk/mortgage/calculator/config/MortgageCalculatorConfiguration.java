package com.gk.mortgage.calculator.config;

//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.gk.mortgage.calculator.task.FixedRateMortgageCalculatorTaskImpl;
import com.gk.mortgage.calculator.task.InterestOnlyMortgageCalculatorTaskImpl;
import com.gk.mortgage.calculator.task.MortgageCalculatorTask;

@Configuration
public class MortgageCalculatorConfiguration {
	
	
	@Bean(name = "fixed")
	public MortgageCalculatorTask fixedRateMortgageCalculator() {		
		return new FixedRateMortgageCalculatorTaskImpl();
	}
	
	@Bean("interest")
	public MortgageCalculatorTask getMortgageCalculator() {		
		return new InterestOnlyMortgageCalculatorTaskImpl();
	}
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
