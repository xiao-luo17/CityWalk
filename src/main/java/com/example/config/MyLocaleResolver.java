package com.example.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author: xiao-luo17
 * @date: 2022/9/15
 * @description: 中英文转换配置
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 中英文请求解析
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        //请求参数解析
        String language = request.getParameter("lan");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(language)){
            String spllit[] = language.split("_");
            locale = new Locale(spllit[0],spllit[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
