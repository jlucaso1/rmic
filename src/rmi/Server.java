package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject
    implements Services {
  public Server() throws RemoteException {
    super();
  }

  public static void main(String args[]) {
    try {
      LocateRegistry.createRegistry(1099);
      Server obj = new Server();
      // Bind this object instance to the name "HelloServer".
      Naming.rebind("rmi://localhost:1099/Ola", obj);
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

// command to run the file with bin folder context \rmi\Client.class
// java -cp .;bin rmi.Client