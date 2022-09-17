package com.example.service;

import com.example.pojo.User;
import com.example.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/13 20:02
 * @description:
 */
public interface LoginService {

    //    更新登陆token
    int updateToken(String token,  String openId);

    //验证登录者
    Result checkToken(HttpServletRequest request);

    //根据token查询用户
    List<User> queryUserByToken(String token);
}
