package com.example.service;

import com.example.pojo.User;

import java.util.List;

public interface UserService {
    //查全部
    List<User> queryUserList(String openId);

    //用户注册
    int insertUser(String openId);

    //初次授权时获取微信小程序获取的信息
    int updateUserWxInfo(String openId, String nickname, String headshot);

    //根据该用户openid和传入的表单修改其对应字段（“未做空校验”）
    int updateUserInfo(String userInfo);
}
