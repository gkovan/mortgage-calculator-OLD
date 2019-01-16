package com.gk.mortgage.calculator.task;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.service.InterestRateService;
import com.gk.mortgage.calculator.service.InterestRateServiceImpl;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Ignore
public class MortgageProcessorTaskImplTest {
	
//	@Mock
//	@Autowired
//	ApplicationContext applicationContext;
	
	@MockBean
	InterestRateServiceImpl interestRateService;
	
	@MockBean
	@Qualifier("fixed")
	MortgageCalculatorTask mortgageCalculatorTask;
	
//	@Autowired
//	@Qualifier("for-test")
	MortgageProcessorTaskImpl mortgageProcessorTask = new MortgageProcessorTaskImpl();
	
	@Test
	public void whenInvokingMortgageProcessorShouldReturnResponseWithMonthlyPaymentValue() {
		
		// given a valid request
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setInterestRate(5.5);
		request.setPrincipal(100000.0);
		request.setTerm(30);
		request.setType("fixed-test");

		// mock the response of the dependency
		when(mortgageCalculatorTask.calculateMonthlyPayment(100000, 5.5, 30)).thenReturn(100.0);
		
		// when we invoke the process method
		MortgageCalculatorResponse response = mortgageProcessorTask.process(request);
		
		// then we expect a valid monthly payment amount in the response object
		assertNotNull(response.getMonthlyPayment());
		assertEquals(new Double(100.0), new Double(response.getMonthlyPayment()));
	}
	
	
//    @Configuration
//    @Import(MortgageProcessorTaskImpl.class) // A @Component injected with ExampleService
//    private static class Config {
//    	@Bean(name = "fixed-test")
//    	public MortgageCalculatorTask fixedRateMortgageCalculator() {		
//    		return new FixedRateMortgageCalculatorTaskImpl();
//    	}
//    	
//    	@Bean("interest-test")
//    	public MortgageCalculatorTask getMortgageCalculator() {		
//    		return new InterestOnlyMortgageCalculatorTaskImpl();
//    	}
//    	
//    	@Bean("for-test")
//    	public MortgageProcessorTask getMortgageProcessorTask() {
//    		return new MortgageProcessorTaskImpl();
//    	}
//    }
}
