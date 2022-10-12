package com.example.mapper.activity;

import com.example.pojo.Activity;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:19
 * @description:
 */

@Mapper
public interface ActivityMapper {

    //根据Id查活动
    Activity queryActivityById(int activityId);

    int addLike(Map<String, Object> map);

    //根据openid查活动
    Activity queryActivityByOpenId(String publishUserOpenId);

    int insertActivity(Map<String, Object> map);
}
