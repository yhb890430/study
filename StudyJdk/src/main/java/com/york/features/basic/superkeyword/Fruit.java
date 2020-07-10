package com.york.features.basic.superkeyword;

/**
 * @description: 父类
 * @author: york
 * @date: 2020-7-10 12:30
 * @version: <1.0>
 */
public class Fruit {

    private String name;

    String country;

    protected Double price;

    public String weight;

    public Fruit() {
    }

    public Fruit(String name,Double price) {
        this.name = name;
        this.price = price;
    }

    public void printInfo(){
        System.out.println("father print..........");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}
