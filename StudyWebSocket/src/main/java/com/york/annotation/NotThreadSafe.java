package com.york.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author York.yuan
 * @version <1.0>
 * @description 非线程安全注解,用于标记一个类或者一个方法或者一个字段是否为线程安全的
 * @createdate 2018/11/27 12:18
 **/
@Target({ElementType.METHOD, ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
}
