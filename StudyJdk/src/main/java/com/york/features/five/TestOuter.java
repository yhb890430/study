package com.york.features.five;

import com.york.features.inner.Outer;

/**
 * @description: 外部类测试
 * @author: york
 * @date: 2019-11-27 10:40
 * @version: <1.0>
 */
public class TestOuter extends Outer{

    public static void main(String[] args) {
        // 访问不了其他包使用缺省访问修饰符修饰的外部类

        Outer.Inner inner = new Outer().new Inner();
        // Inner2和Inner3均访问不到
//        Inner2 inner2 = new Outer().new Inner2();
//        Inner3 inner3 = new Outer().new Inner3();

        // 使用static关键字修饰的内部类，不需要通过外部类对象进行引用，可以直接引用
        new Outer.Inner5();

    }

}
