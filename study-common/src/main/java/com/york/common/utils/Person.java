package com.york.common.utils;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/6/19 16:55
 **/
public class Person implements Comparable<Person> {

    private String name;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.getAge().compareTo(o.getAge());
    }
}
