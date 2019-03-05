package com.gk.mortgage.calculator.logging;


public class PIIDataMask implements SensitiveDataMask {

  @Override
  public String mask(String value) {
    return null;
  }

  @Override
  public String mask(String value, int keepLastDigits) {
    String maskedValue = MaskingUtil.createHash(value, keepLastDigits);
    return maskedValue;
  }
}
