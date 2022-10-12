package com.example.controller;

import com.example.pojo.User;
import com.example.service.creation.CreationPhotoService;
import com.example.service.creation.CreationService;
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
 * @date: 2022/9/19 18:02
 * @description:
 */

@RestController
@RequestMapping("/creation")
public class CreationController {

    @Autowired
    private CreationService creationService;
    @Autowired
    private CreationPhotoService creationPhotoService;
    @Autowired
    private LoginService loginService;

    /**
     * 创作者文字和图片一起上传的接口，开发中，现在还不能用
     * @param json
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(HttpServletRequest request, @RequestBody JSONObject json, MultipartFile[] file){

        try{

            try{
                Result result = loginService.checkToken(request);
                if(result.getCode() == 500) {  //代表校验未通过
                    return Result.LOGIN_Fail("token校验失败！");
                }
                User user = (User) result.getData();

                String creationTitle = json.getString("creationTitle");
                String creationContent = json.getString("creationContent");
                Date publishDate = new Date();

                Map<String, Object> map = new HashMap<>();
                map.put("creationTitle", creationTitle);
                map.put("creationContent", creationContent);
                map.put("publishDate", publishDate);
                map.put("publishUserOpenId", user.getOpenId());

                int creationId = creationService.insertCreation(map);
                if(creationId == 0) return Result.Fail("上传失败！");

                //文字上传成功，上传图片
                for(int i = 0; i < file.length; i ++) {

                    String originalFilename = file[i].getOriginalFilename();
                    //随机生成文件名
                    String fileName = UUIDUtil.uuid() + originalFilename;
                    System.out.println("==============接收到图片================");
                    String filePath = "D:/img/"+fileName;
                    File saveDir = new File(filePath);
                    if (!saveDir.getParentFile().exists())
                        saveDir.getParentFile().mkdirs();
                    file[i].transferTo(saveDir);

                    //向数据库添加图片路径
                    Map<String, Object> photoMap = new HashMap<>();
                    photoMap.put("photoUrl", filePath);
                    photoMap.put("creationId", creationId);

                    int res = creationPhotoService.insertCreationPhoto(photoMap);
                    if(res == 0) return Result.Fail("上传图片失败！");
                }

                return Result.SUCCESS("上传成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return Result.Fail(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }




    /**
     * 创作者文字上传
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "/insertCreation",method = RequestMethod.POST)
    public Result insertCreation(HttpServletRequest request, @RequestBody JSONObject json){

        try{
            Result result = loginService.checkToken(request);
            if(result.getCode() == 500) {  //代表校验未通过
                return Result.LOGIN_Fail("token校验失败！");
            }
            User user = (User) result.getData();
            String creationTitle = json.getString("creationTitle");
            String creationContent = json.getString("creationContent");
            Date publishDate = new Date();

            Map<String, Object> map = new HashMap<>();
            map.put("creationTitle", creationTitle);
            map.put("creationContent", creationContent);
            map.put("publishDate", publishDate);
            map.put("publishUserOpenId", user.getOpenId());

            int res = creationService.insertCreation(map);             //0代表失败，其他值是新插入数据的id

            if(res == 0) return Result.Fail("文字上传失败！");
            return Result.SUCCESS(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

    /**
     * 创作者图片上传
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public Result uploadImg(HttpServletRequest request, @RequestBody MultipartFile[] file) throws Exception{
        try{

            String creationId = request.getHeader("creationId");      //这里request用来获取创作内容的id，因为微信官方的函数不让放json，我就把它放在请求体里面了

            for(int i = 0; i < file.length; i ++) {
//            if (file[i].isEmpty()) {
//                return Result.Fail("图片不能为空");
//            }
                String originalFilename = file[i].getOriginalFilename();
                //随机生成文件名
                String fileName = UUIDUtil.uuid() + originalFilename;    //主要防止不同文章的相同文件名
                System.out.println("==============接收到图片================");
                String filePath = "D:/img/"+fileName;
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                file[i].transferTo(saveDir);

                //向数据库添加图片路径
                Map<String, Object> map = new HashMap<>();
                map.put("photoUrl", filePath);
                map.put("creationId", creationId);            //这里还要改。。。。

                int res = creationPhotoService.insertCreationPhoto(map);
                if(res == 0) return Result.Fail("上传图片失败！");
            }

            return Result.SUCCESS("上传图片成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }


















    /**
     * 向创作者图片数据库添加数据：用于测试，前端不直接调用
     * @param json
     * @return
     */
    @RequestMapping(value = "/insertCreationPhoto",method = RequestMethod.POST)
    public Result insertCreationPhoto(@RequestBody JSONObject json){

        try{

            String photoUrl = json.getString("photoUrl");
            int creationId = json.getInt("creationId");

            Map<String, Object> map = new HashMap<>();
            map.put("photoUrl", photoUrl);
            map.put("creationId", creationId);

            int res = creationPhotoService.insertCreationPhoto(map);
            return Result.SUCCESS(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }

}
