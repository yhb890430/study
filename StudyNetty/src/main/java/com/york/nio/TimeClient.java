package com.york.nio;

/**
 * @description: 时间客户端
 * @author: york
 * @date: 2019-8-14 13:49
 * @version: <1.0>
 */
public class TimeClient {

    public static void main(String[] args){
        Integer port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1",port),"Nio-TimeClient-Start").start();

    }

}
