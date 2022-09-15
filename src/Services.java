import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Services extends Remote {

  public Number soma(Number x, Number y) throws RemoteException;

}