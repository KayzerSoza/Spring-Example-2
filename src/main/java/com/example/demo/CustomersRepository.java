package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Long> {
  List<Customer> findByName(String name);

  List<Customer> findByIdGreaterThan(Long id);

  //  @Query("Select c from Customer c where c.name  like ?1")
  @Query("Select c from Customer c where lower(c.name)  like lower(?1)")
  List<Customer> findByNameCustom(String name);

  List<Customer> findByNameContains(String letter);

  List<Customer> findByIdBetween(Long id1, Long id2);


}
