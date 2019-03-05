package com.gk.mortgage.calculator.logging;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;

public class MaskingUtil {

  private static final Logger log = Logger.getLogger(MaskingUtil.class);

  // PCI
  public static String maskDataButKeepLastFew(String input, int keeplastDigits) {
    if (input == null) {
      return null;
    }
    String maskedString = "";
    if (input.length() - keeplastDigits >= 0) {
      String inputForMask = input.substring(0, (input.length() - keeplastDigits));
      String inputNotForMask = input.substring((input.length() - keeplastDigits));
      inputForMask = inputForMask.replaceAll("(?s).", "*");
      maskedString = inputForMask + inputNotForMask;
    }
    return maskedString;
  }

  // PII
  public static String createHash(String input, int keeplastDigits) {
    if (input == null) {
      return null;
    }

    String hashedString = "";
    if (input.length() - keeplastDigits >= 0) {
      String inputForHash = input.substring(0, (input.length() - keeplastDigits));
      String inputNotForMask = input.substring((input.length() - keeplastDigits));
      String hashedResult = Integer.toHexString(inputForHash.hashCode());
      hashedString = hashedResult + inputNotForMask;
    }
    return hashedString;
  }

  // Encrypt
  // this is meant to be used for password masking
  // a fixed length mask string is returned so as not to reveal the length 
  public static String encrypt(String input) {
    if (input == null) {
      return null;
    }
    return "****";
  }
}

