package com.example.config;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: xiao-luo17
 * @date: 2022/9/15
 * @description:
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 登录拦截器方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "没有管理员权限，请先登录！");
            request.getRequestDispatcher("/index.html");
            return false;
        } else {
            return true;
        }
    }
}
