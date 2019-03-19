package com.fnst.face.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/** 配置文件属性获取工具类
 * Created by geely
 */
public class PropertiesUtil {
    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties prop;

    static {
        String fileName = "application.properties";
        prop = new Properties();
        try {
            //加载Properties
            prop.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            log.error("初始化配置失败", e);
        }
    }

    public static String getKey(String key) {
        String value = prop.getProperty(key.trim());
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    public static String getKey(String key, String defaultValue) {
        String value = prop.getProperty(key.trim());
        if (value == null) {
            return defaultValue;
        }
        return value.trim();
    }


}
