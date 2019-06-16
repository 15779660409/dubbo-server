package com.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.api.entity.User;
import com.dubbo.api.service.UserService;

/**
 * @author kanghaijun
 * @create 2019/6/14
 * @describ
 * */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        System.out.println("192.168.237.214");
        User user = new User();
        user.setId(1);
        user.setName("大表哥");
        return user;
    }
}
