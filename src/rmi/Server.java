package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server extends UnicastRemoteObject
    implements Services {
  public Server() throws RemoteException {
    super();
  }

  List<Bank> banks = new ArrayList<Bank>();

  public static void main(String args[]) {
    try {
      Server obj = new Server();
      obj.initBanks();
      Naming.rebind("Hello", obj);
      System.out.println("Ligado no registro!!");
    } catch (Exception ex) {
      System.err.println("error: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  void initBanks() {
    Bank nubank = new Bank("Nubank");
    Bank itau = new Bank("Itau");
    Bank bradesco = new Bank("Bradesco");
    Bank caixa = new Bank("Caixa");
    banks.add(nubank);
    banks.add(itau);
    banks.add(bradesco);
    banks.add(caixa);
  }

  Bank getBankByName(String name) {
    for (Bank bank : banks) {
      if (bank.name.equals(name)) {
        return bank;
      }
    }
    return null;
  }

  public Customer createCustomer(String name, String bankName) throws RemoteException {
    Bank bank = getBankByName(bankName);
    if (bank == null) {
      throw new RemoteException("Bank not found");
    }
    Customer customer = new Customer();
    customer.name = name;
    customer.balance = 0;
    customer.id = UUID.randomUUID().toString();
    customer.bank = bank;
    bank.customers.add(customer);

    System.out.println("Customer: " + customer.name + " created!");

    System.out.println("Bank: " + bank.name + " has " + bank.customers.size() + " customers");
    return customer;
  }

  @Override
  public Double getBalance(Customer customer) throws RemoteException {
    Bank bank = getBankByName(customer.bank.name);
    if (bank == null) {
      throw new RemoteException("Bank not found");
    }

    Customer myCustomer = bank.getCustomerById(customer.id);
    if (myCustomer == null) {
      throw new RemoteException("Customer not found");
    }

    return myCustomer.balance;
  }

  @Override
  public void deposit(String customerId, String bankName, Double value) throws RemoteException {
    Bank bank = getBankByName(bankName);
    if (bank == null) {
      throw new RemoteException("Bank not found");
    }

    Customer customer = bank.getCustomerById(customerId);
    if (customer == null) {
      throw new RemoteException("Customer not found");
    }

    customer.balance += value;
  }

  @Override
  public void withdraw(String customerId, String bankName, Double value) throws RemoteException {
    Bank bank = getBankByName(bankName);
    if (bank == null) {
      throw new RemoteException("Bank not found");
    }

    Customer customer = bank.getCustomerById(customerId);
    if (customer == null) {
      throw new RemoteException("Customer not found");
    }

    customer.balance -= value;
  }

  @Override
  public void transfer(String customerId, String bankName, String destinationCustomerId, String destinationBankName,
      Double value) throws RemoteException {
    Bank bank = getBankByName(bankName);
    if (bank == null) {
      throw new RemoteException("Bank not found");
    }

    Customer customer = bank.getCustomerById(customerId);
    if (customer == null) {
      throw new RemoteException("Customer not found");
    }

    Bank destinationBank = getBankByName(destinationBankName);
    if (destinationBank == null) {
      throw new RemoteException("Bank not found");
    }

    Customer destinationCustomer = destinationBank.getCustomerById(destinationCustomerId);
    if (destinationCustomer == null) {
      throw new RemoteException("Customer not found");
    }

    if (customer.balance < value) {
      throw new RemoteException("Customer " + customer.name + " does not have enough balance");
    }

    customer.balance -= value;
    destinationCustomer.balance += value;
  }

  @Override
  public void pix(String customerId, String bankName, String destinationCustomerId, String destinationBankName,
      Double value) throws RemoteException {
    // TODO Auto-generated method stub

  }

}