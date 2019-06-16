package com.dubbo.consume.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.api.entity.User;
import com.dubbo.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kanghaijun
 * @create 2019/6/14
 * @describe
 */
@RestController
public class UserController{

    @Reference
    private UserService userService;

    @GetMapping("get")
    public User getUser(){
        System.out.println(8082);
        User user = userService.getUser();
        return user;
    }
}
