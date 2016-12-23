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
import com.ibusiness.codegenerate.common.CodeBpmTagFactory;
import com.ibusiness.codegenerate.common.CodeTagFactory;
import com.ibusiness.codegenerate.util.CodeDateUtils;
import com.ibusiness.codegenerate.util.CodeResourceUtil;
import com.ibusiness.codegenerate.util.def.FtlDef;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.TableCommonUtil;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * 一对一代码生成器
 * 
 * @author JiangBo
 * 
 */
public class CodeGenerate implements ICallBack {
    /**
     * log
     */
    private static final Log log = LogFactory.getLog(CodeGenerate.class);
    private static String entityPackage = "test";
    private static String entityName = "Company";
    private static String mainEntityName = "mainCompany";
    private static String formName = "";
    private static String tableName = "person";
    private static String tableDescription = "分公司";
    private static String primaryKeyPolicy = "uuid";
    private static String sequenceCode = "";
    // 表字段List
    private List<Columnt> columns = new ArrayList<Columnt>();
    // 表字段(原值)List
    private List<Columnt> originalColumns = new ArrayList<Columnt>();
    public static int FIELD_ROW_NUM = 1;
    private static CreateFileProperty createFileProperty = new CreateFileProperty();
    
    // JSP通用数据文件实体
    private DbFiledToJspUtil dbFiledToJspUtil = new DbFiledToJspUtil();

    static {
        createFileProperty.setActionFlag(true);
        createFileProperty.setServiceIFlag(true);
        createFileProperty.setJspFlag(true);
        createFileProperty.setResourceFlag(true);
        createFileProperty.setJspMode("01");
        createFileProperty.setPageFlag(true);
        createFileProperty.setEntityFlag(true);
    }

    /**
     * 构造函数
     */
    public CodeGenerate() {
    }
    public CodeGenerate(CodeParamBean codeParamBean, CreateFileProperty createFileProperty) {
        CodeGenerate.entityName = codeParamBean.getEntityName();
        if (!CommonUtils.isNull(codeParamBean.getMainEntityName())) {
        	CodeGenerate.mainEntityName = codeParamBean.getMainEntityName();
        }
        this.originalColumns = codeParamBean.getOriginalColumns();
        CodeGenerate.formName = codeParamBean.getFormName();
        CodeGenerate.entityPackage = codeParamBean.getPackageName();
        CodeGenerate.tableName = codeParamBean.getTableName();
        CodeGenerate.tableDescription = codeParamBean.getEntityTitle();
        CodeGenerate.createFileProperty = createFileProperty;
        CodeGenerate.FIELD_ROW_NUM = Integer.parseInt(CommonUtils.isNull(codeParamBean.getRowNumber())? "1":codeParamBean.getRowNumber());
        CodeGenerate.primaryKeyPolicy = codeParamBean.getKeyType();
        CodeGenerate.sequenceCode = "";
    }
    /**
     * 执行方法,此方法中设置模板用的Map数据
     */
    public Map<String, Object> execute() {
        // 设置各种数据信息向生成器模板用的Map中
        HashMap<String, Object> localHashMap = new HashMap<String, Object>();
        localHashMap.put("bussiPackage", CodeResourceUtil.bussiPackage);
        // 包名
        localHashMap.put("entityPackage", entityPackage);
        // 实体名
        localHashMap.put("entityName", entityName);
        // 当前实体的主实体名(用于子表生成时)
        localHashMap.put("mainEntityName", mainEntityName);
        // 表名
        localHashMap.put("tableName", tableName);
        // 表描述
        localHashMap.put("ftl_description", tableDescription);
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
            // 读取指定表名的表字段List
            this.columns = getColumListByTableName(tableName, formName);
            // 取得表字段信息
            Map<String, ConfTableColumns> tableColumnsMap = CommonBusiness.getInstance().getTableColumnsMap(tableName);
            if (tableColumnsMap.size() > 0) {
                for (Columnt columnt : this.columns) {
                	if (null == columnt) {
                		continue;
                	}
                    String key = columnt.getFieldDbName();
                    if (tableColumnsMap.containsKey(key)) {
                        columnt.setFiledComment(tableColumnsMap.get(key).getColumnName());
                    }
                }
            }
            // 设置表字段信息
            localHashMap.put("columns", this.columns);
            
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
            if (null == this.originalColumns || this.originalColumns.size() < 1) {
            	this.originalColumns = this.dbFiledToJspUtil.readOriginalTableColumn(tableName);
            	if (tableColumnsMap.size() > 0) {
                    for (Columnt columnt : this.originalColumns) {
                        String key = columnt.getFieldDbName();
                        if (tableColumnsMap.containsKey(key)) {
                            columnt.setFiledComment(tableColumnsMap.get(key).getColumnName());
                        }
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
            // 表埋的保留字段不予设置
            if (TableCommonUtil.getReservedColumnsMap().containsKey(confTableColumns.getColumnValue())) {
                continue;
            }
            Columnt columnt = new Columnt();
            // 字段名
            columnt.setFieldDbName(confTableColumns.getColumnValue());
            // 字段类型
            columnt.setFieldType(confTableColumns.getColumnType());
            // 长度
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
                if ("bpmSub".equals(createFileProperty.getJspMode())) {
                    columnt = CodeBpmTagFactory.getInstance().CreateTagComponent(columnt, formColumn);
                } else {
                    columnt = CodeTagFactory.getInstance().CreateTagComponent(columnt, formColumn);
                }
            }
            
            // ADD
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
        log.info("----Code----Generation----[单表模型:" + tableName + "]------- 生成中。。。");
        // 代码生成工厂类
        CodeFactory codeFactory = new CodeFactory();
        codeFactory.setCallBack(new CodeGenerate());
        // JSP文件
        if (createFileProperty.isJspFlag()) {
            if ("01".equals(createFileProperty.getJspMode())) {
                codeFactory.invoke("jspListTemplate.ftl", "jspList");
                codeFactory.invoke("jspInputTemplate.ftl", "jsp");
            } else if ("sub".equals(createFileProperty.getJspMode())) {
                // 子表生成
                codeFactory.invoke("onetomany/jspSubInputOneToMTemplate.ftl", "jsp");
                codeFactory.invoke("onetomany/htmlSubInputOneToMTemplate.ftl", "html");
            } else if ("bpmSub".equals(createFileProperty.getJspMode())) {
                // 流程子表生成
                codeFactory.invoke("bpm/jspSubInputOneToMTemplate.ftl", "jsp");
            }
        }
        // resource接口文件
        if (createFileProperty.isResourceFlag()) {
            // 生成接口文件
            codeFactory.invoke("resourceTemplate.ftl", "resource");
            // 生成html5页面
            codeFactory.invoke("htmlListTemplate.ftl", "htmlList");
            codeFactory.invoke("htmlInputTemplate.ftl", "html");
        }
        // ServiceI文件
        if (createFileProperty.isServiceIFlag()) {
            codeFactory.invoke("serviceTemplate.ftl", "service");
        }
        // controller文件
        if (createFileProperty.isActionFlag()) {
            codeFactory.invoke("controllerTemplate.ftl", "controller");
        }
        // entity文件
        if (createFileProperty.isEntityFlag()) {
            codeFactory.invoke("entityTemplate.ftl", "entity");
        }
        // page文件
        if (createFileProperty.isPageFlag()) {
            codeFactory.invoke("pageEntityTemplate.ftl", "page");
        }
        log.info("----Code----Generation-----[单表模型：" + tableName + "]------ 生成完成。。。");
    }

    /**
     * main测试方法
     * @param arg
     */
    public static void main(String[] arg) {
        System.out.println("----Code------------- Generation -----[单表模型]------- 生成中。。。");
        new CodeGenerate().generateToFile();
        System.out.println("----Code------------- Generation -----[单表模型]------- 生成完成。。。");
    }
    // ===============================================
}
