package com.javaserver.demo1.service;

import com.javaserver.demo1.model.User;

public interface UserService {
    public Iterable<User> getUsers(User user);
}
