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
 * 一对多代码生成工厂类
 * 
 * @author JiangBo
 * 
 */
public class CodeFactoryBpmForm extends BaseCodeFactory  {

    private ICallBack callBack;
    /**
     * 生成文件
     * @param templateName
     * @param type
     * @param map
     */
    public void generateFile(String templateName, String type, Map<String, Object> map) {
        try {
            String entityPackage = map.get("entityPackage").toString();
            String entityName = map.get("entityName").toString();
            // 取得生成文件路径
            String codePath = getCodePath(type, entityPackage, entityName);
            String fileDir = StringUtils.substringBeforeLast(codePath, "/");
            // 根据指定的模板文件路径，创建一个模板。
            Template template = getConfiguration().getTemplate(templateName);
            FileUtils.forceMkdir(new File(fileDir + "/"));
            OutputStreamWriter outStream = new OutputStreamWriter(new FileOutputStream(codePath), CodeResourceUtil.SYSTEM_ENCODING);
            template.process(map, outStream);
            outStream.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String getClassPath() {
        String classPath = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        return classPath;
    }

    public static void main(String args[]) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("./").getPath());
    }

    public String getTemplatePath() {
        String templatePath = (new StringBuilder(String.valueOf(getClassPath()))).append(CodeResourceUtil.TEMPLATEPATH).toString();
        return templatePath;
    }

    /**
     * 取得生成文件路径
     * @return
     */
    public String getCodePath(String type, String entityPackage, String entityName) {
        String projectPath = getProjectPath();
        StringBuilder strBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(type)) {
            String codeType = CodeType.valueOf(type).getValue();
            strBuilder.append(projectPath);
            if ("jspSub".equals(type) || "jsp".equals(type)  || "jspList".equals(type)) {
                String jspPath = CodeResourceUtil.JSPPATH;
                // 设置JSP路径
                jspPath = jspPath.replace("/com", "");
                strBuilder.append(jspPath);
            } else if (("htmlList".equals(type)) || ("html".equals(type))) {
                // HTML
                strBuilder.append("webapp/assets/");
            } else {
                strBuilder.append(CodeResourceUtil.CODEPATH);
            }
            // 包名
            strBuilder.append(StringUtils.lowerCase(entityPackage));
            strBuilder.append("/");
            // 模块名
            if ("Resource".equalsIgnoreCase(codeType)) {
                strBuilder.append(StringUtils.lowerCase("rs"));
            } else if (!"jspAdd".equals(type) && !"jspEdit".equals(type) && !"jsp".equals(type)) {
                // 如果不是上面几种情况执行下面路径
                strBuilder.append(StringUtils.lowerCase(codeType));
            }
            strBuilder.append("/");
            // 文件名
            if ("jspList".equals(type) || "jspSub".equals(type) || "jsp".equals(type)) {
                String s5 = StringUtils.capitalize(entityName);
                strBuilder.append(CodeStringUtils.getInitialSmall(s5));
                strBuilder.append(codeType);
                // 显示页面 还是插入页面
                if ("jsp".equals(type)) {
                    strBuilder.append("-input");
                } else if ("jspSub".equals(type)) {
                    strBuilder.append("-sub-input");
                } else if ("jspList".equals(type)) {
                    strBuilder.append("-list");
                }
                strBuilder.append(".jsp");
            } else if ("htmlList".equals(type) || ("html".equals(type))) {
                // HTML
                String jspName = StringUtils.capitalize(entityName);
                strBuilder.append(CodeStringUtils.getInitialSmall(jspName));
                strBuilder.append(codeType);
                // 显示页面 还是插入页面
                if ("html".equals(type)) {
                    strBuilder.append("-input");
                } else if ("htmlList".equals(type)) {
                    strBuilder.append("-list");
                }
                strBuilder.append(".html");
            } else {
                strBuilder.append(StringUtils.capitalize(entityName));
                strBuilder.append(codeType);
                strBuilder.append(".java");
            }
        } else {
            throw new IllegalArgumentException("type is null");
        }
        return strBuilder.toString();
    }

    public void invoke(String templateName, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = callBack.execute();
        generateFile(templateName, type, map);
    }

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack icallback) {
        callBack = icallback;
    }
}
