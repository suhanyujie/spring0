package com.xs.basic1;

public class Lambda1Service {
    public static void main(String[] args) {
        ICal1 iCal1 = (int i, int j) -> {
            return i + j;
        };
        System.out.printf("%d\n", iCal1.sum(5, 6));
    }
}
