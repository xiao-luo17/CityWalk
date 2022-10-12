package com.example.mapper.creation;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/9/19 20:17
 * @description:
 */

@Mapper
public interface CreationPhotoMapper {

    //添加创作内容对应的图片
    int insertCreationPhoto(Map<String, Object> map);
}
