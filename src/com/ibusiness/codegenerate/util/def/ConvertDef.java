package com.ibusiness.codegenerate.util.def;

public abstract interface ConvertDef
{
  public static final String FIELD_NULL_ABLE_Y = "Y";
  public static final String FIELD_NULL_ABLE_N = "N";
  public static final String DATABASE_TYPE_MYSQL = "mysql";
  public static final String DATABASE_TYPE_ORACLE = "oracle";
  public static final String DATABASE_TYPE_SQL_SERVER = "sqlserver";
  public static final String mysql_db_sql = "select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}";
  public static final String oracle_db_sql = " select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}";
  public static final String sqlserver_db_sql = " select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}";
}

/* Location:           C:\Users\Administrator\Desktop\commons-cg-2.1.jar
 * Qualified Name:     com.util.def.ConvertDef
 * JD-Core Version:    0.5.4
 */