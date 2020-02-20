package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
// we want api/customers url.
// So we need to add a default mapping for all the methods below
@RequestMapping("/api/")
public class CustomersController {
  //customersList will function as our database as  long as Controller runs
  private List<Customer> customersList=new ArrayList<>();


  @RequestMapping(value = "/customers", method = GET)
  public List<Customer> allCustomers(){

    return customersList;

  }

}
