package rmi;

import java.util.ArrayList;
import java.util.List;

public class Bank {
  String id;
  String name;
  List<Customer> customers;

  Bank(String name) {
    this.name = name;
    this.customers = new ArrayList<Customer>();
  }

}
