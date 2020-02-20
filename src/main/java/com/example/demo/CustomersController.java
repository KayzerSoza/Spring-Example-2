package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/") // we want api/customers url. So we need to add a default mapping for all the methods below
public class CustomersController {

  @RequestMapping(value = "/customers", method = GET)
  public List<Customer> allCustomers(){

    return customersList;

  }

}
