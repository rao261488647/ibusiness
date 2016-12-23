package com.ibusiness.codegenerate.code.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibusiness.codegenerate.code.CodeParamBean;
import com.ibusiness.codegenerate.code.Columnt;
import com.ibusiness.codegenerate.code.NonceUtils;
import com.ibusiness.codegenerate.code.DbEntity.DbFiledToJspUtil;
import com.ibusiness.codegenerate.code.window.CreateFileProperty;
import com.ibusiness.codegenerate.common.CodeTagFactory;
import com.ibusiness.codegenerate.util.CodeDateUtils;
import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.def.FtlDef;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.TableCommonUtil;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * 非流程一对多生成器
 * 
 * @author JiangBo
 * 
 */
public class CodeGenerateOneToMany implements ICallBack {
    private static final Log log = LogFactory.getLog(CodeGenerateOneToMany.class);
    private static String entityPackage = "test";
    private static String entityName = "Person";
    private static String tableName = "person";
    private static String formName = "";
    private static String entityTitle = "用户";
    private static String primaryKeyPolicy = "uuid";
    private static String sequenceCode = "";
    public static String FTL_MODE_A = "A";
    public static String FTL_MODE_B = "B";
    private static List<CodeParamBean> subTabParamList = new ArrayList<CodeParamBean>();
    private static CreateFileProperty createFileProperty = new CreateFileProperty();
    public static int FIELD_ROW_NUM = 4;
    // 主表字段List
    private List<Columnt> mainColums = new ArrayList<Columnt>();
    private List<Columnt> originalColumns = new ArrayList<Columnt>();
    // 子表对应字段
//    private List<Columnt> subColums = new ArrayList<Columnt>();
    private DbFiledToJspUtil dbFiledToJspUtil = new DbFiledToJspUtil();
    
    static {
        createFileProperty.setActionFlag(false);
        createFileProperty.setServiceIFlag(false);
        createFileProperty.setJspFlag(false);
        createFileProperty.setResourceFlag(false);
        createFileProperty.setPageFlag(false);
        createFileProperty.setEntityFlag(false);
    }

    /**
     * 构造函数
     */
    public CodeGenerateOneToMany() {
    }
    public CodeGenerateOneToMany(CodeParamBean codeParamBean, CreateFileProperty fileProperty) {
        entityName = codeParamBean.getEntityName();
        entityPackage = codeParamBean.getPackageName();
        formName = codeParamBean.getFormName();
        tableName = codeParamBean.getTableName();
        entityTitle = codeParamBean.getEntityTitle();
        primaryKeyPolicy = (StringUtils.isNotBlank(codeParamBean.getKeyType())) ? codeParamBean.getKeyType() : "uuid";
        sequenceCode = "";
        createFileProperty = fileProperty;
        subTabParamList = codeParamBean.getSubParamBeans();
    }

    /**
     * 执行方法,此方法中返回模板用的Map数据
     */
    public Map<String, Object> execute() {
        // 向模板中设置Map数据
        // 设置各种数据信息向生成器模板用的Map中
        HashMap<String, Object> localHashMap = new HashMap<String, Object>();
        localHashMap.put("bussiPackage", CodeResourceUtil.bussiPackage);
        // 包名
        localHashMap.put("entityPackage", entityPackage);
        // 实体名
        localHashMap.put("entityName", entityName);
        // 表名
        localHashMap.put("tableName", tableName);
        // 表描述
        localHashMap.put("ftl_description", entityTitle);
        localHashMap.put(FtlDef.CG_TABLE_ID, CodeResourceUtil.CG_GENERATE_TABLE_ID);
        // 主键
        localHashMap.put(FtlDef.CG_PRIMARY_KEY_POLICY, primaryKeyPolicy);
        // SEQUENCE
        localHashMap.put(FtlDef.CG_SEQUENCE_CODE, sequenceCode);
        // 创建时间
        localHashMap.put("ftl_create_time", CodeDateUtils.dateToString(new Date()));
        localHashMap.put(FtlDef.FIELD_REQUIRED_NAME, Integer.valueOf((StringUtils.isNotEmpty(CodeResourceUtil.CG_UI_FIELD_REQUIRED_NUM)) ? Integer
                .parseInt(CodeResourceUtil.CG_UI_FIELD_REQUIRED_NUM) : -1));
        localHashMap.put(FtlDef.SEARCH_FIELD_NUM, Integer.valueOf((StringUtils.isNotEmpty(CodeResourceUtil.CG_UI_FIELD_SEARCH_NUM)) ? Integer
                .parseInt(CodeResourceUtil.CG_UI_FIELD_SEARCH_NUM) : -1));
        // 行字段数目
        localHashMap.put(FtlDef.FIELD_ROW_NAME, Integer.valueOf(FIELD_ROW_NUM));
        try {
            createFileProperty.isEntityFlag();
            createFileProperty.isPageFlag();
        } catch (Exception e) {
            log.error("===================" + e.toString());
        }
        try {
            // 取得主表字段数据
            // 取得表字段list 根据表名
            this.mainColums = getColumListByTableName(tableName, formName);
            // 读取指定表名的表字段List
            // 取得表字段信息
            Map<String, ConfTableColumns> tableColumnsMap = CommonBusiness.getInstance().getTableColumnsMap(tableName);
            if (tableColumnsMap.size() > 0) {
                for (Columnt columnt : this.mainColums) {
                    String key = columnt.getFieldDbName();
                    if (tableColumnsMap.containsKey(key)) {
                        columnt.setFiledComment(tableColumnsMap.get(key).getColumnName());
                    }
                }
            }
            // 设置表字段信息
            localHashMap.put("columns", this.mainColums);
            
            // 取得表单信息
            List<ConfForm> formList = CommonBusiness.getInstance().getFormList(formName);
            // 表单信息
            localHashMap.put("confForm", formList.get(0));
            
            // 取得表单对应表管理表信息
            List<ConfFormTableColumn> formTableColumnList = CommonBusiness.getInstance().getFormTableColumnList(tableName, formName);
            for (ConfFormTableColumn formTableColumn : formTableColumnList) {
                // 转换小写
                formTableColumn.setTableColumnLower(formTableColumn.getTableColumn().toLowerCase());
            }
            // 表单字段信息
            localHashMap.put("formTableColumnList", formTableColumnList);
            
            // 读取指定表名的表字段(原值)List
            this.originalColumns = this.dbFiledToJspUtil.readOriginalTableColumn(tableName);
            if (tableColumnsMap.size() > 0) {
                for (Columnt columnt : this.originalColumns) {
                    String key = columnt.getFieldDbName();
                    if (tableColumnsMap.containsKey(key)) {
                        columnt.setFiledComment(tableColumnsMap.get(key).getColumnName());
                    }
                }
            }
            localHashMap.put("originalColumns", this.originalColumns);
            // 设置值
            for (Columnt columnt : originalColumns) {
                if (!columnt.getFieldName().toLowerCase().equals(CodeResourceUtil.CG_GENERATE_TABLE_ID.toLowerCase())) {
                    continue;
                }
                localHashMap.put("primary_key_type", columnt.getFieldType());
            }
            //========= 子表内容生成 =========================================//
            for (CodeParamBean codeParamBean : subTabParamList) {
                // 取得表字段list 根据表名
                List<Columnt> subColumlist = getColumListByTableName(codeParamBean.getTableName(), formName);
                codeParamBean.setColumns(subColumlist);
                // 表对应全字段信息
                List<Columnt> subOriginalColumns = this.dbFiledToJspUtil.readOriginalTableColumn(codeParamBean.getTableName());
                if (tableColumnsMap.size() > 0) {
                    for (Columnt columnt : subOriginalColumns) {
                        String key = columnt.getFieldDbName();
                        if (tableColumnsMap.containsKey(key)) {
                            columnt.setFiledComment(tableColumnsMap.get(key).getColumnName());
                        }
                    }
                }
                codeParamBean.setOriginalColumns(subOriginalColumns);
            }
            // 将子表内容存入
            localHashMap.put("subTab", subTabParamList);
        } catch (Exception e) {
            log.error("===================" + e.toString());
        }
        long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();
        localHashMap.put("serialVersionUID", String.valueOf(serialVersionUID));
        return localHashMap;
    }
    /**
     * 取得字段list 根据表名
     * @param tableName
     * @return
     */
    private List<Columnt> getColumListByTableName(String tableName, String formName) {
        // 取得子表对应表字段信息
        List<ConfTableColumns> subTableColumnsList = CommonBusiness.getInstance().getTableColumnsList(tableName);
        Map<String, ConfFormTableColumn> formTableColumnMap = CommonBusiness.getInstance().getFormTableColumnMap(tableName, formName);
        List<Columnt> subColumlist = new ArrayList<Columnt>();
        for (ConfTableColumns confTableColumns : subTableColumnsList) {
            // 表中埋的字段不予设置
            if (TableCommonUtil.getReservedColumnsMap().containsKey(confTableColumns.getColumnValue())) {
                continue;
            }
            Columnt columnt = new Columnt();
            // 字段名
            columnt.setFieldDbName(confTableColumns.getColumnValue());
            //
            columnt.setFieldType(confTableColumns.getColumnType());
            columnt.setCharmaxLength(confTableColumns.getColumnSize());
            // 字段名
            columnt.setFieldName(DbFiledToJspUtil.formatField(confTableColumns.getColumnValue().toLowerCase()));
            // 字段标题
            columnt.setFiledComment(confTableColumns.getColumnName());
            // 设置表单相关字段信息
            ConfFormTableColumn formColumn = formTableColumnMap.get(confTableColumns.getColumnValue());
            if (null != formColumn) {
                // 录入宽度
                columnt.setFcWidth(formColumn.getFcWidth());
                // 录入高度
                columnt.setFcHeight(formColumn.getFcHeight());
                // 是否显示
                columnt.setFcDisplay(formColumn.getFcDisplay());
                // 是否编辑
                columnt.setFcEdit(formColumn.getFcEdit());
                // 组件类型
                columnt.setFcType(formColumn.getFcType());
                
                // 根据标签类型生成标签
                columnt = CodeTagFactory.getInstance().CreateTagComponent(columnt, formColumn);
            }
            if (null != columnt) {
                subColumlist.add(columnt);
            }
        }
        return subColumlist;
    }

    /**
     * 生成文件
     */
    public void generateToFile() {
        CodeFactoryBpmForm codeFactoryOneToMany = new CodeFactoryBpmForm();
        codeFactoryOneToMany.setCallBack(new CodeGenerateOneToMany());
        if (createFileProperty.isJspFlag()) {
            codeFactoryOneToMany.invoke("onetomany/jspInputOneToMTemplate.ftl", "jsp");
            codeFactoryOneToMany.invoke("onetomany/jspListOneToMTemplate.ftl", "jspList");
        }
        // resource接口文件
        if (createFileProperty.isResourceFlag()) {
            codeFactoryOneToMany.invoke("onetomany/resourceOneToMTemplate.ftl", "resource");
            // 生成html5页面
            codeFactoryOneToMany.invoke("onetomany/htmlListTemplate.ftl", "htmlList");
            codeFactoryOneToMany.invoke("onetomany/htmlInputTemplate.ftl", "html");
        }
        // Service文件
        if (createFileProperty.isServiceIFlag()) {
            codeFactoryOneToMany.invoke("onetomany/serviceOneToMTemplate.ftl", "service");
        }
        // controller文件
        if (createFileProperty.isActionFlag()) {
            codeFactoryOneToMany.invoke("onetomany/controllerOneToMTemplate.ftl", "controller");
        }
        // entity文件
        if (createFileProperty.isEntityFlag()) {
            codeFactoryOneToMany.invoke("onetomany/entityOneToMTemplate.ftl", "entity");
        }
    }

    public static void main(String[] paramArrayOfString) {
    }
}
