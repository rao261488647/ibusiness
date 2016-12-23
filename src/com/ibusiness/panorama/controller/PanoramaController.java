package com.ibusiness.panorama.controller;

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
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.panorama.entity.PanoramaEntity;
import com.ibusiness.panorama.service.PanoramaService;

/**
 * 全景图
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("panorama")
public class PanoramaController {

    /**
     * 流程图信息初始化
     * 
     * @param flowId
     * @return
     */
    @RequestMapping("panorama-init")
    public String initFlowChart(Model model) {
    	String urlStr = "/ibusiness/panorama/output/index.html";
    	model.addAttribute("urlStr", urlStr);
        return "ibusiness/panorama/panorama-show.jsp";
    }
    private MessageHelper messageHelper;
    private PanoramaService panoramaService;
    /**
     * 列表
     */
    @RequestMapping("panorama-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = panoramaService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "ibusiness/panorama/panorama-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("panorama-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        PanoramaEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = panoramaService.get(id);
        } else {
            entity = new PanoramaEntity();
        }
        
        // 默认值公式
        entity = (PanoramaEntity) new FormulaCommon().defaultValue(entity, "IB_PANORAMA");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        return "ibusiness/panorama/panorama-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("panorama-save")
    public String save(@ModelAttribute PanoramaEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            panoramaService.insert(entity);
        } else {
            panoramaService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/panorama/panorama-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("panorama-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<PanoramaEntity> entitys = panoramaService.findByIds(selectedItem);
        for (PanoramaEntity entity : entitys) {
            panoramaService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/panorama/panorama-list.do";
    }
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("panorama-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        page = panoramaService.pagedQuery(page, propertyFilters);
        List<PanoramaEntity> beans = (List<PanoramaEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("全景图管理表页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "name", "panoramaaddress", "panoramaurl");
        tableModel.setTableName("IB_PANORAMA");
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
    @RequestMapping("panorama-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "name", "panoramaaddress", "panoramaurl");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.test.entity.PanoramaEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/panorama/panorama-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setPanoramaService(PanoramaService panoramaService) {
        this.panoramaService = panoramaService;
    }
}
