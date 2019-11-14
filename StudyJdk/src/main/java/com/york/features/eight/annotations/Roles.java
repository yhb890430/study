package com.york.features.eight.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: jdk1.8之前重复注解使用
 * @author: york
 * @date: 2019-11-14 14:58
 * @version: <1.0>
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Roles {
    Role[] value();
}
