package com.xs.hello1.conf;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {

        RootBeanDefinition def1 = new RootBeanDefinition();
        def1.setBeanClassName("com.xs.hello1.service.UserService");
        registry.registerBeanDefinition("userService", def1);

        RootBeanDefinition def2 = new RootBeanDefinition();
        def2.setBeanClassName("com.xs.hello1.dao.UserDao");
        registry.registerBeanDefinition("userDao", def2);
    }
}
