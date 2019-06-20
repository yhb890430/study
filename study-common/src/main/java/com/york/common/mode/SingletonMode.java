package com.york.common.mode;

import com.york.common.thread.UnsafeThread;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author York.yuan
 * @version <1.0>
 * @description 单例模式
 * @createdate 2019/6/5 12:54
 **/
public class SingletonMode {
    //参考文献:https://www.cnblogs.com/zhaosq/p/10135362.html

    /**
     *  饿汉式-静态常量(即预先初始化好,不管使用与否)
     *  优点:不会出现线程安全问题
     *  缺点:不是延迟加载,如果没有使用该对象会造成资源浪费
     */
//    private static final SingletonMode SINGLETON_MODE = new SingletonMode();
//
//    private SingletonMode(){
//
//    }
//
//    public static SingletonMode getInstance(){
//        return SINGLETON_MODE;
//    }

    /**
     *  饿汉式-静态代码块(即预先初始化好,不管使用与否)
     *  优点:不会出现线程安全问题
     *  缺点:如果没有使用该对象会造成资源浪费，
     */
//    private static SingletonMode SINGLETON_MODE;
//
//    static {
//        SINGLETON_MODE = new SingletonMode();
//    }
//
//    private SingletonMode(){
//
//    }
//
//    public static SingletonMode getInstance(){
//        return SINGLETON_MODE;
//    }

    /**
     * 懒汉式--线程不安全(不可用)
     * 缺点:线程不安全
     */
//    private static SingletonMode SINGLETON_MODE;
//
//    private SingletonMode(){
//
//    }
//
//    public static SingletonMode getInstance(){
//        if(SINGLETON_MODE == null){
//            SINGLETON_MODE = new SingletonMode();
//        }
//        return SINGLETON_MODE;
//    }


    /**
     * 懒汉式-线程安全-同步方法(可用)
     * 优点:线程安全，不浪费资源
     * 缺点:性能低(多个线程同时初始化时都需要获锁)
     */
//    private static SingletonMode SINGLETON_MODE;
//
//    private SingletonMode(){
//
//    }
//
//    public synchronized static SingletonMode getInstance(){
//        if(SINGLETON_MODE == null){
//            SINGLETON_MODE = new SingletonMode();
//        }
//        return SINGLETON_MODE;
//    }

    /**
     * 懒汉式-线程安全-同步代码块(可用)
     * 优点:线程安全，不浪费资源
     * 缺点:性能低(每次初始化时都需要获锁)
     */
//    private static SingletonMode SINGLETON_MODE;
//
//    private SingletonMode(){
//
//    }
//
//    public static SingletonMode getInstance(){
//        if(SINGLETON_MODE == null){
//            synchronized (SingletonMode.class){
//                SINGLETON_MODE = new SingletonMode();
//            }
//        }
//        return SINGLETON_MODE;
//    }

    /**
     * 静态内部类(可用)
     * 优点:线程安全，不浪费资源
     */
    private SingletonMode(){
    }

    private static class InitInstance{
        private static final SingletonMode SINGLETON_MODE = new SingletonMode();
    }

    public static SingletonMode getInstance(){
        return InitInstance.SINGLETON_MODE;
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10000, 10000, 20000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        UnsafeThread t = new UnsafeThread();
        long begin = System.currentTimeMillis();
        for(int j = 0;j<10000;j++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    SingletonMode instance = SingletonMode.getInstance();
                    System.out.println(instance);
                }
            });
        }
        threadPoolExecutor.shutdown();
        long end = System.currentTimeMillis();
        System.out.printf("花费时间:%d ms",(end-begin));
        // 饿汉式-静态常量 耗时:1602 ms
        // 懒汉式-线程安全-同步代码块 耗时:2027 ms
        // 懒汉式-线程安全-同步方法 耗时:1885 ms
        // 静态内部类 花费时间:1687
    }
}
