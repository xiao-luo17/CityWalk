package com.example.mapper.activity;

import com.example.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/8 19:33
 * @description:
 */

@Mapper
public interface CommentMapper {

    //添加评论内容
    int insertComment(Map<String, Object> map);

//    展示评论内容
    List<Comment> queryComment(Map<String, Object> map);
}
