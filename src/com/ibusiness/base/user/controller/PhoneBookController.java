package com.ibusiness.base.user.controller;

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

import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;

/**   
 * 电话本
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("phonebook")
public class PhoneBookController {

    private MessageHelper messageHelper;
    private UserBaseDao userBaseDao;
   /**
     * 列表
     */
    @RequestMapping("phonebook-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 编辑查询参数--关于公司ID
        propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = userBaseDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "common/user/phonebook-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("phonebook-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        UserBase entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = userBaseDao.get(id);
        } else {
            entity = new UserBase();
        }
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        return "common/user/phonebook-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("phonebook-save")
    public String save(@ModelAttribute UserBase entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        if (CommonUtils.isNull(entity.getId())) {
            entity.setId(UUID.randomUUID().toString());
            userBaseDao.saveInsert(entity);
        } else {
            UserBase userBase = userBaseDao.get(entity.getId());
            userBase.setMobile(entity.getMobile());
            userBaseDao.update(userBase);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/phonebook/phonebook-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("phonebook-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<UserBase> entitys = userBaseDao.findByIds(selectedItem);
        for (UserBase entity : entitys) {
            userBaseDao.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/phonebook/phonebook-list.do";
    }
    
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }
    
}
