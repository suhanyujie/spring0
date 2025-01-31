package com.xs.hello1.beanLifetime;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;

public class BeanLifetimeService implements InitializingBean{

    @PostConstruct
    public void init1() {
        System.out.println("BeanLifetimeService init1");
    }


    public void init2() {
        System.out.println("BeanLifetimeService init2");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanLifetimeService afterPropertiesSet");
    }
}
