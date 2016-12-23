package com.ibusiness.codegenerate.code.DbEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibusiness.codegenerate.code.Columnt;
import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.TableConvert;

/**
 * JSP通用数据文件
 * 
 * @author JiangBo
 * 
 */
public class DbFiledToJspUtil {

    private Connection connection;
    private Statement statement;
    private String sqlStr;
    private ResultSet resultSet;

    public DbFiledToJspUtil() {
    }

    /**
     * 读取指定表名的表字段List
     * 
     * @param tableName
     * @return
     */
    public List<Columnt> readTableColumn(String tableName) {
        ArrayList<Columnt> arraylist = new ArrayList<Columnt>();
        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            connection = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
                    CodeResourceUtil.PASSWORD);
            statement = connection.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
                sqlStr = MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
                                new Object[] {TableConvert.getV(tableName.toUpperCase()), TableConvert.getV(CodeResourceUtil.DATABASE_NAME)});
            }
            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle")) {
                sqlStr = MessageFormat
                        .format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}",
                                new Object[] {TableConvert.getV(tableName.toUpperCase())});
            }
            resultSet = statement.executeQuery(sqlStr);
            resultSet.last();
            int i = resultSet.getRow();
            int j = i;
            if (j > 0) {
                Columnt columnt = new Columnt();
                if (CodeResourceUtil.CG_FILED_CONVERT) {
                    columnt.setFieldName(formatField(resultSet.getString(1).toLowerCase()));
                } else {
                    columnt.setFieldName(resultSet.getString(1).toLowerCase());
                }
                columnt.setFieldDbName(resultSet.getString(1).toUpperCase());
                columnt.setFieldType(formatField(resultSet.getString(2).toLowerCase()));
                columnt.setPrecision(resultSet.getString(4));
                columnt.setScale(resultSet.getString(5));
                columnt.setCharmaxLength(resultSet.getString(6));
                columnt.setNullable(TableConvert.getNullAble(resultSet.getString(7)));
                editColumnt(columnt);
                columnt.setFiledComment(StringUtils.isBlank(resultSet.getString(3)) ? columnt
                        .getFieldName() : resultSet.getString(3));
                if (!CodeResourceUtil.CG_GENERATE_TABLE_ID.equals(columnt.getFieldName())
                        && !"createDt".equals(columnt.getFieldName()) && !"modifyDt".equals(columnt.getFieldName())
                        && !"delflag".equals(columnt.getFieldName()) && !"crtuser".equals(columnt.getFieldName())
                        && !"crtuserName".equals(columnt.getFieldName())
                        && !"modifierName".equals(columnt.getFieldName()) && !"optip".equals(columnt.getFieldName())
                        && !"modifier".equals(columnt.getFieldName()) && !"delDt".equals(columnt.getFieldName())
                        && !"modifyip".equals(columnt.getFieldName())) {
                    arraylist.add(columnt);
                }
                while (resultSet.previous()) {
                    Columnt columnt2 = new Columnt();
                    if (CodeResourceUtil.CG_FILED_CONVERT) {
                        columnt2.setFieldName(formatField(resultSet.getString(1).toLowerCase()));
                    } else {
                        columnt2.setFieldName(resultSet.getString(1).toLowerCase());
                    }
                    columnt2.setFieldDbName(resultSet.getString(1).toUpperCase());
                    if (!CodeResourceUtil.CG_GENERATE_TABLE_ID.equals(columnt2.getFieldName())
                            && !"createDt".equals(columnt2.getFieldName())
                            && !"modifyDt".equals(columnt2.getFieldName())
                            && !"delflag".equals(columnt2.getFieldName()) && !"crtuser".equals(columnt2.getFieldName())
                            && !"crtuserName".equals(columnt2.getFieldName())
                            && !"modifierName".equals(columnt2.getFieldName())
                            && !"optip".equals(columnt2.getFieldName()) && !"modifier".equals(columnt2.getFieldName())
                            && !"delDt".equals(columnt2.getFieldName()) && !"modifyip".equals(columnt2.getFieldName())) {
                        columnt2.setFieldType(formatField(resultSet.getString(2).toLowerCase()));
                        columnt2.setPrecision(resultSet.getString(4));
                        columnt2.setScale(resultSet.getString(5));
                        columnt2.setCharmaxLength(resultSet.getString(6));
                        columnt2.setNullable(TableConvert.getNullAble(resultSet.getString(7)));
                        editColumnt(columnt2);
                        columnt2.setFiledComment(StringUtils.isBlank(resultSet.getString(3)) ? columnt2
                                .getFieldName() : resultSet.getString(3));
                        arraylist.add(columnt2);
                    }
                }
            }
        } catch (ClassNotFoundException classnotfoundexception) {
        } catch (SQLException sqlexception) {
        }
        try {
            if (statement != null) {
                statement.close();
                statement = null;
                System.gc();
            }
            if (connection != null) {
                connection.close();
                connection = null;
                System.gc();
            }
        } catch (SQLException sqlexception1) {
        }
        try {
            if (statement != null) {
                statement.close();
                statement = null;
                System.gc();
            }
            if (connection != null) {
                connection.close();
                connection = null;
                System.gc();
            }
        } catch (SQLException sqlexception2) {
        }
        // 编辑返回数据
        List<Columnt> columtList = new ArrayList<Columnt>();
        for (int k = arraylist.size() - 1; k >= 0; k--) {
            Columnt columnt = arraylist.get(k);
            columtList.add(columnt);
        }
        return columtList;
    }

    /**
     * 读取表字段List(原值)
     * 
     * @param s
     * @return
     */
    public List<Columnt> readOriginalTableColumn(String s) {
        ArrayList<Columnt> arraylist = new ArrayList<Columnt>();
        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            connection = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME, CodeResourceUtil.PASSWORD);
            statement = connection.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql"))
                sqlStr = MessageFormat
                        .format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
                                new Object[] {
                                        TableConvert.getV(s.toUpperCase()),
                                        TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle"))
                sqlStr = MessageFormat
                        .format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}",
                                new Object[] {
                                    TableConvert.getV(s.toUpperCase()) });
            resultSet = statement.executeQuery(sqlStr);
            resultSet.last();
            int i = resultSet.getRow();
            int j = i;
            if (j > 0) {
                Columnt columnt = new Columnt();
                if (CodeResourceUtil.CG_FILED_CONVERT) {
                	columnt.setFieldName(formatField(resultSet.getString(1).toLowerCase()));
                } else {
                	columnt.setFieldName(resultSet.getString(1).toLowerCase());
                }
                columnt.setFieldDbName(resultSet.getString(1).toUpperCase());
                columnt.setPrecision(TableConvert.getNullString(resultSet.getString(4)));
                columnt.setScale(TableConvert.getNullString(resultSet.getString(5)));
                columnt.setCharmaxLength(TableConvert.getNullString(resultSet.getString(6)));
                columnt.setNullable(TableConvert.getNullAble(resultSet.getString(7)));
                columnt.setFieldType(getType(resultSet.getString(2).toLowerCase(), columnt.getPrecision(),
                        columnt.getScale()));
                editColumnt(columnt);
                columnt.setFiledComment(StringUtils.isBlank(resultSet.getString(3)) ? columnt
                        .getFieldName() : resultSet.getString(3));
                arraylist.add(columnt);
                Columnt columnt2;
                for (; resultSet.previous(); arraylist.add(columnt2)) {
                    columnt2 = new Columnt();
                    if (CodeResourceUtil.CG_FILED_CONVERT) {
                        columnt2.setFieldName(formatField(resultSet.getString(1).toLowerCase()));
                    } else {
                    	columnt2.setFieldName(resultSet.getString(1).toLowerCase());
                    }
                    columnt2.setFieldDbName(resultSet.getString(1).toUpperCase());
                    columnt2.setPrecision(TableConvert.getNullString(resultSet.getString(4)));
                    columnt2.setScale(TableConvert.getNullString(resultSet.getString(5)));
                    columnt2.setCharmaxLength(TableConvert.getNullString(resultSet.getString(6)));
                    columnt2.setNullable(TableConvert.getNullAble(resultSet.getString(7)));
                    columnt2.setFieldType(getType(resultSet.getString(2).toLowerCase(),
                            columnt2.getPrecision(), columnt2.getScale()));
                    editColumnt(columnt2);
                    columnt2.setFiledComment(StringUtils.isBlank(resultSet.getString(3)) ? columnt2
                            .getFieldName() : resultSet.getString(3));
                }
            }
        } catch (ClassNotFoundException classnotfoundexception) {
        } catch (SQLException sqlexception) {
        }
        try {
            if (statement != null) {
                statement.close();
                statement = null;
                System.gc();
            }
            if (connection != null) {
                connection.close();
                connection = null;
                System.gc();
            }
        } catch (SQLException sqlexception1) {
        }
        try {
            if (statement != null) {
                statement.close();
                statement = null;
                System.gc();
            }
            if (connection != null) {
                connection.close();
                connection = null;
                System.gc();
            }
        } catch (SQLException sqlexception2) {
        }
        List<Columnt> list = new ArrayList<Columnt>();
        for (int k = arraylist.size() - 1; k >= 0; k--) {
            Columnt columnt1 = arraylist.get(k);
            list.add(columnt1);
        }

        return list;
    }

    public static String formatField(String s) {
        String as[] = s.split("_");
        s = "";
        int i = 0;
        for (int j = as.length; i < j; i++) {
            if (i > 0) {
                String s1 = as[i].toLowerCase();
                s1 = (new StringBuilder(String.valueOf(s1.substring(0, 1).toUpperCase()))).append(
                        s1.substring(1, s1.length())).toString();
                s = (new StringBuilder(String.valueOf(s))).append(s1).toString();
            } else {
                s = (new StringBuilder(String.valueOf(s))).append(as[i].toLowerCase()).toString();
            }
        }
        return s;
    }

    public static String formatFieldCapital(String s) {
        String as[] = s.split("_");
        s = "";
        int i = 0;
        for (int j = as.length; i < j; i++) {
            if (i > 0) {
                String s1 = as[i].toLowerCase();
                s1 = (new StringBuilder(String.valueOf(s1.substring(0, 1).toUpperCase()))).append(
                        s1.substring(1, s1.length())).toString();
                s = (new StringBuilder(String.valueOf(s))).append(s1).toString();
            } else {
                s = (new StringBuilder(String.valueOf(s))).append(as[i].toLowerCase()).toString();
            }
        }
        s = (new StringBuilder(String.valueOf(s.substring(0, 1).toUpperCase()))).append(s.substring(1)).toString();
        return s;
    }

    public boolean checkTableExist(String s) {
        try {
            int i;
            System.out.println((new StringBuilder("\u6570\u636E\u5E93\u9A71\u52A8: ")).append(
                    CodeResourceUtil.DIVER_NAME).toString());
            Class.forName(CodeResourceUtil.DIVER_NAME);
            connection = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
                    CodeResourceUtil.PASSWORD);
            statement = connection.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
                sqlStr = (new StringBuilder(
                        "select column_name,data_type,column_comment,0,0 from information_schema.columns where table_name = '"))
                        .append(s.toUpperCase()).append("'").append(" and table_schema = '")
                        .append(CodeResourceUtil.DATABASE_NAME).append("'").toString();
            }
            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle"))
                sqlStr = (new StringBuilder(
                        "select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = '"))
                        .append(s.toUpperCase()).append("'").toString();
            resultSet = statement.executeQuery(sqlStr);
            resultSet.last();
            i = resultSet.getRow();
            return i > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 编辑表字段信息
     * 
     * @param columnt
     */
    private void editColumnt(Columnt columnt) {
        String fieldType = columnt.getFieldType();
        String scale = columnt.getScale();
        if ("N".equals(columnt.getNullable()))
            columnt.setOptionType("required:true");
        if ("datetime".equals(fieldType) || "time".equals(fieldType))
            columnt.setClassType("easyui-datetimebox");
        else if ("date".equals(fieldType))
            columnt.setClassType("easyui-datebox");
        else if ("int".equals(fieldType))
            columnt.setClassType("easyui-numberbox");
        else if ("number".equals(fieldType)) {
            if (StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0) {
                columnt.setClassType("easyui-numberbox");
                if (StringUtils.isNotBlank(columnt.getOptionType()))
                    columnt.setOptionType((new StringBuilder(String.valueOf(columnt.getOptionType()))).append(",")
                            .append("precision:2,groupSeparator:','").toString());
                else
                    columnt.setOptionType("precision:2,groupSeparator:','");
            } else {
                columnt.setClassType("easyui-numberbox");
            }
        } else if ("float".equals(fieldType) || "double".equals(fieldType) || "decimal".equals(fieldType)) {
            columnt.setClassType("easyui-numberbox");
            if (StringUtils.isNotBlank(columnt.getOptionType()))
                columnt.setOptionType((new StringBuilder(String.valueOf(columnt.getOptionType()))).append(",")
                        .append("precision:2,groupSeparator:','").toString());
            else
                columnt.setOptionType("precision:2,groupSeparator:','");
        } else {
            columnt.setClassType("easyui-validatebox");
        }
    }

    /**
     * 取得类型
     * @param type
     * @param longType
     * @param bigDecimalType
     * @return
     */
    private String getType(String type, String longType, String bigDecimalType) {
        if (type.contains("char"))
            type = "java.lang.String";
        else if (type.contains("int"))
            type = "java.lang.Integer";
        else if (type.contains("float"))
            type = "java.lang.Float";
        else if (type.contains("double"))
            type = "java.lang.Double";
        else if (type.contains("number")) {
            if (StringUtils.isNotBlank(bigDecimalType) && Integer.parseInt(bigDecimalType) > 0)
                type = "java.math.BigDecimal";
            else if (StringUtils.isNotBlank(longType) && Integer.parseInt(longType) > 10)
                type = "java.lang.Long";
            else
                type = "java.lang.Integer";
        } else if (type.contains("decimal"))
            type = "BigDecimal";
        else if (type.contains("date"))
            type = "java.util.Date";
        else if (type.contains("time"))
            type = "java.sql.Timestamp";
        else if (type.contains("clob"))
            type = "java.sql.Clob";
        else
            type = "java.lang.Object";
        return type;
    }
    /**
     * 测试用main函数
     * @param args
     */
    public static void main(String args[]) {
        try {
            List<Columnt> list = (new DbFiledToJspUtil()).readTableColumn("person");
            Columnt columnt;
            for (Iterator<Columnt> iterator = list.iterator(); iterator.hasNext(); System.out.println(columnt.getFieldName())) {
                columnt = iterator.next();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
