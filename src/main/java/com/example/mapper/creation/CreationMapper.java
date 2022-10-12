package com.example.mapper.creation;

import com.example.pojo.Creation;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/9/19 17:48
 * @description:
 */
@Mapper
public interface CreationMapper {

    //添加创作内容
    int insertCreation(Creation creation);
}
