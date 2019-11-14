package com.york.features.eight.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: TYPE_PARAMETER使用在自定义类型参数中(比如泛型)，并没有找到方式去做校验
 * @author: york
 * @date: 2019-11-14 16:56
 * @version: <1.0>
 */
@Target({ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeAnnotation {
    Class type();
}
