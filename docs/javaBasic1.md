## lambda 表达式
函数式接口：接口中只有一个未实现的方法，那么此接口就可以称之为“函数式接口”。

只要式函数式接口，就可以用 lambda 表达式进行简化。

```java
///  hello1/src/test/java/com/xs/basic1/ICal1.java
public interface ICal1 {
    int sum(int num1, int num2);
}

/// hello1/src/test/java/com/xs/basic1/Lambda1Service.java
public class Lambda1Service {
    public static void main(String[] args) {
        ICal1 iCal1 = (int i, int j) -> {
            return i + j;
        };
        System.out.printf("%d\n", iCal1.sum(5, 6));
    }
}
```

实际上，在 java 中，可以在接口声明类中，增加方法的默认实现，但只要该接口中有且只有一个未实现的方法，那么就能作为“函数式接口”。

```java
public interface ICal1 {
    int sum(int num1, int num2);

    // 默认的方法实现
    default int mul(int num1, int num2) {return num1*num2;}
}
```

随着项目的维护，接口和类可能会越来越庞杂，此时，可以通过 `@FunctionalInterface` 注解，来把一个接口类声明为函数式接口。
这样可以让编译器对它进行合法性检查。

### 函数式接口
java 库中自带了很多函数式接口，比如上方提到的 `@FunctionalInterface`，还有很多其他的，如：
- `Function` （有入参，有出参。多功能函数）
- `Consumer` （有入参，无出参。消费者）
- `Runnable` （无入参，无出参。普通函数）
- `Supplier` (无入参，有出参。提供者)
- ...更多详见 `java.util.function` 包中。

```java
///  hello1/src/test/java/com/xs/basic1/FnInterfaceTest.java
public void test1() {
    Function<String, String> fn1 = (s1)->{
        return s1 + "|test|";
    };
    System.out.println(fn1.apply("hello "));
}
```

具体使用方法，可以直接在 idea 中点击类型跟踪，查看定义，进而了解使用方法。

## StreamAPI
最佳实践：用 for 循环处理数据的逻辑，可以全部用 StreamAPI 进行替换。

它其实是一种声明式处理集合数据的方法。包括：筛选，转换，组合等。

流由三大部分组成：
- 数据流
- 中间操作
- 终止操作

又因为流是懒加载的，必须有终止操作，才会真正执行。

流的创建：最常用的是可以直接基于集合数据进行创建，如：`list.stream()`。

常见的集合类型有：`ArrayList`、`Set`、`Map` 等

流处理中，默认不并发。但可以通过调用 `.parallel()` 进行并发处理。也可以通过 `StreamSupport` 来实现更精细化的并发控制。
需要注意的是，一旦并发处理，就需要自行考虑和处理并发安全问题。

```java
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
```





 





## ref
- https://www.bilibili.com/video/BV1sC4y1K7ET