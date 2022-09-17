package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.CookieUtil;
import com.example.utils.GetUserInfoUtil;
import com.example.utils.Result;
import com.example.utils.UUIDUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUserList",method = RequestMethod.POST)
    public Result queryUserList(@RequestBody String openid){
        try{
            JSONObject object = JSONObject.fromObject(openid);
            String openId = (String) object.get("openid");
            return Result.SUCCESS(userService.queryUserList(openId));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 插入用户，主要用于测试，前端直接调login
     * @param openid
     * @return
     */
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public Result insertUser(@RequestBody String openid){

        try{
            JSONObject object = JSONObject.fromObject(openid);
            String openId = (String) object.get("openid");
            int res = userService.insertUser(openId);
            if(res == 0) return Result.Fail("注册失败");
            else return Result.SUCCESS("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 授权时更新用户，主要用于测试，前端直接调login
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/updateUserWxInfo",method = RequestMethod.POST)
    public Result updateUserWxInfo(@RequestBody JSONObject jsonObject){

        try{
            String openId = jsonObject.getString("openid");
            String nickname = jsonObject.getString("nickname");
            String headshot = jsonObject.getString("headshot");
            int res = userService.updateUserWxInfo(openId, nickname, headshot);
            if(res == 0) return Result.Fail("用户授权失败！");
            else return Result.SUCCESS("授权成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 根据该用户openid和传入的表单修改其对应字段（“未做空校验”）
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public Result updateUserInfo(@RequestBody String userInfo) {

        try {
            int res = userService.updateUserInfo(userInfo);
            if (res == 0) return Result.Fail("数据修改失败！");
            else return Result.SUCCESS("数据更新成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }

    }
}
