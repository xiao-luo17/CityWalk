package com.example.controller.admin;
import com.example.pojo.User;
import com.example.service.admin.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class AllUserController {

    @Autowired
    private AllUserService allUserService;

    @RequestMapping("/allUsers")
    public String allUsers(Model model) {
        Collection<User> users = allUserService.getAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @RequestMapping("/insertform")
    public String insertfrom() {
        return "users/insertform";
    }

    @RequestMapping("/insert")
    public String addUser(int userId, String nameplateNumber, String headshot, String nickname, String email,
                          String phone, String sex, String ipTerritory, String birthday, String area, String school,
                          String tagWords, String slogan, String userPermissions, String able, String token, String openId, Model model) {
        allUserService.addUser(userId, nameplateNumber, headshot, nickname, email, phone, sex, ipTerritory, birthday, area, school, tagWords, slogan, userPermissions, able, token, openId);
        return "users/insertform";
    }

    @RequestMapping("/updateform")
    public String updatefrom(String userId,Model model) {
        User user = allUserService.queryUserById(userId);
        model.addAttribute("user",user);
        return "users/updateform";
    }

    @RequestMapping("/update")
    public String updateUser(String userId, String nameplateNumber, String headshot, String nickname, String email,
                             String phone, String sex, String ipTerritory, String birthday, String area, String school,
                             String tagWords, String slogan, String userPermissions, String able, String token, String openId, Model model) {
        allUserService.update(userId, nameplateNumber, headshot, nickname, email,
                phone, sex, ipTerritory, birthday, area, school,
                tagWords, slogan, userPermissions, able, token, openId);
        Collection<User> users = allUserService.getAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @RequestMapping("/delete")
    public String deleteUser(String userId, Model model) {
        allUserService.deleteUser(userId);
        Collection<User> users = allUserService.getAll();
        model.addAttribute("users", users);
        return "users/list";
    }

}
