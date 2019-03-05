package com.gk.mortgage.calculator.logging;




public class EncryptDataMask implements SensitiveDataMask {

  @Override
  public String mask(String value) {
    String maskedValue = (MaskingUtil.encrypt(value));
    return maskedValue;
  }

  @Override
  public String mask(String value, int keepLastDigits) {
    return null;
  }


}
