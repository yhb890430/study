package com.york.common.enums;

import java.io.Serializable;

/**
 * @description: 我的第一个枚举类
 * @author: york
 * @date: 2019/7/15 16:06
 * @version: <1.0>
 */
public enum MyEnum implements Serializable,Cloneable {

    // jdk1.5开始支持枚举
    // 枚举类不能继承(extend)，但是可以实现接口
    YORK(20),HELEN,JACK,TOM,SMITH;

    private Integer age;

    MyEnum(){
    }

    MyEnum(int age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Integer age = MyEnum.HELEN.age;
        System.out.println(age);
    }

}
