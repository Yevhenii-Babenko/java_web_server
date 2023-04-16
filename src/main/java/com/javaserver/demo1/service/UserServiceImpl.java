package com.javaserver.demo1.service;

import com.javaserver.demo1.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public Iterable<User> getUsers(User user) {
        return null;
    }
}
