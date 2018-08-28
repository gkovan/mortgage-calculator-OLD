package com.gk.mortgage.calculator.utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class TestUtils {

  
  public static String readFile(String filename) {
      String result = "";
      try {
          BufferedReader br = new BufferedReader(new FileReader(filename));
          StringBuilder sb = new StringBuilder();
          String line = br.readLine();
          while (line != null) {
              sb.append(line);
              line = br.readLine();
          }
          result = sb.toString();
          br.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
      return result;
  }
}
