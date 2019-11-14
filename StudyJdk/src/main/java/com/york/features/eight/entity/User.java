package com.york.features.eight.entity;

import com.york.features.eight.annotations.Valid;

/**
 * @description: 用户实体类
 * @author: york
 * @date: 2019-11-14 15:04
 * @version: <1.0>
 */
public class User {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId( Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
