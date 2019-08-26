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
 * @description: 时间客户端处理类
 * @author: york
 * @date: 2019-8-14 13:49
 * @version: <1.0>
 */
public class TimeClientHandle implements Runnable{

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean stop;

    private String host;

    private Integer port;

    public void stop(){
        this.stop = true;
    }

    /**
     * 初始化客户端
     * @param host
     * @param port
     */
    public TimeClientHandle(String host, Integer port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            selector = Selector.open();
            // 创建客户端
            socketChannel = SocketChannel.open();
            // 设置客户端为非阻塞
            socketChannel.configureBlocking(Boolean.FALSE);
            socketChannel.socket().setReuseAddress(Boolean.TRUE);
        } catch (Exception e){
            e.printStackTrace();
            // 初始化失败则退出程序
            System.exit(1);
        }
    }

    public TimeClientHandle() {
    }

    @Override
    public void run() {
        try {
            // 与服务端建立连接
            doConnect();
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        while(!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e){
                        e.printStackTrace();
                        // 某个客户端连接处理异常时，关闭改客户端连接
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
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
        if(key.isValid()) {
            SocketChannel channel = (SocketChannel) key.channel();
            if(key.isConnectable()){
                // 完成与服务端的连接
                if(channel.finishConnect()){
                    // 注册读事件
                    channel.register(selector,SelectionKey.OP_READ);
                    // 发送查询命令
                    doWrite(channel);
                }else{
                    // 连接服务端失败，结束程序
                    System.exit(1);
                }
            }

            if(key.isReadable()){
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(byteBuffer);
                if(read > 0){
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String result = new String(bytes,"UTF-8");
                    System.out.println("Now is : " + result);
                    stop();
                }else if(read < 0){
                    key.cancel();
                    key.channel().close();
                }else{
                    //
                }
            }
        }
    }

    /**
     * 与服务端建立连接
     * @throws IOException
     */
    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            // 连接成功，注册至多路复用器并监听读操作,准备接收来自服务端的消息
            socketChannel.register(selector, SelectionKey.OP_READ);
        }else{
            // 连接失败，注册至多路复用器并监听连接操作，由多路复用器进行重连
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 发送内容到客户端
     * @param sc
     * @throws IOException
     */
    private void doWrite(SocketChannel sc) throws IOException{
        String order = "";
        byte[] bytes = order.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        sc.write(byteBuffer);
        // TODO 用于判断是否全部发送出去，可能会出现写半包问题，后续处理
        if(!byteBuffer.hasRemaining()){
            System.out.println("Send order to server succeed.");
        }
    }

}
