package com.york.webservice.jws.server.impl;

import com.york.webservice.jws.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @description:
 * @author: york
 * @date: 2019/7/19 11:38
 * @version: <1.0>
 */
@WebService(name="User",serviceName = "UserWebService",portName = "UserPort",targetNamespace = "http://user.york.com")
public class UserWebService {

    @WebMethod(operationName = "saveUser")
    public void saveUser(User user) {
        System.out.println("user saved");
    }
}
