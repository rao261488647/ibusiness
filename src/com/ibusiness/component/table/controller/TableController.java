package com.ibusiness.component.table.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.common.service.TableCommonUtil;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.form.dao.ConfFormTableColumnDao;
import com.ibusiness.component.form.dao.ConfFormTableDao;
import com.ibusiness.component.form.entity.ConfFormTable;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.dao.TableColumnsDao;
import com.ibusiness.component.table.entity.ConfTable;
import com.ibusiness.component.table.entity.ConfTableColumns;
import com.ibusiness.component.table.service.ConfTableColumnsService;
import com.ibusiness.component.table.service.TableService;
import com.ibusiness.core.spring.MessageHelper;

/**
 * 数据库表结构管理
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("table")
public class TableController {

    // 共用Service,可以查询热力站,热源,BPM等基础信息
    private TableService tableService;
    private TableColumnsDao tableColumnsDao;
    private ConfFormTableDao confFormTableDao;
    private ConfFormTableColumnDao confFormTableColumnDao;
    private ConfTableColumnsService confTableColumnsService;
    private MessageHelper messageHelper;

    /**
     * 业务表管理表信息查询
     * 
     * @return
     */
    @RequestMapping("conf-table-list")
    public String confTableShow(@RequestParam("packageName") String packageName, Model model) {
        // 取得表结构信息。
        List<ConfTable> list = tableService.queryConfTableList(packageName,"2");
        // 表结构信息
        model.addAttribute("tableInfoList", list);
        model.addAttribute("packageName", packageName);
        model.addAttribute("typeId", "tables");
        
        return "ibusiness/component/table/conf-table-list.jsp"; 
    }
    @RequestMapping("conf-bpmTable-list")
    public String confBpmTableShow(@RequestParam("packageName") String packageName, Model model) {
        // 取得表结构信息。
        List<ConfTable> list = tableService.queryConfTableList(packageName,"1");
        // 表结构信息
        model.addAttribute("tableInfoList", list);
        model.addAttribute("packageName", packageName);
        model.addAttribute("typeId", "BpmTable");
        
        return "ibusiness/component/table/conf-bpmTable-list.jsp"; 
    }
    
    /**
     * 表列字段结构管理表信息查询
     * 
     * @return
     */
    @RequestMapping("conf-table-column-list")
    public String queryConfTableDetail(@RequestParam("tableName") String tableName, Model model) {
    	// 取得表结构信息
        List<ConfTableColumns> list = tableService.queryConfTableColumns(tableName);
        // 表结构信息
        model.addAttribute("tableInfoList", list);
        model.addAttribute("tableName", tableName);
        List<ConfTable> tableList = tableService.queryConfTableByTableName(tableName);
        if (null != tableList && tableList.size() > 0) {
            model.addAttribute("packageName", tableList.get(0).getPackageName());
            if (1 == tableList.get(0).getIsBpmTable()) {
                model.addAttribute("typeId", "BpmTable");
            } else {
                model.addAttribute("typeId", "Table");
            }
        }
        
        // 取得表结构信息
        return "ibusiness/component/table/conf-table-column-list.jsp";
    }
    /**
     * 表列字段结构管理表信息查询
     * 
     * @return
     */
    @RequestMapping("conf-table-column-input")
    public String queryConfTableColumn(@RequestParam("tableName") String tableName, @RequestParam("columnValue") String columnValue, Model model) {
    	// 取得表结构信息
        ConfTableColumns bean = tableService.queryConfTableColumn(tableName, columnValue);
        // 如果是新建的字段自动生成表字段排序
        int columnNo = 1;
        if (null == bean || 1 < bean.getColumnNo()) {
            ConfTableColumns maxColumnNoBean = tableService.queryMaxTableColumnNo(tableName);
            if (null != maxColumnNoBean && maxColumnNoBean.getColumnNo() > 0) {
                columnNo = maxColumnNoBean.getColumnNo() + 1;
            }
        }
        // 表结构信息
        model.addAttribute("beanInfo", bean);
        model.addAttribute("columnNo", columnNo);
        model.addAttribute("tableName", tableName);
        // 取得表结构信息
        return "ibusiness/component/table/conf-table-column-input.jsp";
    }
    /**
     * 新建一张业务表页面跳转
     * 
     * @return
     */
    @RequestMapping("conf-table-insert")
    public String confTableInsert(@RequestParam("packageName") String packageName, @RequestParam("tableName") String tableName, @RequestParam("isBpmTable") String isBpmTable, Model model) {
        ConfTable confTable = null;
        if (CommonUtils.isNull(tableName)) {
            
        } else {
            List<ConfTable> list = tableService.queryConfTableByTableName(tableName);
            if (null != list && list.size() > 0) {
                confTable = list.get(0);
            }
        }
        model.addAttribute("model", confTable);
        model.addAttribute("packageName", packageName);
        model.addAttribute("isBpmTable", isBpmTable);
        
        return "ibusiness/component/table/conf-table-insert.jsp"; 
    }
    
    /**
     * 保存业务表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-save")
    public String confTableSave(@ModelAttribute ConfTable confTable, RedirectAttributes redirectAttributes) {
        // 新建 ==============================
        if (CommonUtils.isNull(confTable.getId())) {
            confTable.setId(UUID.randomUUID().toString());
            confTable.setTableName("IB_" + confTable.getTableName().toUpperCase());//转成大写
            tableService.getDao().saveInsert(confTable);
            
            // 先删后建
            dropTable("DROP TABLE IF EXISTS " + confTable.getTableName() + "");
            // 判断是流程表还是非流程表
            if (1 == confTable.getIsBpmTable()) {
                // 主表
                if ("1".equals(confTable.getTableType())) {
                    // 在数据库中创建一张业务表
                    createTableByReservedColumns(confTable.getTableName(), TableCommonUtil.getBpmMColumnsMap());
                } else {
                    // 子表
                    // 在数据库中创建一张业务表
                    createTableByReservedColumns(confTable.getTableName(), TableCommonUtil.getBpmSColumnsMap());
                }
                messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
                return "redirect:/table/conf-bpmTable-list.do?packageName="+confTable.getPackageName();
            } else {
                // 主表
                if ("1".equals(confTable.getTableType())) {
                    // 在数据库中创建一张业务表
                    createTableByReservedColumns(confTable.getTableName(), TableCommonUtil.getmColumnsMap());
                } else {
                    // 子表
                    // 在数据库中创建一张业务表
                    createTableByReservedColumns(confTable.getTableName(), TableCommonUtil.getsColumnsMap());
                }
            }
        } else {
            // 更新  ==============================
            ConfTable updateBean = tableService.getDao().get(confTable.getId());
            updateBean.setTableNameComment(confTable.getTableNameComment());
            updateBean.setTableType(confTable.getTableType());
            updateBean.setParentTableId(confTable.getParentTableId());
            tableService.getDao().update(updateBean);
        }
        // 
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/table/conf-table-list.do?packageName="+confTable.getPackageName();
    }
    /**
     * 在数据库中创建一张业务表
     * 
     * @param tableName
     */
    private void createTableByReservedColumns(String tableName, Map<String, ConfTableColumns> columnsMap) {
        // 在数据库中创建一张业务表
        String createSql ="CREATE TABLE " + tableName + " (";
        for (Map.Entry<String, ConfTableColumns> entry : columnsMap.entrySet()) {
            createSql = createSql + entry.getKey() + " " + TableCommonUtil.getReservedColumnsMap().get(entry.getKey()) + ", ";
        };
        createSql = createSql +  " PRIMARY KEY (ID))";
        createTable(createSql);
        // 删除字段
        String tcHql = "from ConfTableColumns where tableName=?";
        tableColumnsDao.removeAll(tableColumnsDao.find(tcHql, tableName));
        // 流程用主表--列字段 -- 流程表埋多个字段
        for (Map.Entry<String, ConfTableColumns> entry : columnsMap.entrySet()) {
            ConfTableColumns confTableColumns = entry.getValue();
            confTableColumns.setTableName(tableName);
            tableColumnsDao.saveInsert(confTableColumns);
        }
    }
    
    /**
     * 插入/变更/修改表列字段管理表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-columns-update")
    public String confTableColumnsUpdate(@ModelAttribute ConfTableColumns confTableColumns, @RequestParam("tableName") String tableName, RedirectAttributes redirectAttributes) {
        // 取得表结构信息
        ConfTableColumns bean = tableService.queryConfTableColumn(tableName, confTableColumns.getColumnValue());
        if (null == bean) {
            List<ConfTableColumns> list = new ArrayList<ConfTableColumns>();
            confTableColumns.setTableName(tableName.toUpperCase());//转成大写
            confTableColumns.setColumnValue(confTableColumns.getColumnValue().toUpperCase());//转成大写
            confTableColumns.setColumnSize(confTableColumns.getColumnSize().replace("，", ",")); // 将全角逗号替换成半角
            list.add(confTableColumns);
            // 插入
            tableService.insertConfTableColumns(list);
            // 在数据库中修改(更新/追加)指定的业务表的数据列
            alterTableColumn(confTableColumns);
        } else {
            confTableColumns.setColumnValue(confTableColumns.getColumnValue().toUpperCase());
            tableService.updateConfTableColumns(tableName, confTableColumns);
            messageHelper.addFlashMessage(redirectAttributes, "core.input.save", "更新成功");
            // 更改表结构
            alterTableColumn(confTableColumns);
        }
        return "redirect:/table/conf-table-column-list.do?tableName=" + tableName + "&isBpmTable=1";
    }
    /**
     * 删除业务表管理表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-remove")
    public String confTableRemove(@RequestParam("selectedItem") List<String> selectedItem, @RequestParam("packageName") String packageName, RedirectAttributes redirectAttributes) {
        ConfTable confTable = tableService.getDao().get(selectedItem.get(0));
        Integer isBpmTable = confTable.getIsBpmTable();
        // 去数据库中drop掉表
        dropTable("DROP TABLE IF EXISTS " + confTable.getTableName() + "");
        // 删除
        tableService.deleteConfTable(selectedItem);
    	messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
    	// 判断跳转
    	if (null != isBpmTable && isBpmTable == 1) {
    	    return "redirect:/table/conf-bpmTable-list.do?packageName="+packageName;
    	} else {
    	    return "redirect:/table/conf-table-list.do?packageName="+packageName;
    	}
        
    }
    /**
     * 删除表列字段管理表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-columns-remove")
    public String confTableColumnsRemove(@RequestParam("selectedItem") List<String> selectedItem, @RequestParam("tableName") String tableName, RedirectAttributes redirectAttributes) {
    	tableService.deleteConfColumnsTable(selectedItem, tableName);
    	messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
    	// 在数据库中(删除)指定的业务表的数据列
    	for (String columnValue : selectedItem) {
    		alterDropTableColumns(tableName, columnValue);
    	}
        return "redirect:/table/conf-table-column-list.do?tableName=" + tableName;
    }
    /**
     * 数据同步
     * 
     * @param packageName
     * @param tableName
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-table-data-synchronization")
    public String confTableSynchronization(@RequestParam("packageName") String packageName, @RequestParam("tableName") String tableName, @RequestParam("isBpmTable") String isBpmTable) {
        // 在数据库中创建一张业务表
        String tableColumnsSql = "from ConfTableColumns where tableName=?";
        List<ConfTableColumns> tcList = confTableColumnsService.find(tableColumnsSql, tableName);
        if (null != tcList && tcList.size() > 0) {
            // 1.去数据库中drop掉表
            dropTable("DROP TABLE IF EXISTS " + tableName + "");
            // 2.创建表
            String createSql ="CREATE TABLE " + tableName + " (";
            for (ConfTableColumns bean : tcList) {
                // 
                String columnType = TableCommonUtil.getInstance().getColumnTypeByDataType(bean.getColumnType(), bean.getColumnSize());
                createSql = createSql + bean.getColumnValue() + " " + columnType;
                createSql = createSql + ", ";
            };
            createSql = createSql +  " PRIMARY KEY (ID))";
            createTable(createSql);
        }
        if ("1".equals(isBpmTable)) {
            return "redirect:/table/conf-bpmTable-list.do?packageName="+packageName;
        } else {
            return "redirect:/table/conf-table-list.do?packageName="+packageName;
        }
    }
    /**
     * 在数据库中创建一张业务表
     * @param list
     */
    private void createTable(String sql) {
        if (0 == tableService.execute(sql)) {
            return;
        }
    }
    /**
     * 在数据库中删除一张业务表
     * @param list
     */
    private void dropTable(String sql) {
        if (0 == tableService.execute(sql)) {
            return;
        }
    }
    /**
     * 在数据库中修改(更新/追加)指定的业务表的数据列
     * @param list
     */
    @SuppressWarnings("unchecked")
    private void alterTableColumn(ConfTableColumns bean) {
        // 根据不同数据库生成不同 字段类型 TODO
        String columnType = TableCommonUtil.getInstance().getColumnTypeByDataType(bean.getColumnType(), bean.getColumnSize());
        
    	String defaultStr = "";
    	if (bean.getColumnType().indexOf("number") >= 0) {
		    defaultStr = " default 0";
	    }
    	// 插入数据列
		if (CommonUtils.isNull(bean.getColumnValueOld())) {
			String sql = "ALTER TABLE " + bean.getTableName() + " ADD " + bean.getColumnValue() + " " + columnType;
			sql = sql + defaultStr;
			if (0 == tableService.execute(sql)) {
				return;
			}
			// 取得表对应表单
			String formTableHql = "from ConfFormTable where tableName=? ";
			List<ConfFormTable> formTableList = confFormTableDao.find(formTableHql, bean.getTableName());
			if (null != formTableList && formTableList.size() > 0) {
			    for (ConfFormTable confFormTable : formTableList) {
			        // 实例化一个 表单对应字段管理表 对象
			        ConfFormTableColumn confFormTableColumn = new ConfFormTableColumn();
			        confFormTableColumn.setPackageName(confFormTable.getPackageName());
			        confFormTableColumn.setFormName(confFormTable.getFormName());
			        confFormTableColumn.setFormColumn(CommonUtils.toUpperCase(bean.getTableName())+"."+CommonUtils.toUpperCase(bean.getColumnValue()));
			        confFormTableColumn.setFormColumnTitle(bean.getColumnName());
			        confFormTableColumn.setTableColumn(bean.getColumnValue());
			        confFormTableColumn.setTableName(bean.getTableName());
			        confFormTableColumn.setColumnNo(2);
			        confFormTableColumn.setFcType("1");
			        confFormTableColumn.setFcDisplay("1");
			        confFormTableColumn.setFcEdit("1");
			        confFormTableColumn.setFcMust("1");
			        confFormTableColumn.setFcQuery("2");
			        // 插入表单中对应的表字段信息
		            confFormTableColumnDao.saveInsert(confFormTableColumn);
			    }
			}
			
		} else {
			// 修改数据列名
		    if (!bean.getColumnValue().equals(bean.getColumnValueOld())) {
		    	String sql = "ALTER TABLE " + bean.getTableName() + " RENAME COLUMN " + bean.getColumnValueOld() + " TO " + bean.getColumnValue();
		        if (0 == tableService.execute(sql)) {
		            return;
		        }
		    }
	    	
		    // 修改数据列类型
			if (!bean.getColumnType().equals(bean.getColumnTypeOld())
					|| (CommonUtils.isNull(bean.getColumnSize()) && !bean.getColumnSize().equals(bean.getColumnSizeOld()))) {
				String sql = "ALTER TABLE " + bean.getTableName() + " MODIFY " + bean.getColumnValue() + " " + columnType;
				if (0 == tableService.execute(sql)) {
		            return;
		        }
			}
		}
    }
    /**
     * 删除指定表中的指定列字段-在指定表的表结构中删除列
     * @param tableName
     * @param columnValue
     */
    @SuppressWarnings("unchecked")
    private void alterDropTableColumns(String tableName, String columnValue) {
    	String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + columnValue;
        if (0 == tableService.execute(sql)) {
            return;
        }
        // 删除表单中对应的表字段信息
        String hql = "from ConfFormTableColumn where tableName=? AND tableColumn=?";
        List<ConfFormTableColumn> list = confFormTableColumnDao.find(hql, CommonUtils.toUpperCase(tableName), CommonUtils.toUpperCase(columnValue));
        confFormTableColumnDao.removeAll(list);
        // 删除表单字段 
	}
    // ============================================================================================
    /**
     * 注入 table service
     */
    @Resource
	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}
    @Resource
    public void setTableColumnsDao(TableColumnsDao tableColumnsDao) {
        this.tableColumnsDao = tableColumnsDao;
    }
    @Resource
    public void setConfFormTableDao(ConfFormTableDao confFormTableDao) {
        this.confFormTableDao = confFormTableDao;
    }
    @Resource
    public void setConfFormTableColumnDao(ConfFormTableColumnDao confFormTableColumnDao) {
        this.confFormTableColumnDao = confFormTableColumnDao;
    }
    @Resource
    public void setConfTableColumnsService(ConfTableColumnsService confTableColumnsService) {
        this.confTableColumnsService = confTableColumnsService;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
