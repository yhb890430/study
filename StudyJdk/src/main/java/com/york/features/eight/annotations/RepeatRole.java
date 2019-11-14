package com.york.features.eight.annotations;

import java.lang.annotation.*;

/**
 * @description: 1.8新特性重复注解
 * @author: york
 * @date: 2019-11-14 15:25
 * @version: <1.0>
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// 通过
@Repeatable(RepeatRoles.class)
public @interface RepeatRole {

    String value();

}
