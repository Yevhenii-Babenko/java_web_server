package com.javaserver.demo1.controller;

import com.javaserver.demo1.exception.ResourceNotFoundException;
import com.javaserver.demo1.model.User;
import com.javaserver.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1")
//public class UserController {
//    @GetMapping("user")
//    public User getUser() {
//        return new User(1, "John","Doe", "jdoe@test.com" );
//    }
//
//    @GetMapping("users")
//    public List<User> getUsersList() {
//        List<User> users = new ArrayList<User>();
//        users.add(new User(1, "John","Doe", "jdoe@test.com" ));
//        users.add(new User(2, "Tom", "Cat", "tcat@test.com"));
//        users.add(new User(3, "Tim", "Ray", "tray@test.com"));
//        users.add(new User(4, "Jack", "Black", "jblack@test.com"));
//        return users;
//    }
//
//    // Request query parameters
//    @GetMapping("user/query")
//    public User userQueryRequest(@RequestParam(name= "id") int id, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "email") String email) {
//        return new User(id, firstName, lastName, email);
//    }
//}

//@EnableJpaRepositories(basePackages="com.javaserver.demo1.repository.UserRepository")
//@Configuration
@RestController
@RequestMapping("/api/v1")
//@EnableJpaRepositories("com.javaserver.demo1.repository.UserRepository")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get users
    @GetMapping("users")
    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // get user by id
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User receivedUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(receivedUser);
    }

    // save users
    @PostMapping("users")
    public User createUser(@RequestBody User postUser) {
        return this.userRepository.save(postUser);
    }

    // update
    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        targetUser.setEmail(userDetails.getEmail());
        targetUser.setFirstName(userDetails.getFirstName());
        targetUser.setLastName(userDetails.getLastName());
        return ResponseEntity.ok(this.userRepository.save(targetUser));
    }

    // delete user
    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User deleteUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        this.userRepository.delete(deleteUser);
        this.userRepository.delete(deleteUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
