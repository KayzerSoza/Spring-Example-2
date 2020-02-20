package com.example.demo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
// we want api/customers url.
// So we need to add a default mapping that will affect all methods below
@RestController
@RequestMapping("/api/")
public class CustomersController {
  //customersList will function as our database as  long as Controller runs
  private List<Customer> customersList = new ArrayList<>();


  @RequestMapping(value = "/customers", method = GET)
  public List<Customer> allCustomers() {
    return customersList;

  }

  @RequestMapping(value = "/customers", method = POST)
  public void createCustomer(@RequestBody Customer customer) {
    // Without @RequestBody GET method returns null values
    customersList.add(customer);
  }

}
