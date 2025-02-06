package com.exa_aop.aopService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootTest(classes = ExaAopApplicationTests.class)
@ComponentScan
@EnableAspectJAutoProxy
public class ExaAopApplicationTests {

    @Test
    public void test1(@Autowired UserService userService) {
        userService.getUserList();
        userService.updateUser(1);
        System.out.printf("end...\n");
    }

    @Test
    public void testAfterReturning(@Autowired UserService userService) {
        userService.getUserNameById(1);
        System.out.printf("end...\n");
    }

    @Test
    public void testThrowing(@Autowired UserService userService) {
        userService.getUserInfoByIdForThrowError(1);
        System.out.printf("end...\n");
    }

    @Test
    public void testAnnPointcut1(@Autowired ProjectService projectService) {
        projectService.edit();
    }
}
