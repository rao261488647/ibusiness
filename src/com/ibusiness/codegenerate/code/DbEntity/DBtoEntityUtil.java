package com.ibusiness.codegenerate.code.DbEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.TableConvert;

public class DBtoEntityUtil {

    private static String a_java_lang_String_static_fld = "zhangdaihao";
    private static String b_java_lang_String_static_fld = "zhangdaiscott@163.com";
    private Connection a_java_sql_Connection_fld;
    private Statement a_java_sql_Statement_fld;
    private String c_java_lang_String_fld;
    private ResultSet a_java_sql_ResultSet_fld;
    private String a_java_lang_String_array1d_fld[];
    private String b_java_lang_String_array1d_fld[];
    private String c_java_lang_String_array1d_fld[];
    private String d[];
    private String e[];
    private String f[];
    private String g[];

    private void a1(String s) {
        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            a_java_sql_Connection_fld = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME,
                    CodeResourceUtil.PASSWORD);
            a_java_sql_Statement_fld = a_java_sql_Connection_fld.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql"))
                c_java_lang_String_fld = MessageFormat
                        .format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}",
                                new Object[] {
                                        TableConvert.getV(s.toUpperCase()),
                                        TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle"))
                c_java_lang_String_fld = MessageFormat
                        .format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}",
                                new Object[] {
                                    TableConvert.getV(s.toUpperCase()) });
            a_java_sql_ResultSet_fld = a_java_sql_Statement_fld.executeQuery(c_java_lang_String_fld);
            a_java_sql_ResultSet_fld.last();
            int i = a_java_sql_ResultSet_fld.getRow();
            int j = i;
            if (j > 0) {
                a_java_lang_String_array1d_fld = new String[j];
                b_java_lang_String_array1d_fld = new String[j];
                c_java_lang_String_array1d_fld = new String[j];
                d = new String[j];
                e = new String[j];
                f = new String[j];
                g = new String[j];
                a_java_lang_String_array1d_fld[--j] = a_java_sql_ResultSet_fld.getString(1);
                b_java_lang_String_array1d_fld[j] = a_java_sql_ResultSet_fld.getString(2);
                c_java_lang_String_array1d_fld[j] = a_java_sql_ResultSet_fld.getString(3);
                d[j] = a_java_sql_ResultSet_fld.getString(4);
                e[j] = a_java_sql_ResultSet_fld.getString(5);
                f[j] = a_java_sql_ResultSet_fld.getString(6);
                for (g[j] = a_java_sql_ResultSet_fld.getString(7); a_java_sql_ResultSet_fld.previous(); g[j] = a_java_sql_ResultSet_fld
                        .getString(7)) {
                    a_java_lang_String_array1d_fld[--j] = a_java_sql_ResultSet_fld.getString(1);
                    b_java_lang_String_array1d_fld[j] = a_java_sql_ResultSet_fld.getString(2);
                    c_java_lang_String_array1d_fld[j] = a_java_sql_ResultSet_fld.getString(3);
                    d[j] = a_java_sql_ResultSet_fld.getString(4);
                    e[j] = a_java_sql_ResultSet_fld.getString(5);
                    f[j] = a_java_sql_ResultSet_fld.getString(6);
                }

                int k = 0;
                for (int l = a_java_lang_String_array1d_fld.length; k < l; k++) {
                    String s1 = a_java_lang_String_array1d_fld[k];
                    a_java_lang_String_array1d_fld[k] = s1;
                    String s2 = b_java_lang_String_array1d_fld[k].toLowerCase();
                    String s3 = d[k];
                    String s4 = e[k];
                    s2 = a(s2, s3, s4);
                    b_java_lang_String_array1d_fld[k] = s2;
                    if ("".equals(c_java_lang_String_array1d_fld[k]) || c_java_lang_String_array1d_fld[k] == null)
                        c_java_lang_String_array1d_fld[k] = a_java_lang_String_array1d_fld[k];
                }

            } else {
            }
        } catch (ClassNotFoundException classnotfoundexception) {
        } catch (SQLException sqlexception) {
        }
        try {
            if (a_java_sql_Statement_fld != null) {
                a_java_sql_Statement_fld.close();
                a_java_sql_Statement_fld = null;
                System.gc();
            }
            if (a_java_sql_Connection_fld != null) {
                a_java_sql_Connection_fld.close();
                a_java_sql_Connection_fld = null;
                System.gc();
            }
        } catch (SQLException sqlexception1) {
        }
        try {
            if (a_java_sql_Statement_fld != null) {
                a_java_sql_Statement_fld.close();
                a_java_sql_Statement_fld = null;
                System.gc();
            }
            if (a_java_sql_Connection_fld != null) {
                a_java_sql_Connection_fld.close();
                a_java_sql_Connection_fld = null;
                System.gc();
            }
        } catch (SQLException sqlexception2) {
        }
        return;
    }

    private void a(String s, String s1, String s2, String s3) {
        a(s);
        a(s1, s);
        c(s1, s2, s3, s);
    }

    private void b(String s, String s1, String s2, String s3) {
        a(s);
        a(s1, s);
        d(s1, s2, s3, s);
    }

    private static String a(String s) {
        String as[] = s.split("_");
        s = "";
        int i = 0;
        for (int j = as.length; i < j; i++)
            if (i > 0) {
                String s1 = as[i].toLowerCase();
                s1 = (new StringBuilder(String.valueOf(s1.substring(0, 1).toUpperCase()))).append(
                        s1.substring(1, s1.length())).toString();
                s = (new StringBuilder(String.valueOf(s))).append(s1).toString();
            } else {
                s = (new StringBuilder(String.valueOf(s))).append(as[i].toLowerCase()).toString();
            }

        return s;
    }

    private String a(String s, String s1, String s2) {
        if (s.contains("char"))
            s = "java.lang.String";
        else if (s.contains("int"))
            s = "java.lang.Integer";
        else if (s.contains("float"))
            s = "java.lang.Float";
        else if (s.contains("double"))
            s = "java.lang.Double";
        else if (s.contains("number")) {
            if (StringUtils.isNotBlank(s2) && Integer.parseInt(s2) > 0)
                s = "java.math.BigDecimal";
            else if (StringUtils.isNotBlank(s1) && Integer.parseInt(s1) > 6)
                s = "java.lang.Long";
            else
                s = "java.lang.Integer";
        } else if (s.contains("decimal"))
            s = "BigDecimal";
        else if (s.contains("date"))
            s = "java.util.Date";
        else if (s.contains("time"))
            s = "java.sql.Timestamp";
        else if (s.contains("clob"))
            s = "java.sql.Clob";
        else
            s = "java.lang.Object";
        return s;
    }

    private String a(String s, String s1) {
        if ("".equals(s) || s == null) {
            s = "";
            String as[] = s1.split("_");
            int i = 0;
            for (int j = as.length; i < j; i++)
                s = (new StringBuilder(String.valueOf(s))).append(as[i].substring(0, 1).toUpperCase())
                        .append(as[i].substring(1, as[i].length()).toLowerCase()).toString();

        } else {
            s = (new StringBuilder(String.valueOf(s.substring(0, 1).toUpperCase()))).append(s.substring(1, s.length()))
                    .toString();
        }
        return s;
    }

    private void c(String s, String s1, String s2, String s3) {
        PrintWriter printwriter;
        s = (new StringBuilder(String.valueOf(s))).append("Entity").toString();
        printwriter = null;
        try {
            File file = new File(s1);
            if (!file.exists())
                file.mkdirs();
            File file1 = new File((new StringBuilder(String.valueOf(s1))).append(s).append(".java").toString());
            if (!file1.exists())
                file1.createNewFile();
            printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append((new StringBuilder("package ")).append(s2).append(";\n\n").toString());
            stringbuffer.append("import javax.persistence.Column;\n");
            stringbuffer.append("import javax.persistence.Entity;\n");
            stringbuffer.append("import javax.persistence.Table;\n");
            stringbuffer.append("import javax.persistence.Id;\n");
            stringbuffer.append("import java.math.BigDecimal;\n");
            stringbuffer.append("/**\n");
            stringbuffer.append((new StringBuilder(" *@\u7C7B:")).append(s).append("\n").toString());
            stringbuffer.append((new StringBuilder(" *@\u4F5C\u8005:")).append(a_java_lang_String_static_fld)
                    .append("\n").toString());
            stringbuffer.append((new StringBuilder(" *@E-mail:")).append(b_java_lang_String_static_fld).append("\n")
                    .toString());
            stringbuffer.append((new StringBuilder(" *@\u65E5\u671F:")).append((new Date()).toLocaleString())
                    .append("\n").toString());
            stringbuffer.append(" */\n\n");
            stringbuffer.append("@Entity\n");
            stringbuffer.append((new StringBuilder("@Table(name =\"")).append(s3).append("\")\n").toString());
            stringbuffer.append("@SuppressWarnings(\"serial\")\n");
            stringbuffer.append((new StringBuilder("public class ")).append(s)
                    .append(" implements java.io.Serializable {\n\n").toString());
            int i = 0;
            for (int j = a_java_lang_String_array1d_fld.length; i < j; i++) {
                stringbuffer.append((new StringBuilder("\t/**")).append(c_java_lang_String_array1d_fld[i])
                        .append("*/\n").toString());
                stringbuffer.append((new StringBuilder("\tprivate ")).append(b_java_lang_String_array1d_fld[i])
                        .append(" ").append(a(a_java_lang_String_array1d_fld[i])).append(";\n\n").toString());
            }

            i = 0;
            for (int k = a_java_lang_String_array1d_fld.length; i < k; i++) {
                stringbuffer.append("\t/**\n");
                stringbuffer.append((new StringBuilder("\t *\u65B9\u6CD5: \u53D6\u5F97"))
                        .append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append((new StringBuilder("\t *@return: ")).append(b_java_lang_String_array1d_fld[i])
                        .append("  ").append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append("\t */\n");
                if (CodeResourceUtil.CG_GENERATE_TABLE_ID.equals(a_java_lang_String_array1d_fld[i].toLowerCase())) {
                    stringbuffer.append("\t@Id\n");
                    stringbuffer.append((new StringBuilder("\t@Column(name =\""))
                            .append(CodeResourceUtil.CG_GENERATE_TABLE_ID.toUpperCase())
                            .append("\", nullable = false, length = 36)\n").toString());
                } else {
                    stringbuffer.append((new StringBuilder("\t@Column(name =\""))
                            .append(a_java_lang_String_array1d_fld[i].toLowerCase()).append("\")\n").toString());
                }
                stringbuffer.append((new StringBuilder("\tpublic "))
                        .append(b_java_lang_String_array1d_fld[i])
                        .append(" get")
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(0, 1).toUpperCase())
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(1,
                                a(a_java_lang_String_array1d_fld[i]).length())).append("(){\n").toString());
                stringbuffer.append((new StringBuilder("\t\treturn this."))
                        .append(a(a_java_lang_String_array1d_fld[i])).append(";\n").toString());
                stringbuffer.append("\t}\n\n");
                stringbuffer.append("\t/**\n");
                stringbuffer.append((new StringBuilder("\t *\u65B9\u6CD5: \u8BBE\u7F6E"))
                        .append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append((new StringBuilder("\t *@param: ")).append(b_java_lang_String_array1d_fld[i])
                        .append("  ").append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append("\t */\n");
                stringbuffer.append((new StringBuilder("\tpublic void set"))
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(0, 1).toUpperCase())
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(1,
                                a(a_java_lang_String_array1d_fld[i]).length())).append("(")
                        .append(b_java_lang_String_array1d_fld[i]).append(" ")
                        .append(a(a_java_lang_String_array1d_fld[i])).append("){\n").toString());
                stringbuffer.append((new StringBuilder("\t\tthis.")).append(a(a_java_lang_String_array1d_fld[i]))
                        .append(" = ").append(a(a_java_lang_String_array1d_fld[i])).append(";\n").toString());
                stringbuffer.append("\t}\n\n");
            }

            stringbuffer.append("}");
            printwriter.write(stringbuffer.toString());
            printwriter.flush();
        } catch (Exception e) {
            if (printwriter != null) {
                printwriter.close();
            }
        }
    }

    private void d(String s, String s1, String s2, String s3) {
        PrintWriter printwriter;
        s = (new StringBuilder(String.valueOf(s))).append("Page").toString();
        printwriter = null;
        try {
            File file = new File(s1);
            if (!file.exists())
                file.mkdirs();
            File file1 = new File((new StringBuilder(String.valueOf(s1))).append(s).append(".java").toString());
            if (!file1.exists())
                file1.createNewFile();
            printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append((new StringBuilder("package ")).append(s2).append(";\n\n").toString());
            stringbuffer.append("import com.core.base.BasePage;\n");
            stringbuffer.append("import java.math.BigDecimal;\n");
            stringbuffer.append("/**\n");
            stringbuffer.append((new StringBuilder(" *@\u7C7B:")).append(s).append("\n").toString());
            stringbuffer.append((new StringBuilder(" *@\u4F5C\u8005:")).append(a_java_lang_String_static_fld)
                    .append("\n").toString());
            stringbuffer.append((new StringBuilder(" *@E-mail:")).append(b_java_lang_String_static_fld).append("\n")
                    .toString());
            stringbuffer.append((new StringBuilder(" *@\u65E5\u671F:"))
                    .append((new Date()).toLocaleString().substring(0, 10)).append("\n").toString());
            stringbuffer.append(" */\n\n");
            stringbuffer.append("@SuppressWarnings(\"serial\")\n");
            stringbuffer.append((new StringBuilder("public class ")).append(s)
                    .append(" extends BasePage implements java.io.Serializable {\n\n").toString());
            int i = 0;
            for (int j = a_java_lang_String_array1d_fld.length; i < j; i++) {
                stringbuffer.append((new StringBuilder("\t/**")).append(c_java_lang_String_array1d_fld[i])
                        .append("*/\n").toString());
                stringbuffer.append((new StringBuilder("\tprivate ")).append(b_java_lang_String_array1d_fld[i])
                        .append(" ").append(a(a_java_lang_String_array1d_fld[i])).append(";\n\n").toString());
            }

            i = 0;
            for (int k = a_java_lang_String_array1d_fld.length; i < k; i++) {
                stringbuffer.append("\t/**\n");
                stringbuffer.append((new StringBuilder("\t *\u65B9\u6CD5: \u53D6\u5F97"))
                        .append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append((new StringBuilder("\t *@return: ")).append(b_java_lang_String_array1d_fld[i])
                        .append("  ").append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append("\t */\n");
                stringbuffer.append((new StringBuilder("\tpublic "))
                        .append(b_java_lang_String_array1d_fld[i])
                        .append(" get")
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(0, 1).toUpperCase())
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(1,
                                a(a_java_lang_String_array1d_fld[i]).length())).append("(){\n").toString());
                stringbuffer.append((new StringBuilder("\t\treturn this."))
                        .append(a(a_java_lang_String_array1d_fld[i])).append(";\n").toString());
                stringbuffer.append("\t}\n\n");
                stringbuffer.append("\t/**\n");
                stringbuffer.append((new StringBuilder("\t *\u65B9\u6CD5: \u8BBE\u7F6E"))
                        .append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append((new StringBuilder("\t *@param: ")).append(b_java_lang_String_array1d_fld[i])
                        .append("  ").append(a(a_java_lang_String_array1d_fld[i])).append("\n").toString());
                stringbuffer.append("\t */\n");
                stringbuffer.append((new StringBuilder("\tpublic void set"))
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(0, 1).toUpperCase())
                        .append(a(a_java_lang_String_array1d_fld[i]).substring(1,
                                a(a_java_lang_String_array1d_fld[i]).length())).append("(")
                        .append(b_java_lang_String_array1d_fld[i]).append(" ")
                        .append(a(a_java_lang_String_array1d_fld[i])).append("){\n").toString());
                stringbuffer.append((new StringBuilder("\t\tthis.")).append(a(a_java_lang_String_array1d_fld[i]))
                        .append(" = ").append(a(a_java_lang_String_array1d_fld[i])).append(";\n").toString());
                stringbuffer.append("\t}\n\n");
            }

            stringbuffer.append("}");
            printwriter.write(stringbuffer.toString());
            printwriter.flush();
        } catch (Exception e) {
            if (printwriter != null) {
                printwriter.close();
            }
            e.printStackTrace();
        }
    }

    public DBtoEntityUtil() {
    }

    public static void main(String args[]) {
        try {
            String s = "person";
            String s1 = "Specials";
            String s2 = "demo";
            createTablePage(s, s1, s2);
            System.out.println("SUCCESS .. ");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void createTableEntity(String s, String s1, String s2) {
        String s3 = (new StringBuilder(String.valueOf(CodeResourceUtil.ENTITY_URL))).append(s2).append("/").toString();
        String s4 = (new StringBuilder(String.valueOf(CodeResourceUtil.ENTITY_URL_INX))).append(s2).toString();
        (new DBtoEntityUtil()).a(s, s1, s3, s4);
    }

    public static void createTablePage(String s, String s1, String s2) {
        String s3 = (new StringBuilder(String.valueOf(CodeResourceUtil.PAGE_URL))).append(s2).append("/").toString();
        String s4 = (new StringBuilder(String.valueOf(CodeResourceUtil.PAGE_URL_INX))).append(s2).toString();
        (new DBtoEntityUtil()).b(s, s1, s3, s4);
    }

}
