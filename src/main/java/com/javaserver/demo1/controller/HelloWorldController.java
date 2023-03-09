package com.javaserver.demo1.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {
    // GET HTTP method
    // http://localhost:8080/api/v1/hello-world

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello world!";
    };
}
