package com.exa_aop.aopService;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public void getUserList() {

    }

    public void updateUser(Integer uid) {
        System.out.printf("uid: %d \n", uid);
    }


    public String getUserNameById(Integer uid) {
        System.out.printf("getUserNameById uid: %d \n", uid);
        return "刘德成";
    }

    public String getUserInfoByIdForThrowError(Integer uid) {
        throw new RuntimeException("custom error 1001.");
    }

    public void trySomeFn1() {
        try {
            throw new Exception("err message 1002");
        } catch (Exception e) {
            System.out.printf("trySomeFn1 catch\n");
        } finally {
            System.out.printf("trySomeFn1 finally\n");
        }
    }
}
