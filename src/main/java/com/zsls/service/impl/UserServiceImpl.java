package com.zsls.service.impl;

import com.zsls.model.User;
import com.zsls.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String addUser(User user) {
        return "success";
    }
}