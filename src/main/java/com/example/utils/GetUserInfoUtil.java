package com.example.utils;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

/**
 * @author: Richerlv
 * @date: 2022/9/11 14:13
 * @description:
 */

public class GetUserInfoUtil {
    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // appid
    public static final String WX_LOGIN_APPID = "";

    // 密匙
    public static final String WX_LOGIN_SECRET = "";

    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

    //通过code换取微信小程序官网获取的信息
    public static JSONObject getResultJson(String code){
        //配置请求参数
        Map<String,String> params = new HashMap<>();
        params.put("appid", WX_LOGIN_APPID);
        params.put("secret",WX_LOGIN_SECRET);
        params.put("js_code",code);
        params.put("grant_type",WX_LOGIN_GRANT_TYPE);

        //向微信服务器发送请求
        String wxRequestResult = HttpClientUtil.doGet(WX_LOGIN_URL,params);
        JSONObject resultJson = JSONObject.fromObject(wxRequestResult);

        return resultJson;
    }

    /**
     * controller层调用,获取openid
     * @param code
     * @return
     */
    public static String getOpenid(String code){
        return getResultJson(code).getString("openid");
    }

}
