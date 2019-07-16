package com.york.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

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
        InputStream inputStream = null;
        try{
            inputStream = socket.getInputStream();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
