package com.example.service;

import com.example.mapper.PlaceMapper;
import com.example.pojo.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.PlainView;
import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/26 17:06
 * @description:
 */

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    /**
     * 根据关键字进行模糊查询
     * @param keyword
     * @return
     */
    @Override
    public List<Place> queryPlace(String keyword) {
        return placeMapper.queryPlace(keyword);
    }
}
