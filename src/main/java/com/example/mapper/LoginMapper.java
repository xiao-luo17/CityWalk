package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/13 19:55
 * @description:
 */

@Mapper
public interface LoginMapper {

//    更新登陆token
    int updateToken(@Param("token") String token, @Param("openId") String openId);

    //根据token查询用户
    List<User> queryUserByToken(String token);
}
