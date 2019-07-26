package com.york.webservice.jws.server;

import com.york.webservice.jws.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @description:
 * @author: york
 * @date: 2019/7/19 10:36
 * @version: <1.0>
 */
@WebService
public interface IHelloWebService {

    void sayHello(String message);

    void sayBye(String message);

    User queryUser(Integer id);

    String saveUser(User user);
}
