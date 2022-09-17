package com.example.service.admin;

import com.example.mapper.admin.AllUserMapper;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AllUserServiceImpl implements AllUserService{

    @Autowired
    private AllUserMapper allUserMapper;

    @Override
    public Collection<User> getAll() {
        return allUserMapper.getAll();
    }

    @Override
    public int addUser(String userId, String nameplateNumber, String headshot, String nickname, String email,
                        String phone, String sex, String ipTerritory, String birthday, String area, String school,
                        String tagWords, String slogan, String userPermissions, String able, String token, String openId) {
        return allUserMapper.addUser(userId,nameplateNumber,headshot,nickname,email,phone,sex,ipTerritory,birthday,area,school,tagWords,slogan,userPermissions,able,token,openId);
    }
}