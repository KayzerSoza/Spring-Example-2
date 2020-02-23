package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// we want api/customers url.
// So we need to add a default mapping that will affect all methods below
@RestController
@RequestMapping("/api/customers")
// we can add /customers path here to write less code. therefore it is better to use  CopyOnWriteArrayList<>()
public class CustomersController {
  //Collections.synchronizedList() causes syc for reading as well which is not efficient.
  // private List<Customer> customersList = new CopyOnWriteArrayList<>();
  //  AtomicLong counter = new AtomicLong(); // not needed after db integration

  final CustomersRepository repository;

  public CustomersController(CustomersRepository storage) {
    this.repository = storage;
  }

  @GetMapping       // replaced @RequestMapping(value = "/customers", method = GET)
  public List<Customer> allCustomers() {
    return repository.findAll();
    //return repository.findByName("Tim");
    //return repository.findByNameCustom("Tim");
    //return repository.findByNameContainsOrNameContains("e", "E");
    //return repository.findByIdGreaterThan(3L);
    //return repository.findByIdBetween(3L, 6L);

  }


  @GetMapping(value = "/{id}")  // replaced  @RequestMapping(value = "/customers/{id}")
  public ResponseEntity<Customer> getOneCustomerById(@PathVariable long id) {
    var customerOptional = repository.findById(id);

    return customerOptional
            .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    //     .orElse(()-> new CustomErrorType("User with id "+ id+ " not found", HttpStatus.NOT_FOUND));
  }

  @PostMapping     //replaced   @RequestMapping(value = "/customers", method = POST)
  public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

    //customer.setId(counter.addAndGet(1)); //  NO LONGER NEEDED BECAUSE DB GIVES ID AUTOMATICALLY
    var c = repository.save(customer);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/customers/" + c.getId());
    return new ResponseEntity<>(c, headers, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteCustomer(@PathVariable Long id){
    if (repository.existsById(id)){
      repository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

/*
  @DeleteMapping("/{id}") // We  developed this method  with ResponseEntity
  void deleteCustomer(@PathVariable Long id){
    repository.deleteById(id);
  }
*/

  @PutMapping("/{id}")
  ResponseEntity<Customer> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
    return repository.findById(id)
            .map(customer -> {
              customer.setName(newCustomer.getName());
              repository.save(customer);
              HttpHeaders headers = new HttpHeaders();
              headers.add("Location", "/api/customers/" + customer.getId());
              return new ResponseEntity<>(customer, headers, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }


/*  @PutMapping("/{id}") // We  developed this method  with ResponseEntity
  Customer replaceCustomer(@RequestBody Customer newCustomer, @ PathVariable Long id){
    return repository.findById(id)
            .map(customer-> {
              customer.setName(newCustomer.getName());
               return repository.save(customer);
            }).orElseGet(()-> {
              newCustomer.setId(id);  //not allowed within Database
              return repository.save(newCustomer);
            } );
  }*/

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
