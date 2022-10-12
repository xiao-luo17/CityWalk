package com.example.controller;

import com.example.pojo.User;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.utils.GetUserInfoUtil;
import com.example.utils.Result;
import com.example.utils.UUIDUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/13 20:03
 * @description:
 */

@RestController
@RequestMapping("/index")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        //通过code换取信息
        String code = jsonObject.getString("code");

        //请求微信小程序官网
        JSONObject resultJson = GetUserInfoUtil.getResultJson(code);

        if (resultJson.containsKey("openid")){
            //获取sessionKey和openId
            String sessionKey = resultJson.get("session_key").toString();
            String openid = resultJson.get("openid").toString();

            //查询用户是否存在
            List<User> list = userService.queryUserList(openid);
            if (list.size() > 0){
                User user = list.get(0);
                //生成token
                String token = UUIDUtil.uuid();
                //把用户存到session中
//                request.getSession().setAttribute("token", user);
                loginService.updateToken(token, openid);
                return Result.SUCCESS("用户已存在，登录成功", token); //用户存在，返回结果
            }else {
                //用户不存在，新建用户信息
                int res = userService.insertUser(openid);
                if (res <= 0){
                    return Result.Fail("登录失败,插入数据库失败");
                }
                List<User> userList = userService.queryUserList(openid);
                User user = userList.get(0);
                //生成token
                String token = UUIDUtil.uuid();
                //把用户存到session中
//                request.getSession().setAttribute("token", user);
                loginService.updateToken(token, openid);
                return Result.SUCCESS("登录成功", token);
            }
        }

        return Result.Fail("登录失败，请求微信小程序官网");
    }


    /**
     * 用户授权自己的头像、昵称
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/authorization",method = RequestMethod.POST)
    public Result authorization(@RequestBody JSONObject jsonObject,HttpServletRequest request){
        String nickname = jsonObject.getString("nickname");
        String headshot = jsonObject.getString("headshot");

        Result result = loginService.checkToken(request);
        if(result.getCode() == 200) {
            User user = (User) result.getData();
            //登录时用户授权，更新用户头像和昵称
            int res = userService.updateUserWxInfo(user.getOpenId(), nickname, headshot);
            if(res != 0) {
                return Result.SUCCESS("授权成功");
            } else {
                return Result.Fail("授权失败");
            }
        } else {
            return Result.Fail("授权失败");
        }
    }


    /**
     * 用于测试
     * @param
     * @return
     */
    @RequestMapping(value = "/checkToken",method = RequestMethod.POST)
    public Result checkToken(HttpServletRequest request) {
        return loginService.checkToken(request);
    }
}
