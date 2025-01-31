package com.xs.hello1.dao;

import org.springframework.stereotype.Component;

@Component // `@Component` 表示当前类交给 spring 进行管理（实例化）
public class UserDao implements IUserDao {


    @Override
    public void getUser() {
        System.out.printf("do get user...");
    }

}
