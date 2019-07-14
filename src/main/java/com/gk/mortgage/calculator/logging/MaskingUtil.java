package com.gk.mortgage.calculator.logging;
import org.apache.log4j.Logger;

public class MaskingUtil {

  private static final Logger log = Logger.getLogger(MaskingUtil.class);

  // For PII
  public static String createPIIMask(String piiInput, int keeplastDigits) {
    if (piiInput == null) {
      return null;
    }

    String piiString = "";
    if (piiInput.length() - keeplastDigits >= 0) {
      String piiInputForMask = piiInput.substring(0, (piiInput.length() - keeplastDigits))+"PII";
      String piiInputNotForMask = piiInput.substring((piiInput.length() - keeplastDigits));
      String piiHashResult = Integer.toHexString(piiInputForMask.hashCode());
      piiString = piiHashResult + piiInputNotForMask;
    }
    return piiString;
  }
  
  // PCI
  public static String createPCIMask(String pciInput, int keeplastDigits) {
    if (pciInput == null) {
      return null;
    }
    String pciString = "";
    if (pciInput.length() - keeplastDigits >= 0) {
      String pciInputForMask = pciInput.substring(0, (pciInput.length() - keeplastDigits))+"PCI";
      String pciInputNotForMask = pciInput.substring((pciInput.length() - keeplastDigits));
      pciInputForMask = pciInputForMask.replaceAll("(?s).", "*");
      pciString = pciInputForMask + pciInputNotForMask;
    }
    return pciString;
  }

  // Password
  // this is meant to be used for password masking
  // a fixed length mask string is returned so as not to reveal the length 
  public static String encryptPassword(String input) {
    if (input == null) {
      return null;
    }
    return "****";
  }
}

