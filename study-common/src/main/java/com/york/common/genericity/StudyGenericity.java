package com.york.common.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author York.yuan
 * @version <1.0>
 * @description 学习泛型实体类
 * @createdate 2019/6/19 17:53
 **/
public class StudyGenericity {

    public static void main(String[] args) {

        List<? super Fruit> list = new ArrayList<>();
        list.add(new Apple());
        Object object = list.get(0);

    }

}
