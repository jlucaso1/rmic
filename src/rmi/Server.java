package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
  public Number soma(Number x, Number y) throws RemoteException {
    return new Number(x.valor + y.valor);
  }

}