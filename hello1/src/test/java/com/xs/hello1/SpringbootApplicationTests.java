package com.xs.hello1;

import com.xs.hello1.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired
    IUserService userSvc;

    @Test
    public void ctxLoads() {
        userSvc.getUser();
    }
}
