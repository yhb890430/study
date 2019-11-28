package com.york.features.inner;

/**
 * @description: 外部类和内部类(访问修饰符详解)
 * @author: york
 * @date: 2019-11-27 10:19
 * @version: <1.0>
 */
public class Outer {
    // 修饰符详解：https://www.cnblogs.com/yuechuan/p/9004461.html

    // 外部类
    // 访问修饰符:public或者没有访问修饰符(此时等价于加了protected访问修饰符)，不能使用private和protected访问修饰符

    private String name;

    private String print() {
        System.out.println("print.......");
        return "success";
    }

    // 内部类
    // 访问修饰符
    // public 修饰内部类，其他包的类可以访问和引用(非子类和子类)
    // protected 当前包下的类可以访问和引用(非子类和子类)
    // 缺省修饰符 当前包下的类可以访问和引用(非子类和子类)
    // private 子类及当前类访问
    // 非访问修饰符
    // static 非静态内部类不能有静态方法和静态成员，静态内部类可以拥有静态方法和静态成员
    // (1) 与public 搭配 可以通过new Outer.Inner5()方式或者new Inner5();
    // (2) protected或者不加，通过new Outer.Inner6()
    // (3) private 搭配,只能内部或者子类使用

    public class Inner {

        private String age;

        public String innerPrint(){
            // 内部类可以直接访问外部类的属性和方法
            System.out.println(name);
            print();
            return "success";
        }

    }

    protected class Inner2 {

    }

    class Inner3{

    }

    private class Inner4{

    }

    public static class Inner5{
        private static Integer age;

        public static void printInner(){
            System.out.println(age);
        }
    }

    protected static class Inner6{

    }

    private static class Inner7{

    }

    public class Inner8 extends Inner{

    }
}
