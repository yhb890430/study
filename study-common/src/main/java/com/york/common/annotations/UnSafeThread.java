package com.york.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author York.yuan
 * @version <1.0>
 * @description 非线程安全注解
 * @createdate 2019/6/6 14:31
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface UnSafeThread {
}
