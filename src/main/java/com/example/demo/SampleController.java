package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//1. method
@RestController

        //can be declared public also
class SampleController {
  @GetMapping("/hello")
  public String sayHello() {
    return "Hello";

  }
}
