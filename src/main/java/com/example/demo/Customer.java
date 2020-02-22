package com.example.demo;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Customer {

  @Id @GeneratedValue Long id;
  String name;
  //final String name;
  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
