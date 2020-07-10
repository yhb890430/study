package com.york.features.basic.superkeyword;

/**
 * @description: 子类
 * @author: york
 * @date: 2020-7-10 12:32
 * @version: <1.0>
 */
public class Orange extends Fruit {

    private String color;

    public Orange(){
        super();
    }

    public Orange(String color) {
        // 调用父类构造方法，必须放在子类或者内部类(子类)构造方法的第一行
        // 原因：因为父类构造方法没有执行之前父类的属性和方法还没有被创建，则子类不能继承父类的属性和方法，因此子类的构造方法不能被执行
        super("orange",5.8D);
        this.color = color;
    }

    public void print(){
        // 在子类内部代表父类对象，从而可以访问父类方法和属性，此时代码位置没有限制
        // 访问父类的方法
        super.printInfo();
        // 访问父类的属性，如父类属性访问修饰符为非private,则可直接访问
        super.getName();
        System.out.println(super.price);
        System.out.println("child print..........");

    }

    public static void main(String[] args) {
        Orange orange = new Orange("red");
        orange.print();
    }

}
