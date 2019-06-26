package com.gk.mortgage.calculator.logging;

public class PCIMask implements SensitiveMask {

	  @Override
	  public String mask(String value) {
	    return null;
	  }

	  @Override
	  public String mask(String value, int keepLastDigits) {
	    String maskedValue = MaskingUtil.createPCIMask(value, keepLastDigits);
	    return maskedValue;
	  }
	}
