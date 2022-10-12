package com.example.controller;

import com.example.pojo.Place;
import com.example.pojo.User;
import com.example.service.PlaceService;
import com.example.utils.Result;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Richerlv
 * @date: 2022/9/26 17:08
 * @description:
 */

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    /**
     * 根据关键字进行模糊查询
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/queryPlace",method = RequestMethod.POST)
    public Result queryPlace(@RequestBody JSONObject jsonObject){

        try{
            String keyword = jsonObject.getString("keyword");
            List<Place> list = placeService.queryPlace(keyword);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.Fail(e.getMessage());
        }
    }
}
