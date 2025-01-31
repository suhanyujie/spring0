package com.xs.hello1.lazy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootTest(classes = TestLazy1.class)
public class TestLazy1 {

    @Bean
    @Lazy
    public Lazy1 newLazy1() {
        return new Lazy1();
    }

    @Test
    public void test1() {

    }
}
