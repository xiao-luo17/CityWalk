package com.example.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

/**
 * @author: xiao-luo17
 * @date: 2022/9/15
 * @description:
 */
@Controller
public class AdminController {

    /**
     * Admin登录校验（一个管理员账号）
     * Admin账号——用户名：Admin
     * Admin账号——密 码：123456
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/admin")
    public String login(String username, String password, RedirectAttributesModelMap model, HttpSession session){
        if("Admin".equals(username)&&"123456".equals(password)){
            System.out.println(username);
            System.out.println(password);
            model.addFlashAttribute("username",username);
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","密码或用户名错误！");
            return "index";
        }
    }

}
