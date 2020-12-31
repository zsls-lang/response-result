package com.zsls.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Tag;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(value = "281-学校信息表", description = "281-学校信息表")
public class User {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "id")
    private Long id;


    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    @ApiModelProperty(value = "account")
    private String account;


    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    @ApiModelProperty(value = "password")
    private String password;


    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "email")
    private String email;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}