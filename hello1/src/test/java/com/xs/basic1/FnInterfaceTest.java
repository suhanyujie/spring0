package com.xs.basic1;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FnInterfaceTest {
    @Test
    public void test1() {
        Function<String, String> fn1 = (s1)->{
            return s1 + "|test|";
        };
        System.out.println(fn1.apply("hello "));

    }
}
