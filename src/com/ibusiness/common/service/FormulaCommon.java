package com.ibusiness.common.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibusiness.base.menu.dao.MenuRoleDefDao;
import com.ibusiness.codegenerate.common.CodeFormulaFactory;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.form.dao.ConfFormTableColumnDao;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.portal.dao.ComponentDao;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.core.util.StringUtils;

/**
 * 默认值公式共用类
 * 
 * @author JiangBo
 *
 */
public class FormulaCommon {
    /**
     * 默认值
     */
    @SuppressWarnings("unchecked")
    public Object defaultValue(Object entity, String tableName) {
        // 
        String sql = "from ConfFormTableColumn where tableName=?";
        List<ConfFormTableColumn> list = getConfFormTableColumnDao().find(sql, tableName);
        Map<String, ConfFormTableColumn> map = new HashMap<String, ConfFormTableColumn>();
        for (ConfFormTableColumn formTableColumn : list) {
            map.put(formTableColumn.getTableColumn(), formTableColumn);
        }
        // 反射获取对象中所有方法名
        for (Field field : entity.getClass().getDeclaredFields()) {
            String tableColumn = field.getName();
            // 取得get方法
            Object objvalue = getMethodValue(entity, "get" + StringUtils.capitalize(field.getName()));
            if ((null == objvalue || CommonUtils.isNull(objvalue.toString())) && map.containsKey(tableColumn.toUpperCase())) {
                ConfFormTableColumn formTableColumn = map.get(tableColumn.toUpperCase());
                if (!CommonUtils.isNull(formTableColumn.getFcDefault())) {
                    // 公式计算
                    objvalue = CodeFormulaFactory.getInstance().getFormula(formTableColumn.getFcDefault());
                    if (null != objvalue && !CommonUtils.isNull(objvalue.toString())) {
                        // set方法
                        setMethodValue(entity, getSetterMethodName(field.getName()), objvalue);
                    }
                }
            }
        }
        return entity;
    }
    /**
     * 反射设置get方法值
     */
    public static Object getMethodValue(Object target, String methodName) {
        try {
            Method method = target.getClass().getDeclaredMethod(methodName);
            return method.invoke(target);
        } catch (Exception e) {
            System.out.println("===========反射设置get方法值出错" + e.toString());
            return "1";
        }
    }
    /**
     * 反射设置set方法值
     */
    public static void setMethodValue(Object target, String methodName, Object methodValue) {
        try {
            Method method = target.getClass().getDeclaredMethod(methodName, methodValue.getClass());
            method.invoke(target, methodValue);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * 取得set方法
     * @param fieldName
     * @return
     */
    public static String getSetterMethodName(String fieldName) {
        return "set" + StringUtils.capitalize(fieldName);
    }
    // ======================================================================
    public ConfFormTableColumnDao getConfFormTableColumnDao() {
        return ApplicationContextHelper.getBean(ConfFormTableColumnDao.class);
    }
    public ComponentDao getComponentDao() {
        return ApplicationContextHelper.getBean(ComponentDao.class);
    }
    public MenuRoleDefDao getMenuRoleDefDao() {
        return ApplicationContextHelper.getBean(MenuRoleDefDao.class);
    }
}
