package com.zsls.service.impl;

import com.zsls.common.base.BaseServiceImpl;
import com.zsls.model.User;
import com.zsls.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String addUser(User user) {
        return "success";
    }

    @Override
    public User addUser1(User user) {
        return user;
    }
}