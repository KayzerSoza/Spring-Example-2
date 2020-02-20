package com.example.demo;


import lombok.Data;

@Data
public class Customer {

  Long id;
  String name;
  //final String name;

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
