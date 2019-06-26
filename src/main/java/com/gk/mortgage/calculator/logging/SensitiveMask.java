package com.gk.mortgage.calculator.logging;


public interface SensitiveMask {

  String mask(String value);

  String mask(String value, int keepLastDigits);

}
