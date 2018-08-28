import java.util.Scanner;
 
public class Mortgage {
   public static void main(String args[]){
 
      Scanner sc = new Scanner(System.in);
 
      //double principal = 200000; 
      System.out.print("Enter Principal: ");
      double principal = sc.nextDouble(); 
 
      // Annual interest rate
      System.out.print("Enter Yearly Interest Rate: ");
      double rate = sc.nextDouble(); 
 
      // Monthly intertest rate
      rate = rate/100/12; 
 
      // Term in years
      System.out.print("Enter Term (years): ");
      int term = sc.nextInt();
 
      // Term in months
      term = term * 12; 
 
      double payment = (principal * rate) / (1 - Math.pow(1 + rate, -term));
 
      // round to two decimals
      payment = (double)Math.round(payment * 100) / 100;
 
      System.out.println("Payment: " + payment);
 
   }
}
