package com.gk.mortgage.calculator.logging;

public class PCIDataMask implements SensitiveDataMask {

	  @Override
	  public String mask(String value) {
	    return null;
	  }

	  @Override
	  public String mask(String value, int keepLastDigits) {
	    String maskedValue = MaskingUtil.maskDataButKeepLastFew(value, keepLastDigits);
	    return maskedValue;
	  }
	}
