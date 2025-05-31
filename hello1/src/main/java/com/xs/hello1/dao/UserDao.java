package com.xs.hello1.dao;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // `@Component` 表示当前类交给 spring 进行管理（实例化）
public class UserDao implements IUserDao {
    Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Override
    public void getUser() {
        logger.info("do get user...");
    }
}
