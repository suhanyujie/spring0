package com.xs.hello1.conf;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.xs.hello1.service.UserService",
                "com.xs.hello1.dao.UserDao",
        };
    }
}
