package com.zsls.framework.service.impl;

import com.zsls.framework.model.User;
import com.zsls.framework.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override public String addUser(User user) {
        return "success";
    }

    @Override public User addUser1(User user) {
        return null;
    }
}