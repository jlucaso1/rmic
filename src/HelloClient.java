import java.rmi.Naming;

public class HelloClient {
  static String message = "blank";
  // The Hello object "obj" is the identifier that is

  // the Hello interface.
  static Hello obj = null;

  public static void main(String args[]) {
    try {
      obj = (Hello) Naming.lookup("//"
          + "localhost"
          + "/Hello");
      message = obj.Hello("Hello World from RMI client!!!");
      System.out.println(message);
    } catch (Exception e) {
      System.out.println("HelloClient exception: "
          + e.getMessage());
      e.printStackTrace();
    }
  }
}