package com.york.features.eight.controller;

import com.york.features.eight.annotations.*;
import com.york.features.eight.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 用户控制器类
 * @author: york
 * @date: 2019-11-14 15:03
 * @version: <1.0>
 */
@Valid(type="Serializable")
public class UserController<@TypeAnnotation(type = Object.class)T> implements Serializable {

    // 重复注解:指一个注解在同一个目标上使用多次

    // 全局变量使用
    private @Valid(type="String") String name;

    /**
     * jdk1.8 之前通过容器(数组方式)实现重复注解
     * @param user
     * @return
     */
    @Roles({@Role("admin"),@Role("add")})
    public String addUser(User user){
        System.out.println("add user ......");
        return  "SUCCESS";
    }

    /**
     * jdk1.8 重复注解实现方式
     * spring 4 重复注解已使用@Repeatable重新实现
     * @param userId
     * @return
     */
    @RepeatRole("admin")
    @RepeatRole("del")
    public String delUser( Integer userId){
        System.out.println("del user ......");
        return  "SUCCESS";
    }

    /**
     * 类型注解使用简单示例，但是并没有具体的实现方式
     * @param userName
     * @return
     */
    public String queryUser(@Valid(type="String") String userName){
        System.out.println("del user ......");
        // 新建对象
        User user = new @Valid(type="User") User();
        // 类型强制转换
        Object o = "test";
        String str = (@Valid(type="String")String)o;
        return "SUCCESS";
    }

    public T queryUser(List<T> list){
        System.out.println("del user ......");
        return null;
    }
}
