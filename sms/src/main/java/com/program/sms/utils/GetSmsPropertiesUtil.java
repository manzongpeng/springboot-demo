package com.program.sms.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @description: 获取配置文件值
 **/
public class GetSmsPropertiesUtil {
    private static Properties properties = null;

    // 初始化
    static {
        properties = new Properties();
        try {
            properties.load(GetSmsPropertiesUtil.class.getClassLoader().getResourceAsStream("./aliyun.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // 获取值
    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}