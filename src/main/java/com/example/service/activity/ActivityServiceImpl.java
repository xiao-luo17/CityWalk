package com.example.service.activity;

import com.example.mapper.activity.ActivityMapper;
import com.example.mapper.activity.CommentMapper;
import com.example.mapper.activity.LikeMapper;
import com.example.pojo.Activity;
import com.example.pojo.Comment;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:22
 * @description:
 */

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public Result like(Map<String, Object> map) {

        int activityId = (int) map.get("activityId");

        int res1 = likeMapper.insertLike(map);
        if(res1 == 0) {
            return Result.Fail("点赞失败");
        }

        Activity activity = activityMapper.queryActivityById(activityId);
        Map<String, Object> mp = new HashMap<>();
        mp.put("activityId", activityId);
        mp.put("likes", activity.getLikes() + 1);

        int res2 = activityMapper.addLike(mp);
        if(res2 == 0) {
            return Result.Fail("点赞失败");
        }

        //这里要加回滚。。

        return Result.SUCCESS("点赞成功");
    }

    @Override
    public Activity queryActivityByOpenId(String publishUserOpenId) {
        return activityMapper.queryActivityByOpenId(publishUserOpenId);
    }

    @Override
    public int insertActivity(Map<String, Object> map) {
        return activityMapper.insertActivity(map);
    }

    @Override
    public Result comment(Map<String, Object> map) {
        int res = commentMapper.insertComment(map);
        if(res == 0) {
            return Result.Fail("评论失败");
        } else {
            return Result.SUCCESS("评论成功");
        }
    }

    @Override
    public Result showComment(Map<String, Object> map) {
        List<Comment> list = commentMapper.queryComment(map);
        return Result.SUCCESS(list);
    }
}
