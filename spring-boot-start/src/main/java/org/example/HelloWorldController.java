package org.example;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!!!";
    }

    @GetMapping("/cars")
    public String getCars() {
        return "All Cars";
    }
}
