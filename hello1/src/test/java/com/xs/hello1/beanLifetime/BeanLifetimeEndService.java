package com.xs.hello1.beanLifetime;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;

public class BeanLifetimeEndService implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("BeanLifetimeEndService destroy");
    }

    public void destroy1() {
        System.out.println("BeanLifetimeEndService destroy1");
    }

    @PreDestroy
    public void destroy2() {
        System.out.println("BeanLifetimeEndService destroy2");
    }
}
