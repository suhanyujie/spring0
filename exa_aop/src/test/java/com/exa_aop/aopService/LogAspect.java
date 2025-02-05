package com.exa_aop.aopService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect  // 标记为切面类
public class LogAspect {

    // @Around：环绕通知。“通知”类型之一。
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

    // “前置通知”
    @Before("execution(* com.exa_aop.aopService.UserService.*(..))")
    public void beforeFn(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("beforeFn %s \t %s \n", methodName, args);
    }

    @AfterReturning(value="execution(* com.exa_aop.aopService.UserService.*(..))", returning = "returnValue")
    public void afterReturningFn(JoinPoint joinPoint, Object returnValue) {
        String methodName = joinPoint.getSignature().getName();
        System.out.printf("afterReturningFn %s \t return: %s \n", methodName, returnValue);
    }

    @After("execution(* com.exa_aop.aopService.UserService.*(..))")
    public void afterFn(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("afterFn %s \t %s \n", methodName, Arrays.toString(args));
    }

    @AfterThrowing("execution(* com.exa_aop.aopService.UserService.*(..))")
    public void afterThrowing1(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.printf("afterThrowing1 %s \t \n", methodName);
    }


    @AfterThrowing(value = "execution(* com.exa_aop.aopService.UserService.*(..))", throwing = "exceptionObj")
    public void afterThrowing2(JoinPoint joinPoint, Exception exceptionObj) {
        String methodName = joinPoint.getSignature().getName();
        System.out.printf("afterThrowing2 %s \t %s \n", methodName, exceptionObj);
    }
}
