package rmi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {
  String id;
  String name;
  List<Customer> customers;

  Bank(String name) {
    this.name = name;
    this.customers = new ArrayList<Customer>();
  }

  Customer getCustomerById(String id) {
    for (Customer customer : customers) {
      if (customer.id.equals(id)) {
        return customer;
      }
    }
    return null;
  }

}
