package com.example.service.activity;

import com.example.mapper.activity.ActivityPhotoMapper;
import com.example.mapper.creation.CreationPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:21
 * @description:
 */

@Service
public class ActivityPhotoServiceImpl implements ActivityPhotoService{
    @Autowired
    private ActivityPhotoMapper activityPhotoMapper;

    @Override
    public int insertActivityPhoto(Map<String, Object> map) {
        return activityPhotoMapper.insertActivityPhoto(map);
    }
}
