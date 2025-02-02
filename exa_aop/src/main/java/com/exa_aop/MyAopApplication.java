package com.exa_aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication 注解中，自动启用了 @EnableAspectJAutoProxy
@SpringBootApplication
public class MyAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAopApplication.class, args);
    }
}
