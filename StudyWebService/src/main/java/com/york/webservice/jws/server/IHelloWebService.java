package com.york.webservice.jws.server;

import com.york.webservice.jws.entity.User;

/**
 * @description:
 * @author: york
 * @date: 2019/7/19 10:36
 * @version: <1.0>
 */
public interface IHelloWebService {

    void sayHello(String message);

    void sayBye(String message);

    User queryUser(Integer id);
}
