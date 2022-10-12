package com.example.controller;

import com.example.pojo.Activity;
import com.example.pojo.User;
import com.example.service.activity.ActivityPhotoService;
import com.example.service.activity.ActivityService;
import com.example.service.LoginService;
import com.example.utils.Result;
import com.example.utils.UUIDUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:22
 * @description:
 */

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityPhotoService activityPhotoService;

    /**
     * 评论展示
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "/showComment",method = RequestMethod.POST)
    public Result showComment(HttpServletRequest request, @RequestBody JSONObject json){

        try{
            Result result = loginService.checkToken(request);
            if(result.getCode() == 500) {  //代表校验未通过
                return Result.LOGIN_Fail("token校验失败！");
            }
            int activityId = json.getInt("activityId");
            Map<String, Object> map = new HashMap<>();
            map.put("activityId", activityId);

            Result res = activityService.showComment(map);

            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 评论
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Result comment(HttpServletRequest request, @RequestBody JSONObject json){

        try{
            Result result = loginService.checkToken(request);
            if(result.getCode() == 500) {  //代表校验未通过
                return Result.LOGIN_Fail("token校验失败！");
            }
            User user = (User) result.getData();
            String openId = user.getOpenId();
            String commentContent = json.getString("commentContent");
            int activityId = json.getInt("activityId");
            Date commentDate = new Date();

            Map<String, Object> map = new HashMap<>();
            map.put("openId", openId);
            map.put("activityId", activityId);
            map.put("commentContent", commentContent);
            map.put("commentDate", commentDate);

            Result res = activityService.comment(map);

            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 点赞
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Result like(HttpServletRequest request, @RequestBody JSONObject json){

        try{
            Result result = loginService.checkToken(request);
            if(result.getCode() == 500) {  //代表校验未通过
                return Result.LOGIN_Fail("token校验失败！");
            }
            User user = (User) result.getData();
            String openId = user.getOpenId();
            int activityId = json.getInt("activityId");

            Map<String, Object> map = new HashMap<>();
            map.put("openId", openId);
            map.put("activityId", activityId);

            Result res = activityService.like(map);             //数据库操作放在这里

            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 展示活动文字部分接口
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "/queryActivityByOpenId",method = RequestMethod.POST)
    public Result queryActivityByOpenId(HttpServletRequest request, @RequestBody JSONObject json) {
        try{
            Result result = loginService.checkToken(request);
            if(result.getCode() == 500) {  //代表校验未通过
                return Result.LOGIN_Fail("token校验失败！");
            }
            User user = (User) result.getData();
            Activity activity = activityService.queryActivityByOpenId(user.getOpenId());
            if(activity == null) return Result.Fail("活动查询失败！");
            else return Result.SUCCESS(activity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 上传活动文字部分接口
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "/insertActivity",method = RequestMethod.POST)
    public Result insertActivity(HttpServletRequest request, @RequestBody JSONObject json) {
        try{
            Result result = loginService.checkToken(request);
            if(result.getCode() == 500) {  //代表校验未通过
                return Result.LOGIN_Fail("token校验失败！");
            }
            User user = (User) result.getData();
            String activityTitle = json.getString("activityTitle");
            String hostOrganization = json.getString("hostOrganization");
            String activityDetail = json.getString("activityDetail");
            Date publishDate = new Date();

            Map<String, Object> map = new HashMap<>();
            map.put("activityTitle", activityTitle);
            map.put("hostOrganization", hostOrganization);
            map.put("activityDetail", activityDetail);
            map.put("publishDate", publishDate);
            map.put("publishUserOpenId", user.getOpenId());

            int res = activityService.insertActivity(map);

            if(res == 0) return Result.Fail("文字上传失败！");
            return Result.SUCCESS(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 上传活动图片部分接口
     * @param request
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadActivityImg",method = RequestMethod.POST)
    public Result uploadActivityImg(HttpServletRequest request, @RequestBody MultipartFile[] file) throws Exception {
        try {

            String activityId = request.getHeader("activityId");
            for (int i = 0; i < file.length; i++) {
                String originalFilename = file[i].getOriginalFilename();
                //随机生成文件名
                String fileName = UUIDUtil.uuid() + originalFilename;
                System.out.println("==============接收到活动图片================");
                String filePath = "D:/img/" + fileName;
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                file[i].transferTo(saveDir);

                //向数据库添加图片路径
                Map<String, Object> map = new HashMap<>();
                map.put("activityPhotoUrl", filePath);
                map.put("activityId", activityId);

                int res = activityPhotoService.insertActivityPhoto(map);
                if (res == 0) return Result.Fail("上传图片失败！");
            }
            return Result.SUCCESS("上传图片成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }
}
