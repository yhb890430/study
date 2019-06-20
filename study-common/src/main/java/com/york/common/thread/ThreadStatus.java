package com.york.common.thread;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/6/10 13:53
 **/
public class ThreadStatus extends Thread{


    private Object lock = new Object();

    private String name;

    public ThreadStatus(){

    }

    public ThreadStatus(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name+":获取锁之前");
//        synchronized (lock){
//            System.out.println(name+":获取锁之后");
//        }
    }

    public static void main(String[] args) {
        ThreadStatus t1 = new ThreadStatus("A");
        ThreadStatus t2 = new ThreadStatus("B");
        t1.start();
        t2.start();
        try {
            t1.wait(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
