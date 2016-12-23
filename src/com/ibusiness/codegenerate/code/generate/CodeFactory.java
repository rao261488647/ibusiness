package com.ibusiness.codegenerate.code.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.CodeStringUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 一对一代码生成工厂类
 * 
 * @author JiangBo
 * 
 */
public class CodeFactory extends BaseCodeFactory {

    private ICallBack callBack;

    public CodeFactory() {
    }

    /**
     * 生成文件
     * 
     * @param templateName
     * @param type
     * @param data
     */
    public void generateFile(String templateName, String type, Map<String, Object> data) {
        try {
            String entityPackage = data.get("entityPackage").toString();
            String entityName = data.get("entityName").toString();
            // 取得生成文件路径
            String fileNamePath = getCodePath(type, entityPackage, entityName);
            String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
            // 根据指定的模板文件路径，创建一个模板。
            Template template = getConfiguration().getTemplate(templateName);
            FileUtils.forceMkdir(new File(fileDir + "/"));
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileNamePath),
                    CodeResourceUtil.SYSTEM_ENCODING);
            template.process(data, out);
            out.close();
        } catch (TemplateException templateexception) {
            templateexception.printStackTrace();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    /**
     * main方法
     * 
     * @param args
     */
    public static void main(String args[]) {
        System.out.println(getProjectPath());
    }

    /**
     * 取得模板路径
     * 
     * @return
     */
    public String getTemplatePath() {
        String path = getClassPath() + CodeResourceUtil.TEMPLATEPATH;
        return path;
    }

    /**
     * 取得代码路径
     * 
     * @return
     */
    public String getCodePath(String type, String entityPackage, String entityName) {
        String path = getProjectPath();
        StringBuilder str = new StringBuilder();
        if (StringUtils.isNotBlank(type)) {
            String codeType = CodeType.valueOf(type).getValue();
            str.append(path);
            // JSP文件路径
            if (("jsp".equals(type)) || ("jspList".equals(type))) {
                String jspPath = CodeResourceUtil.JSPPATH;
                // 设置JSP路径
                jspPath = jspPath.replace("/com", "");
                str.append(jspPath);
            } else if (("htmlList".equals(type)) || ("html".equals(type))) {
                // HTML
                str.append("webapp/assets/");
            } else {
                str.append(CodeResourceUtil.CODEPATH);
            }
            
            // 包名
            str.append(StringUtils.lowerCase(entityPackage));
            str.append("/");
            // 判断相等(忽略大小写)
            if ("Resource".equalsIgnoreCase(codeType)) {
                // 接口
                str.append(StringUtils.lowerCase("rs"));
            } else if (!"List".equalsIgnoreCase(codeType)) {
                // 如果不是上面几种情况执行下面路径
                str.append(StringUtils.lowerCase(codeType));
            }
            str.append("/");
            // JSP文件
            if ("jsp".equals(type) || ("jspList".equals(type))) {
                String jspName = StringUtils.capitalize(entityName);
                str.append(CodeStringUtils.getInitialSmall(jspName));
                str.append(codeType);
                // 显示页面 还是插入页面
                if ("jsp".equals(type)) {
                    str.append("-input");
                }
                if ("jspList".equals(type)) {
                    str.append("-list");
                }
                str.append(".jsp");
            } else if ("htmlList".equals(type) || ("html".equals(type))) {
                // HTML
                String jspName = StringUtils.capitalize(entityName);
                str.append(CodeStringUtils.getInitialSmall(jspName));
                str.append(codeType);
                // 显示页面 还是插入页面
                if ("html".equals(type)) {
                    str.append("-input");
                } else if ("htmlList".equals(type)) {
                    str.append("-list");
                }
                str.append(".html");
            } else {
                // JAVA 文件
                str.append(StringUtils.capitalize(entityName));
                str.append(codeType);
                str.append(".java");
            }
        } else {
            throw new IllegalArgumentException("type is null");
        }
        return str.toString();
    }

    /**
     * invoke
     * 
     * @param templateFileName
     * @param type
     */
    public void invoke(String templateFileName, String type) {
        Map<String, Object> data = new HashMap<String, Object>();
        data = callBack.execute();
        generateFile(templateFileName, type, data);
    }

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack icallback) {
        callBack = icallback;
    }
}
