package com.example.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author: Richerlv
 * @date: 2022/9/6 15:22
 * @description:
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
