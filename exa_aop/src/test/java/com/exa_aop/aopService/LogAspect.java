package com.exa_aop.aopService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    // @Around：环绕通知
    // execution() 中写“切点表达式”
    @Around("execution(* com.exa_aop.aopService.UserService.*(..))")
    public void log(ProceedingJoinPoint proceedingJoinPoint) {
        long t1 = System.currentTimeMillis();

        // 执行具体的方法、逻辑
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("fn exec error:" + e.getMessage());
        }
        long t2 = System.currentTimeMillis();

        System.out.printf("time consume: %d s \n", (t2 - t1));
    }
}
