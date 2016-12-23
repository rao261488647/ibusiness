package com.ibusiness.common.service;

import java.util.HashMap;
import java.util.Map;

import com.ibusiness.common.config.ConfigProperties;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * table业务表共用类
 * 
 * @author JiangBo
 *
 */
public class TableCommonUtil {
    // 单例
    private static TableCommonUtil instance = new TableCommonUtil();
    public static TableCommonUtil getInstance() {
        return instance;
    }
    // 整体预留字段Map
    private static Map<String, String> reservedColumnsMap = new HashMap<String, String>();
    // 流程主表预留字段Map
    private static Map<String, ConfTableColumns> bpmMColumnsMap = new HashMap<String, ConfTableColumns>();
    // 流程子表预留字段Map
    private static Map<String, ConfTableColumns> bpmSColumnsMap = new HashMap<String, ConfTableColumns>();
    // 非流程主表预留字段Map
    private static Map<String, ConfTableColumns> mColumnsMap = new HashMap<String, ConfTableColumns>();
    // 非流程子表预留字段Map
    private static Map<String, ConfTableColumns> sColumnsMap = new HashMap<String, ConfTableColumns>();
    
    // 设置预留字段内容
    static {
        // 整体的预留字段
        reservedColumnsMap.put("ID", "VARCHAR(64) NOT NULL");
        reservedColumnsMap.put("EXECUTIONID", "VARCHAR(64)");
        reservedColumnsMap.put("CREATEDATEBPM", ConfigProperties.DATATYPE_DATE);
        reservedColumnsMap.put("NODENAME", "VARCHAR(128)");
        reservedColumnsMap.put("ASSIGNEEUSER", "VARCHAR(128)");
        reservedColumnsMap.put("USERNAME", "VARCHAR(128)");
        reservedColumnsMap.put("DONEFLAG", "INTEGER DEFAULT 0");
        reservedColumnsMap.put("PARENTID", "VARCHAR(64)");
        reservedColumnsMap.put("TASKTITLE", "VARCHAR(256)");
        reservedColumnsMap.put("SCOPEID", "VARCHAR(64)");
        // 流程主表的预留字段
        bpmMColumnsMap.put("ID", createTableColumn("ID","UUID主键",91,"VARCHAR","64","否"));
        bpmMColumnsMap.put("EXECUTIONID", createTableColumn("EXECUTIONID","流程执行实例ID",92,"VARCHAR","64","是"));
        bpmMColumnsMap.put("CREATEDATEBPM", createTableColumn("CREATEDATEBPM","流程创建时间",93,ConfigProperties.DATATYPE_DATE,"","是"));
        bpmMColumnsMap.put("NODENAME", createTableColumn("NODENAME","流程节点名",94,"VARCHAR","128","是"));
        bpmMColumnsMap.put("ASSIGNEEUSER", createTableColumn("ASSIGNEEUSER","负责人",95,"VARCHAR","64","是"));
        bpmMColumnsMap.put("USERNAME", createTableColumn("USERNAME","负责人名",95,"VARCHAR","128","是"));
        bpmMColumnsMap.put("DONEFLAG", createTableColumn("DONEFLAG","流程结束标记",96,"INTEGER","4","是"));
        bpmMColumnsMap.put("TASKTITLE", createTableColumn("TASKTITLE","流程实例标题",97,"VARCHAR","256","是"));
        // 流程子表的预留字段
        bpmSColumnsMap.put("ID", createTableColumn("ID","UUID主键",91,"VARCHAR","64","否"));
        bpmSColumnsMap.put("PARENTID", createTableColumn("PARENTID","主表UUID",92,"VARCHAR","64","否"));
        // 非流程主表的预留字段
        mColumnsMap.put("ID", createTableColumn("ID","UUID主键",91,"VARCHAR","64","否"));
        mColumnsMap.put("SCOPEID", createTableColumn("SCOPEID","范围",92,"VARCHAR","64","否"));
        // 非流程子表的预留字段
        sColumnsMap.put("ID", createTableColumn("ID","UUID主键",91,"VARCHAR","64","否"));
        sColumnsMap.put("PARENTID", createTableColumn("PARENTID","主表UUID",92,"VARCHAR","64","否"));
    }
    /**
     * 根据数据类型生产字段类型
     * @return
     */
    public String getColumnTypeByDataType(String columnType, String columnSize) {
        // 去除空格
        columnSize = columnSize.trim().replaceAll("，", ",");
        // 文本类型
        if ("VARCHAR".equals(columnType)) {
            if ("MYSQL".equals(ConfigProperties.DATATYPE)) {
                return "VARCHAR" + (!CommonUtils.isNull(columnSize)? ("(" + columnSize + ")") : "");
            } else if ("ORACLE".equals(ConfigProperties.DATATYPE)) {
                return "VARCHAR2" + (!CommonUtils.isNull(columnSize)? ("(" + columnSize + ")") : "");
            }
        } else if ("NUMBER".equals(columnType) || "INTEGER".equals(columnType) || "INT".equals(columnType)) {
            // 数值类型
            if ("MYSQL".equals(ConfigProperties.DATATYPE)) {
                if (!CommonUtils.isNull(columnSize) && columnSize.indexOf(",") > 0) {
                    return "DOUBLE" + (!CommonUtils.isNull(columnSize)? ("(" + columnSize + ")") : "");
                } else {
                    return "INT" + (!CommonUtils.isNull(columnSize)? ("(" + columnSize + ")") : "");
                }
            } else if ("ORACLE".equals(ConfigProperties.DATATYPE)) {
                return "NUMBER" + (!CommonUtils.isNull(columnSize)? ("(" + columnSize + ")") : "");
            }
        } else if ("DATE".equals(columnType) || "DATETIME".equals(columnType)) {
            // 日期类型
            if ("MYSQL".equals(ConfigProperties.DATATYPE)) {
                return "DATETIME";
            } else if ("ORACLE".equals(ConfigProperties.DATATYPE)) {
                return "DATE";
            }
        }
        return "";
    }
    /**
     * 创建一个 表字段结构Bean 对象
     * 
     * @param confTable
     * @return
     */
    private static ConfTableColumns createTableColumn(String columnValue, String columnName, Integer columnNo,
            String columnType, String columnSize, String isNull) {
        ConfTableColumns confTableColumns = new ConfTableColumns();
        confTableColumns.setColumnValue(columnValue);
        confTableColumns.setColumnName(columnName);
        confTableColumns.setColumnNo(columnNo);
        confTableColumns.setColumnType(columnType);
        confTableColumns.setColumnSize(columnSize);
        confTableColumns.setIsNull(isNull);
        return confTableColumns;
    }
    /**
     * @return the reservedColumnsMap
     */
    public static Map<String, String> getReservedColumnsMap() {
        return reservedColumnsMap;
    }
    /**
     * @return the bpmMColumnsMap
     */
    public static Map<String, ConfTableColumns> getBpmMColumnsMap() {
        return bpmMColumnsMap;
    }
    /**
     * @return the bpmSColumnsMap
     */
    public static Map<String, ConfTableColumns> getBpmSColumnsMap() {
        return bpmSColumnsMap;
    }
    /**
     * @return the mColumnsMap
     */
    public static Map<String, ConfTableColumns> getmColumnsMap() {
        return mColumnsMap;
    }
    /**
     * @return the sColumnsMap
     */
    public static Map<String, ConfTableColumns> getsColumnsMap() {
        return sColumnsMap;
    }
}
