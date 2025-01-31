package com.xs.hello1.beanLifetime;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest(classes = TestBeanLifetime.class)
public class TestBeanLifetime {

    @Bean(initMethod = "init2")
    public BeanLifetimeService beanLifetimeService() {
        return new BeanLifetimeService();
    }

    @Bean(destroyMethod = "destroy2")
    public BeanLifetimeEndService beanLifetimeEndService() {
        return new BeanLifetimeEndService();
    }

    @Test
    public void test1(@Autowired BeanLifetimeService beanLifetimeService) {

    }

    @Test
    public void testEnd(@Autowired BeanLifetimeEndService beanLifetimeEndService) {

    }
}
