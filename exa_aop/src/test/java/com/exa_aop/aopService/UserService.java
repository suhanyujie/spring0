package com.exa_aop.aopService;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public void getUserList() {

    }

    public void updateUser(Integer uid) {
        System.out.printf("uid: %d \n", uid);
    }
}
