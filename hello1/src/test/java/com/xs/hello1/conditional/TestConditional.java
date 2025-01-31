package com.xs.hello1.conditional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@SpringBootTest(classes = TestConditional.class)
public class TestConditional {

    @Bean
    @Conditional(MyConditional.class)
    public ConditionalService conditionalService() {
        return new ConditionalService();
    }

    @Test
    public void test1() {

    }
}
