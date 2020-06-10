package com.york.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 日期工具类
 * @author: york
 * @date: 2020-6-1 13:10
 * @version: <1.0>
 */
public class DateUtils {

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public final static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 短日期
     */
    public final static String RORMAT_SHORT_NO_DASH = "yyyyMMdd";

    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public final static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public final static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 小时分钟秒
     */
    public final static String FORMAT_HOUR = "HH:mm:ss";

    /**
     * 年-月-日 时
     */
    public static String FORMAT_HOUR_V2 = "yyyy-MM-dd HH";


    /**
     * 格式化时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        pattern = (pattern == null || pattern.isEmpty() || pattern.trim().isEmpty()) ? FORMAT_LONG : pattern;
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 反格式化时间
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, String pattern) throws ParseException {
        if ((date == null || date.isEmpty() || date.trim().isEmpty())) {
            return null;
        }
        pattern = (pattern == null || pattern.isEmpty() || pattern.trim().isEmpty()) ? FORMAT_LONG : pattern;
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.parse(date);
    }
}
