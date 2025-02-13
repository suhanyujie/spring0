package com.xs.basic1;

@FunctionalInterface
public interface ICal1 {
    int sum(int num1, int num2);

    // 默认的方法实现
    default int mul(int num1, int num2) {return num1*num2;}
}
