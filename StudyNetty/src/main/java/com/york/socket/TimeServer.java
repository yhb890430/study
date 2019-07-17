package com.york.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
            server = new ServerSocket(port);
            System.out.println("The TimeServer is start in port :" + port);
//            while(Boolean.TRUE){
                // 接收来自客户端的连接，如果没有新的连接，那么主线程就阻塞在accpet()方法这
                Socket socket = server.accept();
                System.out.println("Remote Client Port :" + ((InetSocketAddress)socket.getRemoteSocketAddress()).getPort());
                // 每接收一个新连接就创建一个新的线程去处理
//                new Thread(new TimeServerHandler(socket)).start();
                BufferedReader reader = null;
                PrintWriter writer = null;
                try{
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                    String body = "";
                    String currentTime = "";
                    StringBuilder sb = new StringBuilder();
                    body=reader.readLine();
//                    while(Boolean.TRUE){
//                    while((body=reader.readLine()) != null){
//                        sb.append(body);
//                    }
//                        if(body == null){
//                            break;
//                        }
                        System.out.println("RECEIVE CONTENT FROM CLIENT:" + body);
                        currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAR ORDER";
                        // 自带flush操作
//                writer.println(currentTime);
                        writer.write(currentTime);
                        writer.flush();
//                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    // 这样写有问题，如果writer.close()处出现异常，那么reader.close方法将不会执行，此时io资源未关闭
//            try {
//                if(writer != null){
//                    writer.close();
//                }
//                if(reader != null){
//                    reader.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
                    if(writer != null){
                        writer.close();
                    }
                    try {
                        if(reader != null){
                            reader.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        if(socket != null){
                            socket.close();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
//            }

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
