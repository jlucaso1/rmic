package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Services extends Remote {

  public Customer createCustomer(String name, String bankName) throws RemoteException;

  public Double getBalance(Customer customer) throws RemoteException;

  public void deposit(String customerId, String bankName, Double value) throws RemoteException;

  public void withdraw(String customerId, String bankName, Double value) throws RemoteException;

  public void transfer(String customerId, String bankName, String destinationCustomerId, String destinationBankName, Double value) throws RemoteException;

  public void pix(String customerId, String bankName, String destinationCustomerId, String destinationBankName, Double value) throws RemoteException;
}