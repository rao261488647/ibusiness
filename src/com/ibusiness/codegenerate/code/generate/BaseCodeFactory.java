package com.ibusiness.codegenerate.code.generate;

import java.io.IOException;
import java.util.Locale;

import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.common.util.CommonUtils;

import freemarker.template.Configuration;

/**
 * 基础代码生成工厂类
 * 
 * @author JiangBo
 * 
 */
public class BaseCodeFactory {
    /**
     * 根据指定的模板文件路径，创建一个模板
     * @return
     * @throws IOException
     */
    public Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(super.getClass(), "/code/template");
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
    
    /**
     * 获取项目根目录
     * 
     * @return
     */
    public static String getProjectPath() {
        String nowpath = CodeResourceUtil.PROJECTPATH; // 当前tomcat的bin目录的路径 如
        // D:\java\software\apache-tomcat-6.0.14\bin
        String tempdir;
        if (CommonUtils.isNull(nowpath)) {
            nowpath = System.getProperty("user.dir");
        }
        tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
        tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
        return tempdir;
    }
    
    /**
     * 取得类路径
     * 
     * @return
     */
    public String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        if (path.indexOf("lib") > 0) {
            path = "/WEB-INF/classes/";
        }
        return path;
    }
    /**
     * 枚举类
     * 
     * @author JiangBo
     * 
     */
    public enum CodeType {
        resource("Resource"),html(""), htmlList(""), 
        service("Service"), controller("Controller"), entity("Entity"), page("Page"),
        jsp(""), jspList(""),jspSub("");
        
        // 成员变量
        private String type;

        private CodeType(String type) {
            this.type = type;
        }

        public String getValue() {
            return this.type;
        }
    }
}
