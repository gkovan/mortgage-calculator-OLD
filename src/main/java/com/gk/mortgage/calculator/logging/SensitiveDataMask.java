package com.gk.mortgage.calculator.logging;



public interface SensitiveDataMask {

  String mask(String value);

  String mask(String value, int keepLastDigits);

}
