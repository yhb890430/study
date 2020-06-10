package com.york.common.loop;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 循环
 * @author: york
 * @date: 2020-4-28 15:52
 * @version: <1.0>
 */
public class Loop {

    /**
     * 测试使用return语句跳出增强for循环,
     * 测试结果可以
     * @return
     */
    public Boolean testBrokeLoop(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer num : list) {
            System.out.println(num);
            if(num == 3){
                return true;
            }
        }
        return false;
    }

    /**
     * 测试foreach退出循环方式
     * 目前可以使用抛出异常的方式退出循环
     * @return
     */
    public Boolean testBrokeForEach(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.forEach(e->{
            System.out.println(e);
            if(e == 3){
                // 在foreach中不能使用return
//                return true;
                // 也不好用break退出循环
//                break;
                // 使用异常退出循环
                throw new RuntimeException("退出循环");
            }
        });
        return false;
    }

    public static void main(String[] args) {
        Loop loop = new Loop();
//        Boolean res = loop.testBrokeLoop();
        loop.testBrokeForEach();
    }

}
