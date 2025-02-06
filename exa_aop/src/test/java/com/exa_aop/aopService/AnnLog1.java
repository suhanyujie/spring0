package com.exa_aop.aopService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 注解的保留
@Target({ElementType.METHOD}) // 表示这个注解可以标记在类的方法上
public @interface AnnLog1 {
    String value();
}
