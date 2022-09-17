package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    //查全部
    List<User> queryUserList(String openId);

    //用户注册
    int insertUser(String openId);

    //初次授权时获取微信小程序获取的信息
    int updateUserWxInfo(String openId, String nickname, @Param("headshot") String headshot);

    //根据该用户openid和传入的表单修改其对应字段（“未做空校验”）
    int updateUserInfo(String userId, String nameplateNumber, String headshot, String nickname, String email,
                        String phone, String sex, String ipTerritory, String birthday, String area, String school,
                        String tagWords, String slogan, String userPermissions, String able, String token, String openId);
}