package com.york.webservice.jws.server.impl;

import com.york.webservice.jws.entity.User;
import com.york.webservice.jws.server.IHelloWebService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebEndpoint;

/**
 * @description:
 * @author: york
 * @date: 2019/7/19 10:40
 * @version: <1.0>
 */
@WebService(name = "ceshiserver",serviceName = "",targetNamespace = "",portName = "")
public class HelloWebService implements IHelloWebService {

    @WebMethod(operationName = "sayHello",action = "sayHello",exclude = false)
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
    @WebResult(name = "user",partName = "test",targetNamespace = "com.york",header = false)
    @Override
    public User queryUser(Integer id) {
        return new User(id,"张三",20,"male");
    }


}
