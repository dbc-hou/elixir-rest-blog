package com.example.restblog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam (name = "myName", required = false, defaultValue = "User") String myName) {
        return "<h1>Greetings from Spring Boot, " + myName + "!</h1>";
    }
}
