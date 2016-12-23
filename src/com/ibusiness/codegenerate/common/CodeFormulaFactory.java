package com.ibusiness.codegenerate.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.security.util.SpringSecurityUtils;


/**
 * CodeFormulaFactory 公式工厂类
 * 
 * @author JiangBo
 */
public class CodeFormulaFactory {
    private CodeFormulaFactory () {}
    private static CodeFormulaFactory instance = new CodeFormulaFactory();
    public static CodeFormulaFactory getInstance() {
        return instance;
    }
    private static Logger logger = LoggerFactory.getLogger(CodeFormulaFactory.class);
    /**
     * 公式map
     */
    private static Map<String, String> formulaMap = new HashMap<String, String>();
    static {
        // 当前时间
        formulaMap.put("@currentDateToStr", "getCurrentDateToStr");
        formulaMap.put("@CURRENTDATETOSTR", "getCurrentDateToStr");
        formulaMap.put("@currentDate", "getCurrentDate");
        formulaMap.put("@CURRENTDATE", "getCurrentDate");
//        // 当前年份
//        formulaMap.put("year", "getCurrentYear");
//        // 当前月份
//        formulaMap.put("month", "getCurrentMonth");
        // 当前用户
        formulaMap.put("@userName", "getCurrentUserName");
        formulaMap.put("@USERNAME", "getCurrentUserName");
//        // 当前用户ID
//        formulaMap.put("userId", "getCurrentUserId");
    }
    /**
     * 根据公式类型生成JSP值
     * 
     * @param tagType
     * @param formColumn
     */
    public Object getFormula(String formulaType) {
        // 根据事件类型解析事件
        try {
            if (formulaMap.containsKey(formulaType)) {
                String strMethod = formulaMap.get(formulaType);
                Object obj = CodeFormulaFactory.class.getDeclaredMethod(strMethod).invoke(instance);
                return obj;
            } else {
                logger.error("================要求生成的公式控件不存在:" + formulaType);
                return null;
            }
        } catch (IllegalArgumentException e) {
            logger.error("===============公式控件 IllegalArgumentException:" + e.toString());
        } catch (SecurityException e) {
            logger.error("===============公式控件 SecurityException:" + e.toString());
        } catch (IllegalAccessException e) {
            logger.error("===============公式控件 IllegalAccessException:" + e.toString());
        } catch (InvocationTargetException e) {
            logger.error("===============公式控件 InvocationTargetException:" + e.toString());
        } catch (NoSuchMethodException e) {
            logger.error("===============公式控件 NoSuchMethodException:" + e.toString());
        }
        return null;
    }
    
    /**
     * 获取当前系统时间
     * @return
     */
    public String getCurrentDateToStr() {
        return CommonUtils.getInstance().getCurrentDateTime();
    }
    /**
     * 获取当前系统时间
     * @return
     */
    public Date getCurrentDate() {
        return new Date();
    }
    /**
     * 当前用户
     */
    public String getCurrentUserName() {
        return CommonBusiness.getInstance().getUserBean(SpringSecurityUtils.getCurrentUserId()).getDisplayName();
    }
    // ======================================================================
}
