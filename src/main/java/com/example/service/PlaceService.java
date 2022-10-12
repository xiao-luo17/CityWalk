package com.example.service;

import com.example.pojo.Place;

import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/26 17:06
 * @description:
 */
public interface PlaceService {

    //根据关键字模糊查询
    List<Place> queryPlace(String keyword);
}
