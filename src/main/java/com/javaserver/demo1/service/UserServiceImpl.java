package com.javaserver.demo1.service;

import com.javaserver.demo1.model.User;
import com.javaserver.demo1.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
//    private final UserRepositoryImpl userRepository;
//
//    public UserServiceImpl(UserRepositoryImpl userRepository) {
//        this.userRepository = userRepository;
//    }
//
    public Iterable<User> getUsers(User user) {
        return null;
    }
}
