package com.gk.mortgage.calculator.task;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.service.InterestRateService;

@RunWith(SpringRunner.class)
public class MortgageProcessorTaskImplTest {
	
	@Mock
	ApplicationContext applicationContext;
	
	@MockBean
	@Qualifier("fixed")
	MortgageCalculatorTask mortgageCalculatorTask;
	
	@InjectMocks
	MortgageProcessorTaskImpl mortgageProcessorTask;
	
	@Test
	public void whenInvokingMortgageProcessorShouldReturnResponseWithMonthlyPaymentValue() {
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setInterestRate(5.5);
		request.setPrincipal(100000.0);
		request.setTerm(30);
		request.setType("fixed");
		when(applicationContext.getBean("fixed")).thenReturn(mortgageCalculatorTask);
		when(mortgageCalculatorTask.calculateMonthlyPayment(100000, 5.5, 30)).thenReturn(100.0);
		
		MortgageProcessorTaskImpl mortgageProcessorTask = new MortgageProcessorTaskImpl();
		mortgageProcessorTask.setApplicationContext(applicationContext);
		MortgageCalculatorResponse response = mortgageProcessorTask.process(request);
		assertNotNull(response.getMonthlyPayment());
		assertEquals(new Double(100.0), new Double(response.getMonthlyPayment()));
	}
	
//	@Test
//	public void testAppContext() {
//	   assertNotNull(applicationContext);
//	}
}
