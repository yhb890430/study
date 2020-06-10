package com.york.common.utils;

/**
 * @description: 16进制数工具类
 * @author: zq
 * @date: 2020-5-6 14:50
 * @version: <1.0>
 */
public class HexUtils {

    // 两个16进制数字符串只需要一个字节进行表示

    /**
     * 字节数组转16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 对用字符串表示的16进制数进行高低位互换(1个字节两个字符)，比如9A99DD41，转换后结果为41DD999A
     *
     * @param hexStr
     * @return
     */
    public static String exchangeHexStrHighLow(String hexStr) {
        // 以一个字节为单位进行高低位互换(1个字节两个字符)
        char[] charArray = hexStr.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int j = charArray.length - 1; j >= 0; j = j - 2) {
            sb.append(charArray[j - 1]).append(charArray[j]);
        }
        return sb.toString();
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
