package com.york.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @description: NIO编写的时间服务端
 * @author: york
 * @date: 2019/7/29 13:24
 * @version: <1.0>
 */
public class TimeServer {

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            System.out.println(1<<0);// 1
            System.out.println(1<<2);// 4
            System.out.println(1<<3);// 8
            System.out.println(1<<4);// 16

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
