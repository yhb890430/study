package com.york.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: york
 * @date: 2019-8-12 17:03
 * @version: <1.0>
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel server;

    private Integer port;

    /**
     * 用于停止服务端，所以需要保证内存可见性
     */
    private volatile Boolean stop;

    public MultiplexerTimeServer(Integer port) {
        try {
            // 初始化IO多路复用器(Java 采用epoll代替select)
            // 多路复用器可以通过轮询的方式不间断处理来自客户端请求
            selector = Selector.open();
            // 初始化通信通道(NIO中Channel为全双工通信,而旧IO中的InputStream和OutputStream都是单工通信)
            // 单工、半双工、全双工 https://blog.csdn.net/qq_38405680/article/details/83991481
            // ServerSocketChannel是抽象类，不可以直接实例化，通过open()方法获取实例
            server = ServerSocketChannel.open();
            // 下面两者有区别吗？
//            server.bind(new InetSocketAddress(port),1024);
            server.socket().bind(new InetSocketAddress(port),1024);
            // 设置为非阻塞模式
            server.configureBlocking(Boolean.FALSE);
            // 将通道注册到多路复用器，并监听OP_ACCEPT事件(相当于监听ServerSocket.accept()，监听来自客户端的连接)
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);
        } catch (Exception e){
            e.printStackTrace();
            // 初始化失败，退出程序,否则程序会报错
            System.exit(1);
        }
    }

    public MultiplexerTimeServer() {
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        // 通过不断轮询selector接受新客户端连接以及读取客户端内容以及
        while(!stop){
            try {
                // 选择准备好进行I/O操作通道对应的键，该操作为阻塞操作
                selector.select(1000);
                // 获取已经选择的键集，可从该键集中移除键，但不能添加键，否则会报错，
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keySet.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e){
                        e.printStackTrace();
                        if(key != null){
                            key.cancel();
                            // 关闭客户端连接
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }

            } catch (Throwable t){
                t.printStackTrace();
            }
        }
        // 关闭多路复用器时，将自动关闭注册在多路复用器上的Channel和Pipe,所以不需要重复释放资源
        // 放在此处关闭资源是有时序性的
        if(selector != null){
            try {
                selector.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            // 准备好接收来自客户端的请求
            if(key.isAcceptable()){
                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                SocketChannel client = server.accept();
                // 设置客户端非阻塞
                client.configureBlocking(false);
                // 将客户端注册到多路复用器上，并监听读事件，读取来自客户端发送的内容
                client.register(selector,SelectionKey.OP_READ);
            }
            // 准备好读取客户端的内容
            if(key.isReadable()){
                SocketChannel client = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                // 返回读取的字节数
                int readBytes = client.read(readBuffer);
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    // TODO 高并发下会出现TCP粘包的bug(后面会修复)
                    System.out.println("The time server receive order :" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    // 发送当前时间给客户端
                    // TODO 高并发下会出现写半包的bug
                    doWrite(client,currentTime);
                } else if(readBytes < 0){
                    // 字节数小于0，表示已经到达流的末尾，可关闭客户端连接
                    key.cancel();
                    client.close();
                } else {
                    // 等于0
                }
            }
        }
    }

    private void doWrite(SocketChannel sc,String response) throws IOException{
        if(response != null && response.length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            sc.write(byteBuffer);
        }
    }
}
