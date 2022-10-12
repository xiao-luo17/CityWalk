package com.example.mapper.activity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:19
 * @description:
 */

@Mapper
public interface ActivityPhotoMapper {

    int insertActivityPhoto(Map<String, Object> map);
}
