package com.ibusiness.plccontrol.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.plccontrol.entity.PlcDrawConfEntity;
import com.ibusiness.plccontrol.service.PlcDrawConfService;

/**   
 * PLC绘图页面 设备对象配置管理controller
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("drawConf")
public class PlcDrawConfController {

    private MessageHelper messageHelper;
    private PlcDrawConfService plcDrawConfService;
    /**
     * 列表
     */
    @RequestMapping("drawConf-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据条件查询数据
        page = plcDrawConfService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "ibusiness/plc-control/draw-conf-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("drawConf-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        PlcDrawConfEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = plcDrawConfService.get(id);
        } else {
            entity = new PlcDrawConfEntity();
        }
        
        // 默认值公式
        entity = (PlcDrawConfEntity) new FormulaCommon().defaultValue(entity, "IB_CONF_PLCDRAW");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        return "ibusiness/plc-control/draw-conf-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("drawConf-save")
    public String save(@ModelAttribute PlcDrawConfEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            plcDrawConfService.insert(entity);
        } else {
            plcDrawConfService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/drawConf/drawConf-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("drawConf-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<PlcDrawConfEntity> entitys = plcDrawConfService.findByIds(selectedItem);
        for (PlcDrawConfEntity entity : entitys) {
            plcDrawConfService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/drawConf/drawConf-list.do";
    }
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("drawConf-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        page = plcDrawConfService.pagedQuery(page, propertyFilters);
        List<PlcDrawConfEntity> beans = (List<PlcDrawConfEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("PLC绘图页面设备对象配置页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("id", "remark", "itemid", "itemname", "itemtype", "itemtitle", "imghurl", "imgvurl");
        tableModel.setTableName("IB_CONF_PLCDRAW");
        tableModel.setData(beans);
        try {
            new ExcelCommon().exportExcel(response, tableModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * excel导入
     */
    @RequestMapping("drawConf-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("id", "remark", "itemid", "itemname", "itemtype", "itemtitle", "imghurl", "imgvurl");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.ibusiness.plccontrol.entity.PlcDrawConfEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/drawConf/drawConf-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setPlcDrawConfService(PlcDrawConfService plcDrawConfService) {
        this.plcDrawConfService = plcDrawConfService;
    }
    
}
