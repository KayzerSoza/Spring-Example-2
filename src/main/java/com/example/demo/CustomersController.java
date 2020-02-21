package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/*
// void method adds customer to the list. In this case Get Method returns empty list
  @RequestMapping(value = "/customers", method = POST)
  public void createCustomer(@RequestBody Customer customer) {
    // Without @RequestBody GET method returns null values
    customersList.add(customer);
  }
*/

/*  //This method would return only Customer objects.
   //We want return HTTP messages as well.Better to use ResponseEntity
  @RequestMapping(value = "/customers", method = POST)
  public Customer createCustomer(@RequestBody Customer customer) {
    customersList.add(customer);
    return customer;
  }*/

  @RequestMapping(value = "/customers", method = POST)
  public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

    customersList.add(customer);
    HttpHeaders headers=new HttpHeaders();
    headers.add("Location", "/api/customers/"+customer.getId());
    return new ResponseEntity(customer, headers, HttpStatus.CREATED );

  }

}
