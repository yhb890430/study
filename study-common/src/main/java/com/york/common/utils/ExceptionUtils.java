package com.york.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zq
 * @description 异常信息获取工具类
 * @create 2017/9/22 19:06
 **/
public class ExceptionUtils {

    /**
     * 获取异常的完整控制台输出信息
     *
     * @param e
     */
    public static String exceptionMessage(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        return str;
    }

}
