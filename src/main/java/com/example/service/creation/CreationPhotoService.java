package com.example.service.creation;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/9/19 20:21
 * @description:
 */
public interface CreationPhotoService {

    //添加创作内容对应的图片
    int insertCreationPhoto(Map<String, Object> map);
}
