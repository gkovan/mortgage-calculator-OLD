package com.gk.mortgage.calculator.logging;

import java.lang.annotation.Annotation;

import com.gk.mortgage.calculator.annotations.Password;
import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;

public class MaskFactory {

  public SensitiveMask getInstance(Annotation[] annotations) {

    for (Annotation an : annotations) {

      if (an instanceof PII) {
        return new PIIMask();
      } else {
        if (an instanceof PCI) {
          return new PCIMask();
        } else {
          if (an instanceof Password) {
            return new EncryptMask();
          }
        }
      }
    }
    return null;
  }
}