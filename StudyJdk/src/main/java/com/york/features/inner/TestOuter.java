package com.york.features.inner;

/**
 * @description:
 * @author: york
 * @date: 2019-11-27 10:41
 * @version: <1.0>
 */
public class TestOuter extends NoAccessModifierOuter{

    // 当外部类没有任何访问修饰符时，只能被同一个包的其他类引用或者继承

    public static void main(String[] args) {
        NoAccessModifierOuter outer = new NoAccessModifierOuter();

        new Outer().new Inner2();
        new Outer.Inner6();
    }

}
