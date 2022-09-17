package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.mapper")

public class SpringbootStudyMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudyMybatisApplication.class, args);
    }

}