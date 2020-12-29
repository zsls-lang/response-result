package com.zsls.controller;

import com.zsls.enums.APICodeEnum;
import com.zsls.exception.CustomException;
import com.zsls.model.User;
import com.zsls.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户测试")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @PostMapping("/addUser1")
    @ApiOperation("测试用户了1")
    public User addUser1(@Valid User user) {
        return userService.addUser1(user);
    }

    @GetMapping("/getInfo")
    public String addUser() throws Exception{
        throw new CustomException("自定义的错误",APICodeEnum.API_ERROR);
//       int a =  1/0;
//        return "342432";
    }

    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        return user;
    }

}