package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository <Customer, Long>  {
}
