package com.xs.hello1.myclass;

import org.junit.jupiter.api.Test;

/**
 * 静态属性是跟随者类的加载而加载的，而不是跟随对象的创建而加载的。
 * 因此，静态属性是类级别的属性，而不是对象级别的属性。它也无法被继承。
 * 静态属性在类加载时就已经存在，并且在整个程序的生命周期内都存在。
 * 静态属性可以被类的所有实例共享，也就是说，无论创建多少个对象，它们都共享同一个静态属性。
 * 静态属性可以通过类名直接访问，也可以通过对象名访问。
 * 静态属性可以在类的任何地方访问，包括静态方法、非静态方法、构造方法等。
 * 静态属性可以被修饰为 final，这样就不能被修改了。
 * 静态属性可以被修饰为 static final，这样就不能被修改了，也不能被重写。
 * 静态属性可以被修饰为 static final transient，这样就不能被修改了，也不能被重写，也不能被序列化。
 * 静态属性可以被修饰为 static final transient volatile，这样就不能被修改了，也不能被重写，也不能被序列化，也不能被缓存。
 *  - volatile 用于标记JVM和线程从主内存读取其值，而不是利用线程栈中的缓存值。它被用于java的并发编程中。
 *  - transient关键字与实例变量一起使用，以在序列化过程中消除它。
 */
public class SubClassStaticVarMain {
    static volatile String prop1 = "testProp1";
    @Test
    public void test1() {
        SubClassStaticVarChild1 objC = new SubClassStaticVarChild1();

        System.out.println(objC.getStaticVar());
    }
}
