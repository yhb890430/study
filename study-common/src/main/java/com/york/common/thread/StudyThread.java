package com.york.common.thread;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author York.yuan
 * @version <1.0>
 * @description 学习线程安全的总类
 * @createdate 2019/6/4 18:00
 **/
@Resource
public class StudyThread{

    //参考文章：https://www.cnblogs.com/qifengshi/p/6831055.html
    //https://blog.csdn.net/lixin2151408/article/details/86493575

    // 线程安全特性
    //1、原子性 即代码块是原子操作
    //2、可见性 当多个线程修改同一变量(也叫域或者状态)时，一个线程修改成功后需立即同步到主存中，该修改对其他线程立即可见
    //3、有序性 禁止读取共享变量后的线程、修改共享变量前的线程执行期间发生重排序。

    // 竞争条件,当程序执行结果的正确性需要依赖于运行时正确时序或者多个线程之间正确的交替就会产生竞争条件
    // 最常见的一种竞争条件就是检查再运行
    // 检查再运行的常见使用场景在于单例模式的懒汉式(惰性初始化)

    /**
     * 举例：非原子性操作
     * 程序目标:将计数器累计10000
     * 结果:并不是每次执行最终结果都会达到10000
     */
    public static void test1(){
        // 多个线程在同时修改同一个对象中同一个变量,且count++的这个操作分为三个,读取 count 的值、读取的值加1、修改 count 的值
        // 当两个线程在同时执行的时候由于时间片的问题,导致线程1改的值还没有完全同步到主存中，而此时线程2还取的是原来的值
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10000, 10000, 20000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        UnsafeThread t = new UnsafeThread();

        for(int j = 0;j<10000;j++){
            threadPoolExecutor.execute(t);
        }
        threadPoolExecutor.shutdown();

    }

    public static void main(String[] args) {
        StudyThread.test1();
    }

}
