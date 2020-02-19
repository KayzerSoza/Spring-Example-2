package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//1. method
@Controller
@RequestMapping("/hello")
@ResponseBody
//can be declared public also
class SampleController {
@GetMapping
  public String sayHello(){
    return "Hello";

  }
}
