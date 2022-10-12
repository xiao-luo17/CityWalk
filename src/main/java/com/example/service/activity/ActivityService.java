package com.example.service.activity;

import com.example.pojo.Activity;
import com.example.utils.Result;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:21
 * @description:
 */

public interface ActivityService {

    //添加点赞内容
    Result like(Map<String, Object> map);

    //根据openid查活动
    Activity queryActivityByOpenId(String publishUserOpenId);

    int insertActivity(Map<String, Object> map);

    Result comment(Map<String, Object> map);

    Result showComment(Map<String, Object> map);
}
