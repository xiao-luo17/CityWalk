package com.example.service.creation;

import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/9/19 17:58
 * @description:
 */
public interface CreationService {

    //添加创作内容
    int insertCreation(Map<String, Object> map);
}
