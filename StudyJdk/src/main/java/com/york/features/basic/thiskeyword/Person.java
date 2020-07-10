package com.york.features.basic.thiskeyword;

/**
 * @description: this关键字测试
 * @author: york
 * @date: 2020-7-10 14:31
 * @version: <1.0>
 */
public class Person {

    private String name;

    private Integer age;

    private String sex;

    public Person() {

    }

    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public Person(String name,Integer age,String sex){
        // 调用当前类其他构造方法，必须当前构造方法的第一行，普通方法无法调用
        this(name,age);
        this.sex = sex;
    }

    public void eat(){
        // 代表当前类对象，可以访问当前类的方法和属性，创建内部类对象
        // 访问当前类的方法
        this.print();
        // 直接访问当前类的属性
        System.out.println("age: "+this.age);
        // 创建内部类对象（非静态内部类）
        System.out.println("House Size: " + this.new House(98.06D).size);
        System.out.println(new House());
        System.out.println(new Car());
    }

    public void print(){
        System.out.println("person eat......");
    }

    final class House{

        private Double size;

        public House() {
        }

        public House(Double size) {
            this.size = size;
        }
    }

    /**
     * 静态内部类
     */
    static class Car{
        private String brand;

        public Car() {
        }

        public Car(String brand) {
            this.brand = brand;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("tom",20,"male");
        person.eat();
        House house = new Person().new House();
        Car car = new Person.Car();
    }
}
