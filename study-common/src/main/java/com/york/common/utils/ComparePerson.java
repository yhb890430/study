package com.york.common.utils;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/6/19 16:57
 **/
public class ComparePerson<T extends Comparable<? super Person>> {

    private T a;

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        ComparePerson c = new ComparePerson();
        c.setA(new Student());
        Comparable a = c.getA();
    }
}
