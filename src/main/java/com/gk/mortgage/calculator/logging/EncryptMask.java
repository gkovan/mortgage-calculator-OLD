package com.gk.mortgage.calculator.logging;




public class EncryptMask implements SensitiveMask {

  @Override
  public String mask(String value) {
    String maskedValue = (MaskingUtil.encryptPassword(value));
    return maskedValue;
  }

  @Override
  public String mask(String value, int keepLastDigits) {
    return null;
  }


}
