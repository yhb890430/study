package com.york.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author York.yuan
 * @description 字符串去除Emoji图片工具类
 * @createdate 2018/1/16 15:33
 * @version <1.0>
 **/
public class RegexUtil {

    /**
     * 校验URL正则表达式
     */
    private static final String URL_PATTERN = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+(\\\\?{0,1}(([A-Za-z0-9-~]+\\\\={0,1})([A-Za-z0-9-~]*)\\\\&{0,1})*)$";

    /**
     * 正则验证URL
     * @param url
     * @return
     */
    public static boolean verifyUrl(String url){
        Pattern pattern = Pattern.compile(URL_PATTERN);
        if(pattern.matcher(url).matches()){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 将普通字符串转为unicode字符串
     * @param str
     * @return
     */
    public static String convert(String str)
    {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++)
        {
            c = str.charAt(i);
            sb.append("\\u");
            //取出高8位
            j = (c >>>8);
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
            {
                sb.append("0");
            }
            sb.append(tmp);
            //取出低8位
            j = (c & 0xFF);
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
            {
                sb.append("0");
            }
            sb.append(tmp);

        }
        return (new String(sb).toUpperCase());
    }

    /**
     * unicode转成字符串，与上述过程反向操作即可
     * 将unicode 字符串
     * @param str 待转字符串
     * @return 普通字符串
     */
    public static String revert(String str)
    {
        str = (str == null ? "" : str);
        //如果不是unicode码则原样返回
        if(str.indexOf("\\u") == -1){
            return str;
        }

        StringBuffer sb = new StringBuffer(1000);

        for (int i = 0; i < str.length() - 6;)
        {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++)
            {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar)
                {
                    case 'a':
                        t = 10;
                        break;
                    case 'b':
                        t = 11;
                        break;
                    case 'c':
                        t = 12;
                        break;
                    case 'd':
                        t = 13;
                        break;
                    case 'e':
                        t = 14;
                        break;
                    case 'f':
                        t = 15;
                        break;
                    default:
                        t = tempChar - 48;
                        break;
                }

                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            sb.append((char) c);
            i = i + 6;
        }
        return sb.toString();
    }



}
