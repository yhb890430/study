package com.york.nio;

import java.nio.ByteBuffer;
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
            Integer port = 8080;
            if (args != null && args.length > 0) {
                try {
                    // main方法提供端口
                    port = Integer.parseInt(args[0]);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(port);
            // 启动服务端，并通过多路复用器不断接收和处理来自客户端的消息
            MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
            new Thread(timeServer,"Nio-TimeServer-Thread-001").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
