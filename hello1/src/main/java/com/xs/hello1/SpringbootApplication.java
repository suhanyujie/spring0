package com.xs.hello1;

import com.xs.hello1.service.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(SpringbootApplication.class, args);
        IUserService svc = ioc.getBean(IUserService.class);
        svc.getUser();
    }
}
