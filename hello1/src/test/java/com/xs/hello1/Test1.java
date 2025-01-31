package com.xs.hello1;

import com.xs.hello1.conf.SpringConfig;
import com.xs.hello1.service.IUserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    @Test
    public void test() {
        // spring 容器实例化。（根据“xml 文件”实例化）
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        IUserService svc = ioc.getBean(IUserService.class);
        svc.getUser();
    }

    @Test
    public void test2() {
        // spring 容器实例化。（根据“配置类”实例化）
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService svc = ioc.getBean(IUserService.class);
        svc.getUser();
    }
}
