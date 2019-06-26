package com.gk.mortgage.calculator.logging;

public class PIIMask implements SensitiveMask {

  @Override
  public String mask(String value) {
    return null;
  }

  @Override
  public String mask(String value, int keepLastDigits) {
    String maskedValue = MaskingUtil.createPIIMask(value, keepLastDigits);
    return maskedValue;
  }
}
