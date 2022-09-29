package rmi;

import java.io.Serializable;

public class Customer implements Serializable {
  String id;
  String name;
  Bank bank;
  double balance;
}
