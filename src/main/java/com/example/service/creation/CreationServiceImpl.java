package com.example.service.creation;

import com.example.mapper.creation.CreationMapper;
import com.example.pojo.Creation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/9/19 17:58
 * @description:
 */

@Service
public class CreationServiceImpl implements CreationService{
    @Autowired
    private CreationMapper creationMapper;


    @Override
    public int insertCreation(Map<String, Object> map) {
        Creation creation = new Creation();
        creation.setCreationTitle((String) map.get("creationTitle"));
        creation.setCreationContent((String) map.get("creationContent"));
        creation.setPublishDate((Date) map.get("publishDate"));
        creation.setPublishUserOpenId((String) map.get("publishUserOpenId"));
        int res = creationMapper.insertCreation(creation);
        if(res == 0) {     //这里代表插入数据失败，把失败情况告知controller
            return 0;
        } else {
            return creation.getCreationId();   //如果插入数据成功就返回新插入数据的id，插入图片时会用
        }
    }
}
