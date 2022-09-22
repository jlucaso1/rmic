package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Services extends Remote {

  public Customer createCustomer(Customer customer, Bank bank) throws RemoteException;

}