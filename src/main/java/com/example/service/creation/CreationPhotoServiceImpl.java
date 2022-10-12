package com.example.service.creation;

import com.example.mapper.creation.CreationPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/9/19 20:23
 * @description:
 */
@Service
public class CreationPhotoServiceImpl implements CreationPhotoService{

    @Autowired
    private CreationPhotoMapper creationPhotoMapper;

    @Override
    public int insertCreationPhoto(Map<String, Object> map) {
        return creationPhotoMapper.insertCreationPhoto(map);
    }
}
