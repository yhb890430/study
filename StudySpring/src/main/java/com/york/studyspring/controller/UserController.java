package com.york.studyspring.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: york
 * @date: 2019-8-28 10:16
 * @version: <1.0>
 */
@Controller
@RequestMapping("user")
// 多例
// @Scope(value = "prototype")
public class UserController {

    @GetMapping
    @ResponseBody
    public String getUser(){
        System.out.println(this);
        System.out.println(Thread.currentThread());
        System.out.println(22222);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        });
        t.start();
        return "success";
    }

}
