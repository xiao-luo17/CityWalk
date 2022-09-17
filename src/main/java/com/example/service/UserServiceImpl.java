package com.example.service;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUserList(String openId) {
        List<User> userList = userMapper.queryUserList(openId);
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @Override
    public int insertUser(String openId) {
        return userMapper.insertUser(openId);
    }

    @Override
    public int updateUserWxInfo(String openId, String nickname, String headshot) {
        return userMapper.updateUserWxInfo(openId, nickname, headshot);
    }

    @Override
    public int updateUserInfo(String userInfo){
        //json解析
        JSONObject object = JSONObject.fromObject(userInfo);
        String openId = (String) object.get("openid");
        String userId = (String) object.get("userId");
        String nameplateNumber = (String) object.get("nameplateNumber");
        String headshot = (String) object.get("headshot");
        String nickname = (String) object.get("nickname");
        String email = (String) object.get("email");
        String phone = (String) object.get("phone");
        String sex = (String) object.get("sex");
        String ipTerritory = (String) object.get("ipTerritory");
        String birthday = (String) object.get("birthday");
        String area = (String) object.get("area");
        String school = (String) object.get("school");
        String tagWords = (String) object.get("tagWords");
        String slogan = (String) object.get("slogan");
        String userPermissions = (String) object.get("userPermissions");
        String able = (String) object.get("able");
        String token = (String) object.get("token");
        return userMapper.updateUserInfo(userId, nameplateNumber, headshot, nickname, email, phone, sex, ipTerritory,
                birthday, area, school, tagWords, slogan, userPermissions, able, token, openId);
    }
}
