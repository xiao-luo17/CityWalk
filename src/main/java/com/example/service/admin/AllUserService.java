package com.example.service.admin;

import com.example.pojo.User;

import java.util.Collection;

public interface AllUserService {

    Collection<User> getAll();

    int addUser(int userId, String nameplateNumber, String headshot, String nickname, String email,
                 String phone, String sex, String ipTerritory, String birthday, String area, String school,
                 String tagWords, String slogan, String userPermissions, String able, String token, String openId);

    int deleteUser(String userId);

    int update(String userId, String nameplateNumber, String headshot, String nickname, String email,
               String phone, String sex, String ipTerritory, String birthday, String area, String school,
               String tagWords, String slogan, String userPermissions, String able, String token, String openId);

    User queryUserById(String userId);
}
