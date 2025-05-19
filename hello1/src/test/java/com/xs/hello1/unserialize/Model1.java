package com.xs.hello1.unserialize;

public class Model1 {
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
        System.out.printf("getUndefinedAttr1 step1001 \n");
        return desc;
    }
}
