package com.xs.basic1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.function.Predicate;

public class StreamTest {

    @Test
    public void test1() {
        String[] arr = {"tes", "1", "2", "3", "wu", "4", "5", "6"};
        int res = Arrays.stream(arr).filter(StreamTest::isNumber)
                .mapToInt(Integer::parseInt)
                .filter(ele -> ele % 2 == 0)
                .sum();
        System.out.println(res);
    }

    public static boolean isNumber(String input) {
        Predicate<String> isNum = s->s.matches("-?\\d+(\\.\\d+)?");
        return isNum.test(input);
    }
}
