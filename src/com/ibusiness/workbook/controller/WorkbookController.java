package com.ibusiness.workbook.controller;

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

import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.workbook.entity.WorkbookEntity;
import com.ibusiness.workbook.service.WorkbookService;

/**   
 * 工作日志controller
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("workbook")
public class WorkbookController {

    private MessageHelper messageHelper;
    private WorkbookService workbookService;
   /**
     * 列表
     */
    @RequestMapping("workbook-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据条件查询数据
        page = workbookService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/test/workbook-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("workbook-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        WorkbookEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = workbookService.get(id);
        } else {
            entity = new WorkbookEntity();
        }
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> formTableColumnMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_WORKBOOK", "workbook");List<com.ibusiness.common.model.ConfSelectItem> worktypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(formTableColumnMap.get("WORKTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("worktypeItems", worktypeItems);
        return "codegenerate/test/workbook-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("workbook-save")
    public String save(@ModelAttribute WorkbookEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            workbookService.saveInsert(entity);
        } else {
            workbookService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/workbook/workbook-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("workbook-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<WorkbookEntity> entitys = workbookService.findByIds(selectedItem);
        for (WorkbookEntity entity : entitys) {
            workbookService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/workbook/workbook-list.do";
    }
    
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setWorkbookService(WorkbookService workbookService) {
        this.workbookService = workbookService;
    }
    
}
