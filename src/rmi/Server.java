package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Server extends UnicastRemoteObject
    implements Services {
  public Server() throws RemoteException {
    super();
  }

  public static void main(String args[]) {
    try {
      // Creates an object of the HelloServer class.
      Server obj = new Server();
      // Bind this object instance to the name "HelloServer".
      Naming.rebind("Hello", obj);
      System.out.println("Ligado no registro!!");
    } catch (Exception ex) {
      System.err.println("error: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  @Override
  public Customer createCustomer(Customer customer, Bank bank) throws RemoteException {
    customer.balance = 0;
    customer.id = UUID.randomUUID().toString();
    customer.bank = bank;
    bank.customers.add(customer);

    System.out.println("Customer: " + customer.name + " created!");
    return customer;
  }

}