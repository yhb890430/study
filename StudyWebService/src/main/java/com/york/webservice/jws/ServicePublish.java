package com.york.webservice.jws;

import com.york.webservice.jws.server.impl.HelloWebService;
import com.york.webservice.jws.server.impl.UserWebService;

import javax.xml.ws.Endpoint;

/**
 * @description:
 * @author: york
 * @date: 2019/7/19 11:40
 * @version: <1.0>
 */
public class ServicePublish {

    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8088/HelloWebService",new HelloWebService());
//        Endpoint.publish("http://127.0.0.1:8088/UserWebService",new UserWebService());
    }
}
