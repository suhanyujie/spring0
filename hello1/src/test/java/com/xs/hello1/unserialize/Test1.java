package com.xs.hello1.unserialize;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;

public class Test1 {
    /**
     * json 反序列化时，会执行对应对象的 `get*` 方法。
     */
    @Test
    public void test1() {
        String json1 = "{\"name\":\"zs\",\"age\":\"18\",\"desc\":\"desc\"}";
        Model1 obj1 = JSON.parseObject(json1, Model1.class);
        System.out.printf("%s\n", JSON.toJSONString(obj1));
    }
}
