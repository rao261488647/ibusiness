package com.ibusiness.component.table.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibusiness.base.menu.dao.MenuDao;
import com.ibusiness.base.menu.entity.Menu;
import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.common.service.TableCommonUtil;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.form.dao.ConfFormDao;
import com.ibusiness.component.form.dao.ConfFormTableColumnDao;
import com.ibusiness.component.form.dao.ConfFormTableDao;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTable;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.entity.ConfTable;
import com.ibusiness.component.table.entity.ConfTableColumns;
import com.ibusiness.component.table.service.ConfTableColumnsService;
import com.ibusiness.component.table.service.ConfTableService;
import com.ibusiness.flowchart.entity.ConfFlowChart;
import com.ibusiness.flowchart.service.FlowChartService;

/**
 * 数据库表导出
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("export-table")
public class ExportTableController {

    private ConfFormDao confFormDao;
    private ConfFormTableDao confFormTableDao;
    private ConfFormTableColumnDao confFormTableColumnDao;
    private ConfTableColumnsService confTableColumnsService;
    private ConfTableService confTableService;
    
    private MenuDao menuDao;
    
    private BpmProcessDao bpmProcessDao;
    private FlowChartService flowChartService;
    
    /**
     * 显示导表页面
     * 
     * @param model
     * @return
     */
    @RequestMapping("export-table-show")
    public String exportTableShow(Model model) {
        // 在controller中设置页面控件用的数据
        String formidSql = "select id vKey, formTitle vValue from IB_CONF_FORM Order by formTitle ";
        List<Map<String, Object>> formidList = com.ibusiness.core.spring.ApplicationContextHelper
                .getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate()
                .queryForList(formidSql);
        List<ConfSelectItem> formidItems = new java.util.ArrayList<ConfSelectItem>();
        for (Map<String, Object> mapBean : formidList) {
            ConfSelectItem confSelectItem = new ConfSelectItem();
            confSelectItem.setKey(mapBean.get("vKey").toString());
            confSelectItem.setValue(mapBean.get("vValue").toString());
            formidItems.add(confSelectItem);
        }
        model.addAttribute("formidItems", formidItems);
        // 
        String flowidSql = "select id vKey, flowTitle vValue from IB_BPM_PROCESS Order by flowTitle ";
        List<Map<String, Object>> flowidList = com.ibusiness.core.spring.ApplicationContextHelper
                .getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate()
                .queryForList(flowidSql);
        List<ConfSelectItem> flowidItems = new java.util.ArrayList<ConfSelectItem>();
        for (Map<String, Object> mapBean : flowidList) {
            ConfSelectItem confSelectItem = new ConfSelectItem();
            confSelectItem.setKey(mapBean.get("vKey").toString());
            confSelectItem.setValue(mapBean.get("vValue").toString());
            flowidItems.add(confSelectItem);
        }
        model.addAttribute("flowidItems", flowidItems);

        model.addAttribute("filepath", "D:/export.sql");
        return "ibusiness/component/exporttable/export-table.jsp";
    }
    /**
     * 导出SQL
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("export-sql")
    public String exportSql(@RequestParam("formId") String formId, @RequestParam("flowId") String flowId, 
            @RequestParam("filePath") String filePath, Model model) {
        // 菜单
        String menuurl="";
        if (!CommonUtils.isNull(flowId)) {
            // 6.导出 流程
            BpmProcess bpmProcess = bpmProcessDao.get(flowId);
            String bpmSql = "/*======= 流程 ==========*/\r\n";
            bpmSql = bpmSql + "INSERT INTO IB_BPM_PROCESS(ID,PACKAGENAME,FLOWNAME,FLOWTITLE,VERSIONID, FORMID,FLOWURL) VALUES(";
            bpmSql = bpmSql + "'" + bpmProcess.getId() + "','" + bpmProcess.getPackageName() + "','" + bpmProcess.getFlowName() + "','" + bpmProcess.getFlowTitle() + "','" + bpmProcess.getVersionId();
            bpmSql = bpmSql + "','" + bpmProcess.getFormId() + "','" + bpmProcess.getFlowUrl() + "');\r\n";
            // 写文件
            fileInputByStr(bpmSql, filePath);
            menuurl = bpmProcess.getFlowUrl();
            
            // 设置表单ID
            formId = bpmProcess.getFormId();
            // 7.导出 流程图
            List<ConfFlowChart> flowChartList = flowChartService.find("from ConfFlowChart where flowId=?", flowId);
            String flowChartSql = "/*======= 流程图 ==========*/\r\n";
            for (ConfFlowChart flowChart : flowChartList) {
                flowChartSql = flowChartSql + "insert into ib_conf_flow_chart(ID,FLOWID,ITEMID,NODEID,CONTEXT) values (";
                flowChartSql = flowChartSql + "'" + flowChart.getId() + "','" + flowChart.getFlowId() + "','" + flowChart.getItemId() + "','" + flowChart.getNodeId() + "','" + flowChart.getContext();
                flowChartSql = flowChartSql + "');\r\n";
            }
            // 写文件
            fileInputByStr(flowChartSql, filePath);
        }
        
        // 1.导出 表单
        ConfForm confForm = confFormDao.get(formId);
        String formName = confForm.getFormName();
        String formSql = "/*======= 表单 ==========*/\r\n";
        formSql = formSql + " delete from ib_conf_form where FORMNAME='" + confForm.getFormName() + "';\r\n";
        formSql = formSql + " insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT"
                + ",ISIMPORTEXPORT,ISBPMFORM) values ("; 
        formSql = formSql + "'" + confForm.getId() + "','" + confForm.getPackageName() + "','" + confForm.getFormName() + "','" + confForm.getFormTitle() + "','" + confForm.getFormURL();
        formSql = formSql +  "'," + confForm.getIsEdit() + "," + confForm.getIsAdd() + "," + confForm.getIsDelete() + "," + confForm.getIsQuery() + "," + confForm.getIsExcelExport();
        formSql = formSql +  "," + confForm.getIsImportExport() + "," + confForm.getIsBpmForm();
        formSql = formSql + ");\r\n";
        
        if (CommonUtils.isNull(menuurl)) {
            menuurl = confForm.getFormURL();
        }
        
        // 2.表单对应数据表
        List<ConfFormTable> formTableList = confFormTableDao.find("from ConfFormTable where formName=?", formName);
        String formTableSql = "/*======= 表单对应表 ==========*/\r\n";
        formTableSql = formTableSql + " delete from ib_conf_form_table where FORMNAME='" + formName + "';\r\n";
        for (ConfFormTable formTable : formTableList) {
            formTableSql = formTableSql + "insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values (";
            formTableSql = formTableSql + "'" + formTable.getPackageName() + "','" + formTable.getFormName() + "','" + formTable.getTableName() + "','" + formTable.getTableType();
            formTableSql = formTableSql + "');\r\n";
        }
        
        // 3.导出 表单字段
        List<ConfFormTableColumn> formTableColumnList = confFormTableColumnDao.find("from ConfFormTableColumn where formName=? ORDER BY tablename, columnno ", formName);
        String formTableColumsSql = "/*======= 表单字段 ==========*/\r\n";
        formTableColumsSql = formTableColumsSql + " delete from ib_conf_form_table_colums where FORMNAME='" + formName + "';\r\n";
        for (ConfFormTableColumn bean : formTableColumnList) {
            formTableColumsSql = formTableColumsSql + "insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,"
                    + "FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values (";
            formTableColumsSql = formTableColumsSql + "'" + bean.getPackageName() + "','" + bean.getFormName() + "','" + bean.getFormColumn() + "','" + bean.getFormColumnTitle() + "','" + bean.getTableColumn();
            formTableColumsSql = formTableColumsSql + "','" + bean.getTableName() + "'," + bean.getColumnNo() + ",'" + bean.getFcType() + "','" + bean.getFcWidth() + "','" + bean.getFcHeight();
            formTableColumsSql = formTableColumsSql + "','" + bean.getFcDisplay() + "','" + bean.getFcEdit() + "','" + bean.getFcQuery() + "','" + bean.getFcMust() + "','" + bean.getFcDefault();
            formTableColumsSql = formTableColumsSql + "','" + bean.getConfSelectInfo() + "');\r\n";
        }
        
        // 4.导出 表
        String tableSql = "/*======= 表 ==========*/\r\n";
        for (ConfFormTable formTable : formTableList) {
            List<ConfTable> tableList = confTableService.find("from ConfTable where tableName=?", formTable.getTableName());
            tableSql = tableSql + " delete from ib_conf_table where TABLENAME='" + formTable.getTableName() + "';\r\n";
            for (ConfTable table : tableList) {
                tableSql = tableSql + "insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values (";
                tableSql = tableSql + "'" + table.getId() + "','" + table.getPackageName() + "','" + table.getTableName() + "','" + table.getTableNameComment() + "','" + table.getTableType();
                tableSql = tableSql + "','" + table.getParentTableId() + "'," + table.getIsBpmTable() + ");\r\n";
            }
        }
        
        // 5.导出 表字段
        String tableColumnsSql = "/*======= 表字段 ==========*/\r\n";
        String createSql ="/*======= 创建表 ==========*/\r\n";
        for (ConfFormTable formTable : formTableList) {
            List<ConfTableColumns> tableColumnsList = confTableColumnsService.find("from ConfTableColumns where tableName=? ORDER BY columnno", formTable.getTableName());
            tableColumnsSql = tableColumnsSql + " delete from ib_conf_table_columns where TABLENAME='"+formTable.getTableName()+"';\r\n";
            for (ConfTableColumns bean : tableColumnsList) {
                tableColumnsSql = tableColumnsSql + "insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values (";
                tableColumnsSql = tableColumnsSql + "'" + bean.getTableName() + "','" + bean.getColumnValue() + "','" + bean.getColumnName() + "','" + bean.getColumnType() + "','" + bean.getColumnSize();
                tableColumnsSql = tableColumnsSql + "','" + bean.getIsNull() + "','" + bean.getDefaultValue() + "'," + bean.getColumnNo() + ");\r\n";
            }
            // 生成建表语句
            if (null != tableColumnsList && tableColumnsList.size() > 0) {
                // 创建表
                createSql = createSql + "DROP TABLE IF EXISTS " + formTable.getTableName() + ";\r\n";
                createSql = createSql + "CREATE TABLE " + formTable.getTableName() + " (";
                for (ConfTableColumns bean : tableColumnsList) {
                    String columnType = TableCommonUtil.getInstance().getColumnTypeByDataType(bean.getColumnType(), bean.getColumnSize());
                    createSql = createSql + bean.getColumnValue() + " " + columnType;
                    createSql = createSql + ", ";
                };
                createSql = createSql +  " PRIMARY KEY (ID)) ENGINE=INNODB;\r\n";
            }
        }
        // 写文件 == 创建表 ===
        fileInputByStr(createSql, filePath);
        // 写文件 == 表单 ===
        fileInputByStr(formSql.replaceAll("'null'", "''"), filePath);
        // 写文件 === 表单对应表 ===
        fileInputByStr(formTableSql, filePath);
        // 写文件 === 表单字段 ==
        fileInputByStr(formTableColumsSql.replaceAll("'null'", "''"), filePath);
        // 写文件 == 表 ==
        fileInputByStr(tableSql, filePath);
        // 写文件 == 表字段 =
        fileInputByStr(tableColumnsSql.replaceAll("'null'", "''"), filePath);
        
        // 菜单
        List<Menu> menuList = menuDao.find("from Menu where menuUrl=?", menuurl);
        String menuSql = "/*======= 菜单 ==========*/\r\n";
        for (Menu menu : menuList) {
            menuSql = menuSql + "delete from IB_MENU where ID='"+menu.getId()+"';\r\n";
            menuSql = menuSql + "INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES(";
            menuSql = menuSql + "'" + menu.getId() + "','" + menu.getMenuName() + "','" + menu.getMenuLevel() + "','" + menu.getMenuUrl() + "','" + menu.getMenuIframe();
            menuSql = menuSql + "','" + menu.getMenuOrder() + "','" + menu.getIbMenu().getId() + "');\r\n";
        }
        fileInputByStr(menuSql, filePath);
        
        return "redirect:/export-table/export-table-show.do"; 
    }
    
    /**
     * 利用StringBuffer写文件
     * @throws IOException
     */
    public void fileInputByStr(String str, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(file, true);
            out.write(str.getBytes("utf-8"));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ============================================================================================
    /**
     * 注入 table service
     */
    @Resource
	public void setConfFormDao(ConfFormDao confFormDao) {
		this.confFormDao = confFormDao;
	}
    @Resource
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    @Resource
    public void setConfFormTableColumnDao(ConfFormTableColumnDao confFormTableColumnDao) {
        this.confFormTableColumnDao = confFormTableColumnDao;
    }
    @Resource
    public void setConfFormTableDao(ConfFormTableDao confFormTableDao) {
        this.confFormTableDao = confFormTableDao;
    }
    @Resource
    public void setConfTableColumnsService(ConfTableColumnsService confTableColumnsService) {
        this.confTableColumnsService = confTableColumnsService;
    }
    @Resource
    public void setConfTableService(ConfTableService confTableService) {
        this.confTableService = confTableService;
    }
    @Resource
    public void setBpmProcessDao(BpmProcessDao bpmProcessDao) {
        this.bpmProcessDao = bpmProcessDao;
    }
    @Resource
    public void setFlowChartService(FlowChartService flowChartService) {
        this.flowChartService = flowChartService;
    }
}
