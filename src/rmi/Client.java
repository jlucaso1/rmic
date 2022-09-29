package rmi;

import java.rmi.Naming;

public class Client {
  static String message = "blank";

  public static void main(String args[]) {
    try {
      Services server = (Services) Naming.lookup("//"
          + "localhost"
          + "/Hello");

      Customer joao_lucas_account = server.createCustomer("João Lucas", "Nubank");
      System.out.printf("Customer %s created with balance %.2f%n", joao_lucas_account.name,
          joao_lucas_account.balance);

      System.out.printf("Depositing 100.00 in %s account%n", joao_lucas_account.name);
      server.deposit(joao_lucas_account.id, joao_lucas_account.bank.name, 100.00);
      System.out.printf("New %s balance: %.2f%n", joao_lucas_account.name, server.getBalance(joao_lucas_account));

      System.out.printf("Withdrawing 50.00 in %s account%n", joao_lucas_account.name);
      server.withdraw(joao_lucas_account.id, joao_lucas_account.bank.name, 50.00);
      System.out.printf("New %s balance: %.2f%n", joao_lucas_account.name, server.getBalance(joao_lucas_account));

      Customer maria_account = server.createCustomer("Maria", "Nubank");
      System.out.printf("Customer %s created with balance %.2f%n", maria_account.name,
          maria_account.balance);

      System.out.printf("Depositing 50.00 to %s account", maria_account.name);
      server.deposit(maria_account.id, maria_account.bank.name, 50.0);
      System.out.printf("New %s balance: %.2f%n", maria_account.name, server.getBalance(maria_account));

      System.out.printf("Transferring 20.00 from %s to %s account%n", joao_lucas_account.name, maria_account.name);
      server.transfer(joao_lucas_account.id, joao_lucas_account.bank.name, maria_account.id, maria_account.bank.name,
          20.0);
      System.out.printf("New %s balance: %.2f%n", joao_lucas_account.name, server.getBalance(joao_lucas_account));
      System.out.printf("New %s balance: %.2f%n", maria_account.name, server.getBalance(maria_account));

    } catch (Exception e) {
      System.out.println("HelloClient exception: "
          + e.getMessage());
      // e.printStackTrace();
    }
  }
}