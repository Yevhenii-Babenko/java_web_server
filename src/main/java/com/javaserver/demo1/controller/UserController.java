package com.javaserver.demo1.controller;

import com.javaserver.demo1.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @GetMapping("user")
    public User getUser() {
        return new User(1, "John","Doe", "jdoe@test.com" );
    }

    @GetMapping("users")
    public List<User> getUsersList() {
        List<User> users = new ArrayList<User>();
        users.add(new User(1, "John","Doe", "jdoe@test.com" ));
        users.add(new User(2, "Tom", "Cat", "tcat@test.com"));
        users.add(new User(3, "Tim", "Ray", "tray@test.com"));
        users.add(new User(4, "Jack", "Black", "jblack@test.com"));
        return users;
    }

    // Request query parameters
    @GetMapping("user/query")
    public User userQueryRequest(@RequestParam(name= "id") int id, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "email") String email) {
        return new User(id, firstName, lastName, email);
    }
}
