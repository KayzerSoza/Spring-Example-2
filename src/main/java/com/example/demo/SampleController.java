package com.example.demo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class SampleController {

  @RequestMapping("/hello")

  public Greeting sayHello() {

    return new Greeting(3,"Greeting method says Hello!");

  }
}
