package com.gk.mortgage.calculator.task;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.domain.interest.rate.InterestRatesResponse;
import com.gk.mortgage.calculator.service.InterestRateService;

@Service
public class MortgageProcessorTaskImpl implements MortgageProcessorTask, ApplicationContextAware {
	
    private static ApplicationContext applicationContext;
	
	private MortgageCalculatorTask mortgageCalculatorTask;
	
	@Autowired
	private InterestRateService interestRateService;

	@Override
	public MortgageCalculatorResponse process(MortgageCalculatorRequest request) {
		
		MortgageCalculatorRequest modifiedRequest = request;
		
		// If the interest rate json element in request is null, we want to call the service to get the market rates
		if (request.getInterestRate() == null ) {
			InterestRatesResponse intRatesResponse = interestRateService.getRates();
			
			// Create the modified request object as the original request object is immutable.
			// The modified request has the interest rate in it.
			modifiedRequest = MortgageCalculatorRequest.builder().
					name(request.getName()).
					propertyAddress(request.getPropertyAddress()).
					creditCard(request.getCreditCard()).
					creditCardExpiry(request.getCreditCardExpiry()).
					socialSecurityNumber(request.getSocialSecurityNumber()).
					type(request.getType()).
					principal(request.getPrincipal()).
					interestRate(request.getInterestRate()).
					term(request.getTerm()).
					interestRate(intRatesResponse.getInterestRates().get(0).getRate()).
					build();					
		}
		
		// get the bean to invoke the mortgage calculator to calculate the monthly payment
		mortgageCalculatorTask = applicationContext.getBean(modifiedRequest.getType(), MortgageCalculatorTask.class);
		
		// invoke the bean to calculate the monthly payment
		double monthlyPayment = mortgageCalculatorTask.calculateMonthlyPayment(modifiedRequest.getPrincipal().doubleValue(), 
																				modifiedRequest.getInterestRate().doubleValue(), 
																				modifiedRequest.getTerm().intValue());		
		// return the response object
		return buildResponseObject(modifiedRequest, monthlyPayment);
	}
	
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

}
