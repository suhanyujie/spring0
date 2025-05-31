package com.xs.hello1.unserialize;

import com.xs.hello1.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Model1 {
    Logger logger = LoggerFactory.getLogger(Model1.class);

    private String name;
    private String age;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUndefinedAttr1() {
        logger.info("getUndefinedAttr1 step1001 \n");
        return desc;
    }
}
