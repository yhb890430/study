package com.york.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: 阻塞io创建的TimeServer
 * @author: york
 * @date: 2019/7/16 13:00
 * @version: <1.0>
 */
public class TimeServer {

    public static void main(String[] args) throws IOException{
        Integer port = 8088;
        if(args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        ServerSocket server = null;
        try {
            // 指定服务端监听端口
//            InetAddress[] baidu = InetAddress.getAllByName("www.baidu.com");
//            for (InetAddress inetAddress : baidu) {
//                System.out.println(inetAddress.getHostAddress());
//            }
            server = new ServerSocket(port,100);
            System.out.println("The TimeServer is start in port :" + port);
            while(Boolean.TRUE){
                // 接收来自客户端的连接，如果没有新的连接，那么主线程就阻塞在accpet()方法这
                Socket socket = server.accept();
                // 每接收一个新连接就创建一个新的线程去处理
                new Thread(new TimeServerHandler(socket)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(server != null){
                server.close();
                server = null;
            }
        }
    }


}
