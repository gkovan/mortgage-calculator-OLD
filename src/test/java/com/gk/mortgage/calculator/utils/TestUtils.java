package com.gk.mortgage.calculator.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;

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
  
  private static final String UTF_8 = "utf-8";
  
  public static String loadSourceFile(String path) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream(), Charset.forName(UTF_8)));
      String response = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
      bufferedReader.close();
      return response;
  }
}
