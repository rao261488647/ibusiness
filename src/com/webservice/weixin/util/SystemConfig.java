package com.webservice.weixin.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 用来读取properties可配置属性
 * 
 * @author zhanghailong
 */
public class SystemConfig {

    private static Logger log = Logger.getLogger(SystemConfig.class);

    public static String UPLOAD_IMG_PATH;// 图片上传路径
    public static final int PAGE_SIZE = 15;// 查询帖子每次返回条数
    public static String PARTNER;
    public static String SELLER;
    public static String RSA_PRIVATE;
    public static String LOCAL_SERVICE; // 回调接口
    public static String SEND_SMS;
    /**
     * 读取可配置信息的properties文件
     */
    static {
        Properties properties = new Properties();
        try {
            properties.load(SystemConfig.class.getResourceAsStream("/systconfig.properties"));

            UPLOAD_IMG_PATH = properties.getProperty("upload_img_path");
            PARTNER = properties.getProperty("partner");
            SELLER = properties.getProperty("seller");
            RSA_PRIVATE = properties.getProperty("rsa_private");
            LOCAL_SERVICE = properties.getProperty("local_service");
            SEND_SMS = properties.getProperty("send_sms");
        } catch (IOException e) {
            log.error("初始化系统参数错误:" + e.getMessage());
            log.error(SystemConfig.class, e);
        }
    }
}
