package com.xs.hello1.service.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("someConfig2.properties")
public class UserDomain {
    @Value("刘德华")
    private String name;
    @Value("42")
    private Integer age;

    @Value("${u1.skills}")
    protected String skills;

    @Override
    public String toString() {
        System.out.printf("name:%s,age:%s,skills:%s\n", name, age, skills);
        return "";
    }
}
