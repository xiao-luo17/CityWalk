package com.example.mapper;

import com.example.pojo.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/26 16:54
 * @description:
 */
@Mapper
public interface PlaceMapper {

    //根据关键字模糊查询
    List<Place> queryPlace(String keyword);
}
