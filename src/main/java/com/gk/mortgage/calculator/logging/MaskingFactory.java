package com.gk.mortgage.calculator.logging;

import java.lang.annotation.Annotation;

import com.gk.mortgage.calculator.annotations.Encrypt;
import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;




public class MaskingFactory {

  public SensitiveDataMask getInstance(Annotation[] annotations) {

    for (Annotation an : annotations) {

      if (an instanceof PII) {
        return new PIIDataMask();
      } else {
        if (an instanceof PCI) {
          return new PCIDataMask();
        } else {
          if (an instanceof Encrypt) {
            return new EncryptDataMask();
          }
        }
      }
    }
    return null;
  }
}