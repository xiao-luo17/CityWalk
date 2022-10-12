package com.example.mapper.activity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/7 14:04
 * @description:
 */

@Mapper
public interface LikeMapper {

    //添加点赞内容
    int insertLike(Map<String, Object> map);
}
