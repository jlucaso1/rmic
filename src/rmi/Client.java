package rmi;

import java.rmi.Naming;

public class Client {
  static String message = "blank";

  static Services serverFunc = null;

  public static void main(String args[]) {
    try {
      serverFunc = (Services) Naming.lookup("//"
          + "localhost"
          + "/Hello");
      Bank nubank = new Bank("Nubank");
      Customer newCustomer = new Customer();
      newCustomer.name = "João Lucas";
      Number result = serverFunc.createCustomer(newCustomer, nubank);
      // System.out.println(result.valor);
    } catch (Exception e) {
      System.out.println("HelloClient exception: "
          + e.getMessage());
      e.printStackTrace();
    }
  }
}