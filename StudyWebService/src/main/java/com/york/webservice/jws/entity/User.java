package com.york.webservice.jws.entity;

import java.io.Serializable;

/**
 * @description: 用户实体类
 * @author: york
 * @date: 2019/7/19 10:54
 * @version: <1.0>
 */
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    public User() {
    }

    public User(Integer id, String name, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
