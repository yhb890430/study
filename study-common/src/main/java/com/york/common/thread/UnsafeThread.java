package com.york.common.thread;

import com.york.common.annotations.UnSafeThread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/5/31 17:35
 **/
@UnSafeThread
public  class UnsafeThread implements Runnable{

    /**
     * 计数器
     */
    private int count = 0;

    /**
     * 非原子性操作（非线程安全）
     */
    @Override
    @UnSafeThread
    public void run() {
        count++;
        System.out.println(count);
    }

//    private AtomicInteger COUNT = new AtomicInteger(0);
//
//    /**
//     * 线程安全
//     */
//    @Override
//    public void run() {
//        COUNT.getAndIncrement();
//        System.out.println(COUNT.intValue());
//    }
}
