package com.ibusiness.doc.controller;

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
import com.ibusiness.doc.entity.DoctypeEntity;
import com.ibusiness.doc.service.DoctypeService;

/**
 * 文件类型页面
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("doctype")
public class DoctypeController {

    private MessageHelper messageHelper;
    private DoctypeService doctypeService;

    /**
     * 列表
     */
    @RequestMapping("doctype-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
        propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = doctypeService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "ibusiness/doc/doctype-list.jsp";
    }

    /**
     * 插入
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("doctype-input")
    public String input(@RequestParam(value = "id", required = false)
    String id, Model model) {
        DoctypeEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = doctypeService.get(id);
        } else {
            entity = new DoctypeEntity();
        }

        // 默认值公式
        entity = (DoctypeEntity) new FormulaCommon().defaultValue(entity, "IB_DOCTYPE");

        model.addAttribute("model", entity);

        // 在controller中设置页面控件用的数据
        return "ibusiness/doc/doctype-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("doctype-save")
    public String save(@ModelAttribute
    DoctypeEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            doctypeService.insert(entity);
        } else {
            doctypeService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/doctype/doctype-list.do";
    }

    /**
     * 删除
     * 
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("doctype-remove")
    public String remove(@RequestParam("selectedItem")
    List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<DoctypeEntity> entitys = doctypeService.findByIds(selectedItem);
        for (DoctypeEntity entity : entitys) {
            doctypeService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/doctype/doctype-list.do";
    }

    /**
     * 控件添加的方法 ========
     */

    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("doctype-export")
    public void excelExport(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
        propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = doctypeService.pagedQuery(page, propertyFilters);
        List<DoctypeEntity> beans = (List<DoctypeEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("文件类型页面" + CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "typename", "orderno");
        tableModel.setTableName("IB_DOCTYPE");
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
    @RequestMapping("doctype-importExcel")
    public String importExport(@RequestParam("attachment")
    MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls");
            attachment.transferTo(file);
            //
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "typename", "orderno");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.test.entity.DoctypeEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/doctype/doctype-list.do";
    }

    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setDoctypeService(DoctypeService doctypeService) {
        this.doctypeService = doctypeService;
    }
}
