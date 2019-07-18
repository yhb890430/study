package com.york.socket;

import java.io.*;
import java.net.Socket;

/**
 * @description: 时间客户端
 * @author: york
 * @date: 2019/7/17 12:50
 * @version: <1.0>
 */
public class TimeClient {

//    public static void main(String[] args) {
//        Socket socket = null;
//        BufferedReader reader = null;
//        PrintWriter writer = null;
//        String result = "";
//        try {
//            socket = new Socket("127.0.0.1",8088);
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//            writer.write("QUERY TIME ORDER");
//            writer.flush();
////            writer.println("QUERY TIME ORDER");
////            result = reader.readLine();
//            System.out.println("1111");
//            StringBuilder sb = new StringBuilder();
////            while(Boolean.TRUE){
//                result = reader.readLine();
////                if(result == null){
////                    break;
////                }
//                sb.append(result);
////            }
//            System.out.println(2222);
//            System.out.println("NOW TIME IS:" + sb.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(writer != null){
//                writer.close();
//            }
//            try {
//                if(reader != null){
//                    reader.close();
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            try {
//                if(socket != null){
//                    socket.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//    }


    public static void main(String[] args) {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        String result = "";
        try {
            socket = new Socket("127.0.0.1",8088);
            is =socket.getInputStream();
            os = socket.getOutputStream();
            System.out.println(is);
            System.out.println(os);
            os.write(new String("QUERY TIME ORDER").getBytes("UTF-8"));
            os.flush();

            System.out.println("1111");
            StringBuilder sb = new StringBuilder();
            byte[] arr = new byte[1024];
            while(is.read(arr) != -1){
                sb.append(new String(arr));
            }
            System.out.println(2222);
            System.out.println("NOW TIME IS:" + sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(4444);
            try {
                if(os != null){
                    os.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try {
                if(is != null){
                    is.close();
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
