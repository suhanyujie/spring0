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