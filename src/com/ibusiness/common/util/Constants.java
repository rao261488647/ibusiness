package com.ibusiness.common.util;

/**
 * 常量公用类, 所有常量。 使用时请标注什么页面的常量。尽量不要使用其它页面的常量。
 * 
 * @author JiangBo
 * 
 */
public interface Constants {
    /**
     * action公用
     */
    // 返回失败
    String FAILURE = "failure";
    // 返回成功
    String SUCCESS = "success";
    // 时间格式常量
    String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    String DATE_FORMAT_YYYY_MM = "yyyy-MM";
    String DATE_FORMAT_HH_MM_SS = "HH:mm:ss";

    /**
     * Oracle SQL
     */
    String SQL_YYYY_MM_DD_HH24_MM_SS = "yyyy-MM-dd hh24:mi:ss";

    /**
     * 时间类型
     */
    // 日
    String DATE_TIME_TYPE_DAY = "3";
    // 小时
    String DATE_TIME_TYPE_HOUR = "1";
    // 分钟
    String DATE_TIME_TYPE_MINUTE = "2";

    /**
     * SQL用数字(SQl专用)
     */
    // 数字1
    int SQL_1 = 1;
    // 数字2
    int SQL_2 = 2;
    // 数字3
    int SQL_3 = 3;
    // 数字4
    int SQL_4 = 4;
    // 数字5
    int SQL_5 = 5;
    
    /**
     * 报表用
     */
    // 开始时间
    String BEGINTIME = "beginTime";
    // 结束时间
    String ENDTIME = "endTime";
    
    /**
     * 代码生成器生成页面标签工厂用
     */
    // 单行
    String CODE_SINGLE_INPUT = "1";
    // 多行
    String CODE_MULTI_INPUT = "2";
    // 数值
    String CODE_NUMBER_INPUT = "5";
    // 日期
    String CODE_DATE = "3";
    // 时间
    String CODE_DATE_TIME = "4";
    // 下拉列表
    String CODE_SELECT = "6";
    // 数据字典
    String CODE_DATA_SELECT = "7";
    // 单选按钮
    String CODE_RADIOBOX = "8";
    // 复选按钮
    String CODE_CHECKBOX = "9";
    // 选择带出
    String CODE_SELECT_INPUT = "10";
    // 附件上传组件
    String CODE_UPLOAD = "11";
    // 附件下载组件
    String CODE_DOWNLOAD = "12";
    // 图片组件
    String CODE_IMG_LOAD = "13";
    // 日期--年月
    String CODE_DATE_YM = "14";
    // 富文本编辑器组件
    String CODE_KINDEDITOR = "15";
}
