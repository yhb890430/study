package com.york.features.eight.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 1.8新特性重复注解
 * @author: york
 * @date: 2019-11-14 15:26
 * @version: <1.0>
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatRoles {

    RepeatRole[] value();

}
