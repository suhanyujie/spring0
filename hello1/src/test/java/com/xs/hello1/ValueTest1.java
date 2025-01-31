package com.xs.hello1;


import com.xs.hello1.dao.UserDao;
import com.xs.hello1.service.domain.UserDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValueTest1 {

    @Test
    public void test1(@Autowired UserDomain userDomain) {
        System.out.println(userDomain);
    }
}
