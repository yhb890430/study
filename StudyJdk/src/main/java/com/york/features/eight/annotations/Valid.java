package com.york.features.eight.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: jdk1.8新特性类型注解
 * TYPE_USE可以在任何用到类型的地方使用，比如定义类，类型转换，new 对象,方法形参，局部参数，全局变量等
 * @author: york
 * @date: 2019-11-14 15:53
 * @version: <1.0>
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Valid {

    String type();

}
