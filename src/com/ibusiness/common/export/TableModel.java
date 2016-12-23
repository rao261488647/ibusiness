package com.ibusiness.common.export;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibusiness.core.util.ReflectUtils;

/**
 * 表模型
 * 
 * @author JiangBo
 * 
 */
public class TableModel {
    private static Logger logger = LoggerFactory.getLogger(TableModel.class);
    // 导出excel文件名
    private String excelName;
    // 导入表名
    private String tableName;
    // 列/字段信息
    private List<String> headers = new ArrayList<String>();
    // 数据
    private List data;

    /**
     * @return the excelName
     */
    public String getExcelName() {
        return excelName;
    }
    /**
     * @param excelName the excelName to set
     */
    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    /**
     * 添加列信息
     * @param header
     */
    public void addHeaders(String... header) {
        if (header == null) {
            return;
        }

        for (String text : header) {
            if (text == null) {
                continue;
            }

            headers.add(text);
        }
    }

    /**
     * 设置值
     * @param data
     */
    public void setData(List data) {
        this.data = data;
    }

    public int getHeaderCount() {
        return headers.size();
    }

    public int getDataCount() {
        return data.size();
    }

    public String getHeader(int index) {
        return headers.get(index);
    }

    /**
     * 取得值
     * 
     * @param i
     * @param j
     * @return
     */
    public String getValue(int i, int j) {
        try {
            String header = getHeader(j);
            Object object = data.get(i);
            String methodName = ReflectUtils.getGetterMethodName(object, header);
            Object value = ReflectUtils.getMethodValue(object, methodName);

            return (value == null) ? "" : value.toString();
        } catch (Exception ex) {
            logger.info("error", ex);

            return "";
        }
    }
    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }
    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
