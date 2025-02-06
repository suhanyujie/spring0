package com.exa_aop.aopService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyPointCut {
    @Pointcut("execution(* com.exa_aop.aopService.UserService.*(..))")
    public void testExecPointCut1(){}

    @Pointcut("within(com.exa_aop.aopService.ProjectService)")
    public void testExecPointCut2(){}

    // 针对所有使用了 `@AnnLog1` 注解的方法,进行切入
    @Pointcut("@annotation(AnnLog1)")
    public void testExecPointCut3(){}

    // “前置通知”
    @Before("testExecPointCut1()")
    public void beforeFn(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("MyPointCut.beforeFn %s \t %s \n", methodName, args);
    }

    @Before("testExecPointCut1() && testExecPointCut2() && testExecPointCut3()")
    public void beforeFn2(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("MyPointCut.beforeFn2 %s \t %s \n", methodName, args);
    }

    @Before("@annotation(log)")
    public void beforeFn3(JoinPoint joinPoint, AnnLog1 log) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("MyPointCut.beforeFn3 methodName: %s, 注解的参数值: %s \n", methodName, log.value());
    }
}
