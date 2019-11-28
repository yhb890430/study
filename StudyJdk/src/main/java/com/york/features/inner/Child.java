package com.york.features.inner;

/**
 * @description:
 * @author: york
 * @date: 2019-11-28 15:37
 * @version: <1.0>
 */
public class Child extends Outer{


    public static void main(String[] args) {
        Inner2 inner2 = new Outer().new Inner2();
        Inner3 inner3 = new Outer().new Inner3();
    }

}
