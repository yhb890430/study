package com.york.common.thread;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/6/13 9:35
 **/
public class Test {

    public static String test(String s,int length){
        if(length <= 0){
            return "";
        }
        char[] arr = s.toCharArray();
        for (char c : arr) {
            System.out.println(c);
            Character character = new Character(c);
        }

        return "";
    }

    public static void test1(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D://1.txt")));
            String s = "";
            Map<String,Integer> map = new HashMap<String,Integer>();
            while((s = reader.readLine()) != null){
                String[] arr = s.split(",");
                if(map.containsKey(arr[1])){
                    map.put(arr[1],map.get(arr[1])+1);
                }else{
                    map.put(arr[1],1);
                }
            }
            Integer a = new Integer(1);
            List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
                @Override
                public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                    int i = o2.getValue().compareTo(o1.getValue());
//                    System.out.printf("o1=%d,o2=%d,i=%d\n",o1.getValue(),o2.getValue(),i);
                    return i;
                }
            });
            Map<String,Integer> map1 = new LinkedHashMap();
            for (Map.Entry<String, Integer> entry : list) {
                map1.put(entry.getKey(),entry.getValue());
            }
            for (String key : map1.keySet()) {
                System.out.printf("key=%s,value=%d\n",key,map1.get(key));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
//        Test.test("你好Abcd",10);
//        try {
//            String s = "你好Abcd";
//            System.out.println(s.getBytes().length);
//            System.out.println(s.getBytes("unicode").length);
////            System.out.println(s.getBytes("UTF-8").length);
////            System.out.println(s.getBytes("GBK").length);
//            Properties properties = System.getProperties();
//            for (Object o : properties.keySet()) {
//                System.out.println((String)o+":"+properties.getProperty((String)o));
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Test.test1();
    }
}
