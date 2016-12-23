package com.ibusiness.common.config;

/**
 * 读取Properties文件信息配置类
 * 
 * @author JiangBo
 *
 */
public class ConfigProperties {
	
	private static PropertiesReader pr = null;

	// 日期时间类型_创建数据库表字段时用。用于区分mysql：DATETIME 和oracle：DATE  
	public static String DATATYPE_DATE;
	// 当前驱动链接的是什么数据库
	public static String DRIVERCLASSNAME;
	public static String DATATYPE;
	static {
		pr = new PropertiesReader("/application.properties");
		DRIVERCLASSNAME = pr.getValue("db.default.driverClassName");
		
		if ("com.mysql.jdbc.Driver".equals(DRIVERCLASSNAME)) {
		    DATATYPE = "MYSQL";
		    DATATYPE_DATE = "DATETIME";
		} else if ("oracle.jdbc.driver.OracleDriver".equals(DRIVERCLASSNAME)) {
		    DATATYPE = "ORACLE";
		    DATATYPE_DATE = "DATE";
		}
	}
}
