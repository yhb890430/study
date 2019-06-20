package com.yhb.entity;

import org.apache.ibatis.type.Alias;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/5/10 16:21
 **/
@Alias(value = "User")
public class User {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

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
}
