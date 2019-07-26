package com.york.webservice.jws.server.impl;

import com.york.webservice.jws.entity.User;
import com.york.webservice.jws.server.IHelloWebService;

import javax.jws.*;

/**
 * @description:
 * @author: york
 * @date: 2019/7/19 10:40
 * @version: <1.0>
 */
@WebService(name = "Hello",serviceName = "HelloService",targetNamespace = "http://york.test.com",portName = "HelloPort")
public class HelloWebService implements IHelloWebService {

    @WebMethod(operationName = "sayHello",action = "sayHello",exclude = false)
    @Oneway
    @Override
    public void sayHello(@WebParam(name = "message",partName = "String") String message) {
        System.out.println("Hello:" + message);
    }

    @WebMethod(operationName = "sayBye",action = "sayBye",exclude = true)
    @Override
    public void sayBye(String message) {
        System.out.println("Bye:" + message);
    }

    @WebMethod(operationName = "queryUser",action = "queryUser",exclude = false)
    @WebResult(name = "user",partName = "test",header = false)
    @Override
    public User queryUser(Integer id) {
        return new User(id,"张三",20,"male");
    }

    @WebMethod(operationName = "saveUser",action = "saveUser",exclude = false)
    @Override
    public String saveUser(@WebParam(name = "user",partName = "com.york.webservice.jws.entity.User") User user) {
        return "success";
    }

}
