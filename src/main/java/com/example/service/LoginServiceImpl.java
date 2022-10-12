package com.example.service;

import com.example.mapper.LoginMapper;
import com.example.pojo.User;
import com.example.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/13 20:03
 * @description:
 */

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    LoginMapper loginMapper;

    @Override
    public int updateToken(String token, String openId) {
        return loginMapper.updateToken(token, openId);
    }

    /**
     * 验证登录者
     * @param request
     * @return
     */
    @Override
    public Result checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            return Result.Fail("登录验证失效，请重新登录");
        }
        List<User> userList = loginMapper.queryUserByToken(token);
        if(userList == null || userList.size() == 0) {
            return Result.Fail("登录验证失效，请重新登录");
        }
        User user = (User) userList.get(0);
        System.out.println("checkToken===>" + user);
        if(user == null) {
            return Result.Fail("登录验证失效，请重新登录");
        }
        return Result.SUCCESS(user);
    }

    @Override
    public List<User> queryUserByToken(String token) {
        return loginMapper.queryUserByToken(token);
    }

}
