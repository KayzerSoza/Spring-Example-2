package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

// we want api/customers url.
// So we need to add a default mapping that will affect all methods below
@RestController
@RequestMapping("/api/customers") // we can add /customers path here to write less code
public class CustomersController {
  //Collections.synchronizedList() causes syc for reading as well which is not efficient.
  // therefore it is better to use  CopyOnWriteArrayList<>()
  private List<Customer> customersList = new CopyOnWriteArrayList<>();

  AtomicLong counter = new AtomicLong(); // will increment customerId in a thread secure way

  @GetMapping       // replaced @RequestMapping(value = "/customers", method = GET)
  public List<Customer> allCustomers() {
    return customersList;
  }

  @GetMapping(value = "/{id}")  // replaced  @RequestMapping(value = "/customers/{id}")
  public ResponseEntity<Customer> getOneCustomerById(@PathVariable long id) {
    var customerOptional = customersList.stream()
            .filter(customer -> customer.getId() == id)
            .findFirst();
    return customerOptional
            .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    //     .orElse(()-> new CustomErrorType("User with id "+ id+ " not found", HttpStatus.NOT_FOUND));
  }
  @PostMapping     //replaced   @RequestMapping(value = "/customers", method = POST)
  public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

    customer.setId(counter.addAndGet(1)); // increments counter by delta and gets the value to setId
    customersList.add(customer);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/customers/" + customer.getId());
    return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
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


}
