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
    // 枚举可以有全局属性，构造方法，但都必须定义在枚举成员之后，且此时枚举成员需要以分号结尾

    // 枚举成员
    YORK(20),HELEN,JACK,TOM,SMITH;

    // 全局属性
    private Integer age;

    // 无参构造方法
    MyEnum(){
    }

    // 有参构造方法
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
        MyEnum myEnum = MyEnum.HELEN;
        System.out.println(myEnum.age);
        String name = "";
        switch (name){
            case "helen":
                break;
            default:
                break;
        }
        switch (myEnum){
            case HELEN:


        }
    }

}
