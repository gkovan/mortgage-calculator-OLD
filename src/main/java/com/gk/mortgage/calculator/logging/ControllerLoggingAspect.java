package com.gk.mortgage.calculator.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerLoggingAspect {
		  
	@Pointcut("execution(* com.gk.mortgage.calculator.controller.*.*(..))")
	public void loggerAnnotation() {
		
	}
	
	@Before("loggerAnnotation()")
	public void beforeLogging(JoinPoint joinPoint) {
		 
		Logger logger = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
		if(logger.isDebugEnabled()) {
			String logBuilder = LoggingUtil.LOGGER_BEFORE_PREFIX+ "Controller Start- " + getName(joinPoint) + LoggingUtil.logBeforeResponse(joinPoint,true);
			logger.debug(logBuilder);
		} 
	}
	
	@AfterReturning(value = "execution(* com.gk.mortgage.calculator.controller.*.*(..))", returning = "retVal")
	public void afterReturningLogging(JoinPoint joinPoint, Object retVal) throws Throwable {
		
		Logger logger = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
		
		if(logger.isDebugEnabled()) {
			String logBuilder = LoggingUtil.LOGGER_AFTER_PREFIX + "Controller End- " + getName(joinPoint) + LoggingUtil.logAfterResponse(retVal, true);
			logger.debug(logBuilder);
		} 
	}

	@AfterThrowing(pointcut = "loggerAnnotation()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Throwable e) {
		
		 Logger logger = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
			
		// if(logger.isDebugEnabled()) {
				
				String logBuilder = LoggingUtil.LOGGER_BEFORE_PREFIX + "After Logger Throwing - " + getName(joinPoint)
						+ LoggingUtil.logAfterThrowingRequest(joinPoint.getArgs(), true) + "\n " + "Error Message=" + e.getMessage();
		
				logger.error(logBuilder);
			//}
	}

	
	private String getName(JoinPoint joinPoint) {
		return joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName() + " ";
	}
	
}
