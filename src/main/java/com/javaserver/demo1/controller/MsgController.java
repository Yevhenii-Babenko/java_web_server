package com.javaserver.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MsgController {

    @GetMapping("welcome")
    public String test() {
        return "Hello world";
    }
}
