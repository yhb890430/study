package com.yhb.mapper;

import com.yhb.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/5/10 16:20
 **/
@Mapper
public interface UserMapper {

    /**
     * 查询用户信息
     * @return
     */
    @Select("SELECT * FROM t_user")
//    @ResultType(value = User.class)
//    @Results
    List<User> queryUser();

}
