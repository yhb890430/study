package com.york;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2018/11/12 18:20
 **/
public class LogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        //Log4j2架构 http://logging.apache.org/log4j/2.x/manual/architecture.html
        //UML图类图 http://www.uml.org.cn/oobject/201610282.asp
        try{
            int a = 1/0;
        }catch (Exception e){
            LOGGER.info("111");
            LOGGER.error("LogTest/main",e);
            LOGGER.error("LogTest/main"+e);
        }
    }
}
