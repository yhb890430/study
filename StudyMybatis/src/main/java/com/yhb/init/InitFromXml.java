package com.yhb.init;

import com.yhb.entity.User;
import com.yhb.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author York.yuan
 * @version <1.0>
 * @description 从XML初始化Myabtis
 * @createdate 2019/5/10 16:30
 **/
public class InitFromXml {

    public static void init(){
        String resource = "props/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);
            List<User> userList = userMapper.queryUser();
            for (User user : userList) {
                System.out.println(user.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        init();
    }

}
