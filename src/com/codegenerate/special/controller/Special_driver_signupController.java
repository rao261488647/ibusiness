package com.codegenerate.special.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.security.util.SpringSecurityUtils;
import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.common.service.FormulaCommon;

import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.util.CommonUtils;

import com.codegenerate.special.entity.Special_driver_signupEntity;
import com.codegenerate.special.service.Special_driver_signupService;

/**   
 * @Title: Controller
 * @Description: 专车司机报名页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("special_driver_signup")
public class Special_driver_signupController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Special_driver_signupService special_driver_signupService;
   /**
     * 列表
     */
    @RequestMapping("special_driver_signup-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
    	page.setOrderBy("submitdate");
    	page.setOrder("DESC");
        page = special_driver_signupService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/special/special_driver_signup-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("special_driver_signup-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Special_driver_signupEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = special_driver_signupService.get(id);
        } else {
            entity = new Special_driver_signupEntity();
        }
        
        // 默认值公式
        entity = (Special_driver_signupEntity) new FormulaCommon().defaultValue(entity, "IB_SPECIAL_DRIVER_SIGNUP");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> ordertypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_SIGNUP", "specialdriversignup");List<com.ibusiness.common.model.ConfSelectItem> ordertypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(ordertypeFTCMap.get("ORDERTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("ordertypeItems", ordertypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> cartypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_SIGNUP", "specialdriversignup");JSONObject cartypeJsonObj = JSONObject.fromObject(cartypeFTCMap.get("CARTYPE").getConfSelectInfo());String cartypeSql = cartypeJsonObj.getString("sql");List<Map<String,Object>> cartypeList = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(cartypeSql);List<ConfSelectItem> cartypeItems = new java.util.ArrayList<ConfSelectItem>();for (Map<String,Object> mapBean : cartypeList) {    ConfSelectItem confSelectItem = new ConfSelectItem();    confSelectItem.setKey(mapBean.get("vKey").toString());    confSelectItem.setValue(mapBean.get("vValue").toString());    cartypeItems.add(confSelectItem);}model.addAttribute("cartypeItems", cartypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> preregistertimeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_SIGNUP", "specialdriversignup");List<com.ibusiness.common.model.ConfSelectItem> preregistertimeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(preregistertimeFTCMap.get("PREREGISTERTIME").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("preregistertimeItems", preregistertimeItems);
        return "codegenerate/special/special_driver_signup-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("special_driver_signup-save")
    public String save(@ModelAttribute Special_driver_signupEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            entity.setInputtype("PC端");
            special_driver_signupService.insert(entity);
        } else {
            special_driver_signupService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/special_driver_signup/special_driver_signup-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("special_driver_signup-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Special_driver_signupEntity> entitys = special_driver_signupService.findByIds(selectedItem);
        for (Special_driver_signupEntity entity : entitys) {
            special_driver_signupService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/special_driver_signup/special_driver_signup-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
	@ResponseBody
	@RequestMapping("carphote-upload")
	public String carphoteUpload(
			@org.springframework.beans.factory.annotation.Qualifier("attachment") MultipartFile attachment,
			HttpServletResponse response) {
		com.ibusiness.doc.store.StoreDTO storeDTO = null;
		if (null != attachment && attachment.getSize() > 0) {
			try {
				storeDTO = storeConnector.save("ibimg",
						attachment.getInputStream(),
						attachment.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null == storeDTO ? "" : storeDTO.getKey();
	}

    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("special_driver_signup-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = special_driver_signupService.pagedQuery(page, propertyFilters);
        List<Special_driver_signupEntity> beans = (List<Special_driver_signupEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("专车司机报名页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "name", "cell", "submitdate", "ordertype", "cartype", "selfcaruseyear", "carphote", "platformto", "precontractdate", "preregisterdate", "preregistertime", "dealperson", "dealtime", "dealresult");
        tableModel.setTableName("IB_SPECIAL_DRIVER_SIGNUP");
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
    @RequestMapping("special_driver_signup-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "name", "cell", "submitdate", "ordertype", "cartype", "selfcaruseyear", "carphote", "platformto", "precontractdate", "preregisterdate", "preregistertime", "dealperson", "dealtime", "dealresult");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.special.entity.Special_driver_signupEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/special_driver_signup/special_driver_signup-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setSpecial_driver_signupService(Special_driver_signupService special_driver_signupService) {
        this.special_driver_signupService = special_driver_signupService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
