package com.york;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2018/11/27 10:05
 **/
public class TestSafeThread {


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 10000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        for(int i = 0;i<200;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection conn = null;
                    InputStream is = null;
                    try {
                        conn = (HttpURLConnection)new URL("http://localhost:8080/count").openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();
                        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                            is = conn.getInputStream();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if(is != null){
                            is.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(conn != null){
                            conn.disconnect();
                        }
                    }
                }
            });
        }
        executor.shutdown();
    }

}
