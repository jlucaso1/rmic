import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer extends UnicastRemoteObject
    implements Hello {
  public HelloServer() throws RemoteException {
    super();
  }

  public String Hello(String message) {
    System.out.println(message);
    return "Hello World from RMI server!!!";
  }

  public static void main(String args[]) {
    try {
      // Creates an object of the HelloServer class.
      HelloServer obj = new HelloServer();
      // Bind this object instance to the name "HelloServer".
      Naming.rebind("Hello", obj);
      System.out.println("Ligado no registro");
    } catch (Exception ex) {
      System.out.println("error: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

}