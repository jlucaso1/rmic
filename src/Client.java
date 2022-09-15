import java.rmi.Naming;

public class Client {
  static String message = "blank";
  // The Hello object "obj" is the identifier that is

  // the Hello interface.
  static Services obj = null;

  public static void main(String args[]) {
    try {
      obj = (Services) Naming.lookup("//"
          + "localhost"
          + "/Hello");
      Number x = new Number(4);
      Number y = new Number(6);
      Number result = obj.soma(x, y);
      System.out.println(result.valor);
    } catch (Exception e) {
      System.out.println("HelloClient exception: "
          + e.getMessage());
      e.printStackTrace();
    }
  }
}