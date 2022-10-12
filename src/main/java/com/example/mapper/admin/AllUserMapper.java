package com.example.mapper.admin;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface AllUserMapper {

    //查询全部字段
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
