package com.xs.hello1.conf;

import com.xs.hello1.service.IUserService;
import com.xs.hello1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 该配置类用来代替 xml 配置文件（依赖注入声明）
@Configuration
@ComponentScan("com.xs")
//@Import(MyImportSelector.class)
//@Import(MyImportBeanDefinitionRegistrar.class)
public class SpringConfig {

    /**
     * `@Bean`
     * a.该注解写在方法上
     * b.通常写在“配置类”中
     *  1.@Bean 方法调用另一个 Bean 方法时，会从 spring 容器中获取。
     * c.可以干预 Bean 的实例化过程，jar 包中的类如果要配置 bean 就需要用 @Bean
     * d.在方法中声明的参数 spring 会帮我们自动注入进来
     */
//    @Bean
//    public IUserService newUserSvc() {
//        return new UserService();
//    }
}
