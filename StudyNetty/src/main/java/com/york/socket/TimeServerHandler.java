package com.york.socket;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * @description: 时间服务器处理线程
 * @author: york
 * @date: 2019/7/16 13:39
 * @version: <1.0>
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;


    public TimeServerHandler() {
    }

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String body = "";
            String currentTime = "";
            while(Boolean.TRUE){
                body = reader.readLine();
                if(body == null){
                    break;
                }
                System.out.println("RECEIVE CONTENT FROM CLIENT:" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAR ORDER";
                // 自带flush操作
//                writer.println(currentTime);
                writer.write(currentTime);
                writer.flush();
            }
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

    }

}
