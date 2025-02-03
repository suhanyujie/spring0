## 项目的框架结构
通过 maven 管理多个模块。
最外层的父模块中，直接删除 src 目录，将 pom.xml 文件中，增加 packaging 属性，且值为 `pom`。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!--   
    略...
    -->

    <packaging>pom</packaging>
    
    <!--   
    略...
    -->
</project>
```

后续基于当前父模块，创建多个子模块进行开发。如下图所示：

![springProjectDirLayout1.png](images1/springProjectDirLayout1.png)![](./docs/images1/)


## 注解
### @Autowired


### @Value
当 @Value 注解作用在类的属性上时，表示向该属性注入对应的值。如 Integer，String 类型等

```java
@Component
public class UserDomain {
    @Value("刘德华")
    private String name;
    @Value("42")
    private Integer age;
}
```

还可以通过 @PropertySource 注解，将配置文件中的值进行注入：

```java
/// UserDao.java
@PropertySource("someConfig2.properties")
public class UserDomain {
    // ...
    @Value("${u1.skills:PHP}")
    protected String skills;
}
```

```
/// [someConfig2.properties](../hello1/src/main/resources/someConfig2.properties)
u1.skills = "PHP,Golang,Java"
u1.age = "39"
```

如果配置文件中的对应的 key 不存在，可以制定一个默认值：

```java
/// UserDao.java
@PropertySource("someConfig2.properties")
public class UserDomain {
    // ...
    @Value("${u1.skills:PHP}")
    protected String skills;
}
```

可以通过在 `:` 后，指定对应的默认值。如果默认值也不提供，在 Springboot 中则会报错，因为该机制在 springboot 中更严格。
而在 spring 中，key 不存在时，则不回报错，默认会将 key 对应的”名称“作为值使用，如 `${u1.skills}`。

### @Order/@DependsOn
通过 @Order/@DependsOn 注解可改变 Bean 实例化的顺序。

### @Lazy
通过 @Lazy 注解实现 Bean 的懒加载。从而优化应用的启动速度。

当然，也可以通过在 application.properties 配置文件中添加一下配置，实现所有 Bean 的懒加载。

```properties
spring.main.lazy-initialization=true
```

### @Scope
通过该注解，实现 Bean 对象的单例模式和多例模式。如：`@Scope("singleton")`，`@Scope("prototype")`

### @Conditional
动态决定某个 Bean 是否生效。

```java
@SpringBootTest(classes = TestConditional.class)
public class TestConditional {

    @Bean
    @Conditional(MyConditional.class)
    public ConditionalService conditionalService() {
        return new ConditionalService();
    }
}
```

其中 `MyConditional.class` 是自己定义的实现了 `org.springframework.context.annotation.Condition` 接口的类。
由其返回值确定对应的 Bean 是否生效。

### Bean 的生命周期
#### 生命周期-开始：@PostConstruct
通过 @PostConstruct 注解实现。

```java
public class BeanLifetimeService implements InitializingBean{

    @PostConstruct
    public void init1() {
        System.out.println("BeanLifetimeService init1");
    }
}
```

#### 生命周期-开始：Bean 注解参数 initMethod
可以对 `@Bean` 注解传入参数：`@Bean(initMethod = "init2")`，从而指定初始化时的钩子方法。

```java
@SpringBootTest(classes = TestBeanLifetime.class)
public class TestBeanLifetime {

    @Bean(initMethod = "init2")
    public BeanLifetimeService beanLifetimeService() {
        return new BeanLifetimeService();
    }
}
```

#### 生命周期-开始 
通过实现 `org.springframework.beans.factory.InitializingBean` 接口，可以清楚地看到 Bean 的生命周期中，开始时的相关调用。

```java
import org.springframework.beans.factory.InitializingBean;

public class BeanLifetimeService implements InitializingBean{
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("BeanLifetimeService afterPropertiesSet");
    }
}
```

以上 3 中方式中的优先级：@PostConstruct > initMethod > InitializingBean 接口。

#### 生命周期-结束：基于接口

```java
import org.springframework.beans.factory.DisposableBean;

public class BeanLifetimeEndService implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("BeanLifetimeEndService destroy");
    }
}
```

#### 生命周期-结束：基于注解 @PreDestroy

```java
public class BeanLifetimeEndService implements DisposableBean {
    // ...

    @PreDestroy
    public void destroy2() {
        System.out.println("BeanLifetimeEndService destroy2");
    }
}
```

#### 生命周期-结束：Bean 注解参数 destroyMethod

```java
@SpringBootTest(classes = TestBeanLifetime.class)
public class TestBeanLifetime {

    // ...

    @Bean(destroyMethod = "destroy2")
    public BeanLifetimeEndService beanLifetimeEndService() {
        return new BeanLifetimeEndService();
    }
}
```


需要注意的是，springboot 中，当 jvm 进程结束后，会自动调用 ioc 容器的销毁方法 `close`。
而在 spring 中，则需要手动调用销毁方法。

## 循环依赖
在 spring 中，可以进行循环依赖。

而在 springboot 中，默认情况下，循环依赖被认为是一种不合理的设计，因此会报错。

解决方式 1:

可以通过增加特殊的配置项，达到支持的目的：

```xml
# 放开循环依赖的限制
spring.main.allow-circular-references = true
```

不过，即使如此，循环依赖也是不建议使用的。

解决方式 2:

调整代码的设计和组织，独立出相应的逻辑。

解决方式 3:

通过延迟注入的方式也可以解决。

## SpringAOP 面向切面编程
AOP：Aspect Oriented Programming

切面：主要是“增强”作用。在不改变原有代码的基础上进行增强（额外运行“切面”中的代码）

启用 AOP，需要添加注解 `@EnableAspectJAutoProxy`，否则， AOP 功能无法使用。

不过，如果**启动类和切面代码位于同一个包**中，则默认可以使用。
原因是：启动类中有 @SpringBootApplication 注解，该注解中，此时，自动加上了 `@EnableAspectJAutoProxy` 注解。

```java
// @SpringBootApplication 注解中，自动启用了 @EnableAspectJAutoProxy
@SpringBootApplication
public class MyAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAopApplication.class, args);
    }
}
```

即使如此，真正使用时，建议还是加上 `@EnableAspectJAutoProxy` 注解。

```java
package com.exa_aop.aopService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootTest(classes = ExaAopApplicationTests.class)
@ComponentScan
@EnableAspectJAutoProxy // -->启用 AOP 代理
public class ExaAopApplicationTests {

    @Test
    public void test1(@Autowired UserService userService) {
        userService.getUserList();
        System.out.printf("end...\n");
    }
}
```

以上，是针对 springboot。如果是 spring，对应注解是必须要加的。

通过 `@Aspect` 可将一个类标记为切面类：

```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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
}
```

通过特定注解，将方法设定为特定的执行动作。一般包含以下几种类型：

- 环绕通知 `@Around`：可以把代码增加在目标方法的任意地方，更通用
- 前置通知 `@Before`：目标方法之前执行
- 后置通知 `@After`：目标方法之后执行
- 异常通知 `@AfterThrowing`：目标方法出现了异常时执行
- 返回通知 `@AfterReturning`：目标方法返回值时执行

**切点**：通过“切点表达式”，设定代码要切入到哪些方法中。

**连接点**：“通知”和目标方法的一个桥梁。例如：上方示例代码中的 `proceedingJoinPoint` 对象。

 
