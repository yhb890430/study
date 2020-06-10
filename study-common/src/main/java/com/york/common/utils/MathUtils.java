package com.york.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @description: 数学工具类
 * @author: york
 * @date: 2020-5-14 15:16
 * @version: <1.0>
 */
public class MathUtils {

    /**
     * 四舍五入double
     *
     * @param val
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double roundDouble(double val, int scale, int roundingMode) {
        BigDecimal b = new BigDecimal(val);
        return b.setScale(scale, roundingMode).doubleValue();
    }

    /**
     * 四舍五入浮点型
     *
     * @param val
     * @param scale
     * @param roundingMode
     * @return
     */
    public static float roundFloat(float val, int scale, int roundingMode) {
        BigDecimal b = new BigDecimal(val);
        return b.setScale(scale, roundingMode).floatValue();
    }

    /**
     * 单精度浮点型转双精度浮点型
     *
     * @param value
     * @return
     */
    public static Double float2Double(float value) {
        Double val = Double.valueOf(String.valueOf(value));
        return val;
    }

    /**
     * 16进制字符串转浮点数
     *
     * @param hexStr
     * @return
     */
    public static float hexStr2Float(String hexStr) {
        BigInteger bigInteger = new BigInteger(hexStr, 16);
        return Float.intBitsToFloat(bigInteger.intValue());
    }

    /**
     * 浮点数转16进制字符串
     *
     * @param f
     * @return
     */
    public static String float2Hex(float f) {
        int i = Float.floatToIntBits(f);
        return Integer.toHexString(i);
    }

    /**
     * 字节数组转短整形(限制两个字节)
     *
     * @param bytes
     * @return
     */
    public static short bytes2Short(byte[] bytes) {
        short s = 0;
        short s0 = (short) (bytes[0] & 0xff);
        short s1 = (short) (bytes[1] & 0xff);
        s0 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * 字节数组转整形(限制4个字节)
     *
     * @param bytes
     * @return
     */
    public static Integer bytes2Int(byte[] bytes) {
        int num = 0;
        for (int i = 0; i < bytes.length; i++) {
            num += (bytes[i] & 0xff) << (24 - 8 * i);
        }
        return num;
    }

    /**
     * 字节数组转长整形(限制8个字节)
     *
     * @param bytes
     * @return
     */
    public static long bytes2Long(byte[] bytes) {
        return (0xff00000000000000L & ((long) bytes[0] << 56))
                | (0x00ff000000000000L & ((long) bytes[1] << 48))
                | (0x0000ff0000000000L & ((long) bytes[2] << 40))
                | (0x000000ff00000000L & ((long) bytes[3] << 32))
                | (0x00000000ff000000L & ((long) bytes[4] << 24))
                | (0x0000000000ff0000L & ((long) bytes[5] << 16))
                | (0x000000000000ff00L & ((long) bytes[6] << 8))
                | (0x00000000000000ffL & (long) bytes[7]);
    }

    /**
     * 字节数组转单精度浮点型
     *
     * @param bytes
     * @return
     */
    public static float bytes2Float(byte[] bytes) {
        int bits = bytes[3] & 0xff | (bytes[2] & 0xff) << 8 | (bytes[1] & 0xff) << 16
                | (bytes[0] & 0xff) << 24;
        int sign = ((bits & 0x80000000) == 0) ? 1 : -1;
        int exponent = ((bits & 0x7f800000) >> 23);
        int mantissa = (bits & 0x007fffff);
        mantissa |= 0x00800000;
        float f = (float) (sign * mantissa * Math.pow(2, exponent - 150));
        return f;
    }

    /**
     * 字节数组转双精度浮点型
     *
     * @param arr
     * @return
     */
    public static double bytes2Double(byte[] arr) {
        return Double.longBitsToDouble(bytes2Long(arr));
    }

    /**
     * 16进制字符串转双精度浮点型
     *
     * @param hexStr
     * @return
     */
    public static Double hexStr2Double(String hexStr) {
        if (hexStr == null || hexStr.trim().equals("")) {
            return null;
        }
        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            String subStr = hexStr.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes2Double(bytes);
    }

    /**
     * 16进制字符串转长整形
     *
     * @param hexStr
     * @return
     */
    public static Long hexStr2Long(String hexStr) {
        if (hexStr == null || hexStr.trim().equals("")) {
            return null;
        }
        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            String subStr = hexStr.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes2Long(bytes);
    }
}
