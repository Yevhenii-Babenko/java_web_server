package com.javaserver.demo1.controller;

import com.javaserver.demo1.exception.ResourceNotFoundException;
import com.javaserver.demo1.model.User;
import com.javaserver.demo1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // get users
    @GetMapping("users")
    public List<User> getAllUsers() {
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
