package com.example.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @author: xiao-luo17
 * @date: 2022/9/15
 * @description:
 */
@SpringBootConfiguration
public class AutoStartProject implements CommandLineRunner {

    /**
     * 启动项目自动跳转到登陆页面（直接注释掉就可以关闭该功能）
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080/");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

