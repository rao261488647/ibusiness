package com.ibusiness.codegenerate.util;

import java.util.ResourceBundle;
/**
 * 代码生成器配置文件管理
 * 
 * @author JiangBo
 *
 */
public class CodeResourceUtil {
    // 取得代码生成器数据库配置文件
//    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("code/code_database");
    // 数据库统一用 application.properties 中的配置进行配置
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    // 取得代码生成器配置文件
    private static final ResourceBundle configProperties = ResourceBundle.getBundle("code/code_config");
    public static String DIVER_NAME = "com.mysql.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=UTF-8";
    public static String USERNAME = "root";
    public static String PASSWORD = "root";
    public static String DATABASE_NAME = "sys";
    public static String DATABASE_TYPE = "mysql";
    public static String CG_UI_FIELD_REQUIRED_NUM = "4";
    public static String CG_UI_FIELD_SEARCH_NUM = "3";
    // jsp文件起始根路径
    public static String web_root_package = "WebRoot";
    // java文件起始根路径
    public static String source_root_package = "src";
    // java文件生成路径
    public static String bussiPackage = "sun";
    // jsp文件生成路径
    public static String webrootBussiPackage = "jsp";
    public static String entity_package = "entity";
    public static String page_package = "page";
    public static boolean CG_FILED_CONVERT = true;
    public static String ENTITY_URL;
    public static String PAGE_URL;
    public static String ENTITY_URL_INX;
    public static String PAGE_URL_INX;
    public static String TEMPLATEPATH;
    // 代码生成器,生成文件路径
    public static String PROJECTPATH;
    public static String CODEPATH;
    public static String JSPPATH;
    public static String CG_GENERATE_TABLE_ID;
    public static String CG_GENERATE_UI_FILTER_FIELDS;
    public static String SYSTEM_ENCODING;

    static {
        DIVER_NAME = getDIVER_NAME();
        URL = getURL();
        USERNAME = getUSERNAME();
        PASSWORD = getPASSWORD();
        DATABASE_NAME = getDATABASE_NAME();
        CG_FILED_CONVERT = getCG_FILED_CONVERT();
        SYSTEM_ENCODING = getSYSTEM_ENCODING();
        TEMPLATEPATH = getTEMPLATEPATH();
        PROJECTPATH = getPROJECTPATH();
        source_root_package = getSourceRootPackage();
        web_root_package = getWebRootPackage();
        bussiPackage = getBussiPackage();
        //
        webrootBussiPackage = getWebrootBussiPackage();
        CG_GENERATE_TABLE_ID = getCg_generate_table_id();
        CG_UI_FIELD_SEARCH_NUM = getCg_ui_search_filed_num();
        if ((URL.indexOf("mysql") >= 0) || (URL.indexOf("MYSQL") >= 0))
            DATABASE_TYPE = "mysql";
        else if ((URL.indexOf("oracle") >= 0) || (URL.indexOf("ORACLE") >= 0))
            DATABASE_TYPE = "oracle";
        source_root_package = source_root_package.replace(".", "/");
        web_root_package = web_root_package.replace(".", "/");
        String str = bussiPackage.replace(".", "/");
        ENTITY_URL = source_root_package + "/" + str + "/" + entity_package + "/";
        PAGE_URL = source_root_package + "/" + str + "/" + page_package + "/";
        ENTITY_URL_INX = bussiPackage + "." + entity_package + ".";
        PAGE_URL_INX = bussiPackage + "." + page_package + ".";
        CODEPATH = source_root_package + "/" + str + "/";
        JSPPATH = web_root_package + "/" + str + "/";
    }

    public static final String getDIVER_NAME() {
        return resourceBundle.getString("db.default.driverClassName");
    }

    public static final String getURL() {
        return resourceBundle.getString("db.default.url");
    }

    public static final String getUSERNAME() {
        return resourceBundle.getString("db.default.username");
    }

    public static final String getPASSWORD() {
        return resourceBundle.getString("db.default.password");
    }

    public static final String getDATABASE_NAME() {
        return resourceBundle.getString("default.database.name");
    }

    public static final boolean getCG_FILED_CONVERT() {
        String str = configProperties.getString("cg_filed_convert");
        return !str.toString().equals("false");
    }

    private static String getBussiPackage() {
        return configProperties.getString("bussi_package");
    }

    /**
     * 取得jsp文件生成路径
     * 
     * @return
     */
    private static String getWebrootBussiPackage() {
        return configProperties.getString("webroot_bussi_package");
    }

    public static final String getEntityPackage() {
        return configProperties.getString("entity_package");
    }

    public static final String getPagePackage() {
        return configProperties.getString("page_package");
    }

    /**
     * 模板路径
     * @return
     */
    public static final String getTEMPLATEPATH() {
        return configProperties.getString("templatepath");
    }
    /**
     * 代码生成器,生成文件路径
     * @return
     */
    public static final String getPROJECTPATH() {
        return configProperties.getString("project_path");
    }

    public static final String getSourceRootPackage() {
        return configProperties.getString("source_root_package");
    }

    public static final String getWebRootPackage() {
        return configProperties.getString("webroot_package");
    }

    public static final String getSYSTEM_ENCODING() {
        return configProperties.getString("system_encoding");
    }

    public static final String getCg_generate_table_id() {
        return configProperties.getString("cg_generate_table_id");
    }

    public static final String getCg_generate_ui_filter_fields() {
        return configProperties.getString("cg_generate_ui_filter_fields");
    }

    public static final String getCg_ui_search_filed_num() {
        return configProperties.getString("cg_ui_search_filed_num");
    }

    public static final String getCg_ui_field_required_num() {
        return configProperties.getString("cg_ui_field_required_num");
    }
}
