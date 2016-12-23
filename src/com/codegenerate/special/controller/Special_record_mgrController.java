package com.codegenerate.special.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codegenerate.customermgr.entity.Customer_mgrEntity;
import com.codegenerate.customermgr.service.Customer_mgrService;
import com.codegenerate.special.entity.Special_record_mgrEntity;
import com.codegenerate.special.service.Special_record_mgrService;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;
import com.xg.src.com.tencent.xinge.XingeApp;

/**   
 * 专车司机记录管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("special_record_mgr")
public class Special_record_mgrController {

    private MessageHelper messageHelper;
    private Special_record_mgrService special_record_mgrService;
    private Customer_mgrService customer_mgrService;
   /**
     * 列表
     */
    @RequestMapping("special_record_mgr-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
    	page.setOrderBy("eventdate");
    	page.setOrder("DESC");
        page = special_record_mgrService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/special/special_record_mgr-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("special_record_mgr-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Special_record_mgrEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = special_record_mgrService.get(id);
        } else {
            entity = new Special_record_mgrEntity();
            entity.setEventdate(new Date());
        }
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sentpersonFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> sentpersonItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sentpersonFTCMap.get("SENTPERSON").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sentpersonItems", sentpersonItems);
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> infotypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> infotypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(infotypeFTCMap.get("INFOTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("infotypeItems", infotypeItems);
        return "codegenerate/special/special_record_mgr-input.jsp";
    }
    @RequestMapping("special_record_mgr-edit")
    public String specialrecordmgredit(@RequestParam(value = "id", required = false) String id, Model model) {
        Special_record_mgrEntity entity = null;
        entity = special_record_mgrService.get(id);
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sentpersonFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> sentpersonItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sentpersonFTCMap.get("SENTPERSON").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sentpersonItems", sentpersonItems);
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> infotypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> infotypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(infotypeFTCMap.get("INFOTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("infotypeItems", infotypeItems);
        return "codegenerate/special/special_record_mgr-edit.jsp";
    }
    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("special_record_mgr-save")
    public String save(@ModelAttribute Special_record_mgrEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            special_record_mgrService.insert(entity);
        } else {
            special_record_mgrService.update(entity);
        }
        // 
//        List<Customer_mgrEntity>  list = customer_mgrService.find("from Customer_mgrEntity where customercell=?", entity.getCellphone());
//        XingeApp.pushTokenAndroid(2100177474, "f78f4286e17cfe74215a9913bce06b2a", "标题坤坤test1", "pushTokenAndroid坤坤测试推送信息内容", "359786056940134");
        
        return "redirect:/special_record_mgr/special_record_mgr-list.do";
    }
    // 发送
    @SuppressWarnings("unchecked")
	@RequestMapping("special_record_mgr-send")
    public String send(@ModelAttribute Special_record_mgrEntity entity, RedirectAttributes redirectAttributes) throws Exception {
    	String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            special_record_mgrService.insert(entity);
        } else {
        	entity = special_record_mgrService.get(entity.getId());
        }
    	// 
        List<Customer_mgrEntity>  list = customer_mgrService.find("from Customer_mgrEntity where customercell=?", entity.getCellphone());
        if (null != list && list.size() > 0) {
        	Customer_mgrEntity bean = list.get(0);
        	// TODO
//        	if (!CommonUtils.isNull(bean.getImei())) {
	        	// 保存
	    		entity.setEventdate(new Date());
	    		entity.setSendflag("发送成功");
	    		// 未读
	    		entity.setIsReaded("no");
	    		special_record_mgrService.update(entity);
    		    //
        		JSONObject jsonObject = new JSONObject();
        		jsonObject.put("itemId", entity.getId());
        		jsonObject.put("type", entity.getInfotype());
        		jsonObject.put("detail", entity.getContent());
        		jsonObject.put("amount", entity.getAccount());
        		jsonObject.put("sendDate", CommonUtils.getInstance().getYmdhms().format(entity.getEventdate()));
        		jsonObject.put("amountMonth", entity.getMonthdate());
        		jsonObject.put("noticeId", entity.getId());
        		// 根据token调用
        		if (!CommonUtils.isNull(bean.getToken())) {
        			com.xg.outerlib.org.json.JSONObject xgmsg = XingeApp.pushTokenAndroid(2100177474, "f78f4286e17cfe74215a9913bce06b2a", entity.getInfotype(), jsonObject.toString(), bean.getToken());
        			System.out.println("===================xgmsg:"+xgmsg);
        		} else {
        			
        		}
//        	}
        }
        return "redirect:/special_record_mgr/special_record_mgr-list.do";
    }
    /**
     * 群发页面
     */
    @RequestMapping("special_record_mgr-mass-input")
    public String input_mass(@RequestParam(value = "id", required = false) String id, Model model) {
        Special_record_mgrEntity entity = new Special_record_mgrEntity();
        entity.setEventdate(new Date());
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sentpersonFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> sentpersonItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sentpersonFTCMap.get("SENTPERSON").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sentpersonItems", sentpersonItems);
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> infotypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> infotypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(infotypeFTCMap.get("INFOTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("infotypeItems", infotypeItems);
        return "codegenerate/special/special_record_mgr-mass-input.jsp";
    }
    /**
     * 群发
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("special_record_mgr-sendmass")
    public String send_mass(@ModelAttribute Special_record_mgrEntity entity, RedirectAttributes redirectAttributes) throws Exception {
    	String[] cellphones = entity.getCellphone().split(",");
    	for (String phoneno : cellphones) {
    		if (!CommonUtils.isNull(phoneno)) {
                // 发推送 
                List<Customer_mgrEntity>  list = customer_mgrService.find("from Customer_mgrEntity where customercell=?", phoneno);
                if (null != list && list.size() > 0) {
                	Customer_mgrEntity bean = list.get(0);
                    // 推送
                	// TODO
//                	if (!CommonUtils.isNull(bean.getImei())) {
            		    //
                		JSONObject jsonObject = new JSONObject();
                		jsonObject.put("itemId", entity.getId());
                		jsonObject.put("type", entity.getInfotype());
                		jsonObject.put("detail", entity.getContent());
                		jsonObject.put("amount", entity.getAccount());
                		jsonObject.put("sendDate", CommonUtils.getInstance().getYmdhms().format(entity.getEventdate()));
                		jsonObject.put("amountMonth", entity.getMonthdate());
                		jsonObject.put("noticeId", entity.getId());
                		// 根据token调用
                		if (!CommonUtils.isNull(bean.getToken())) {
                			com.xg.outerlib.org.json.JSONObject xgmsg = XingeApp.pushTokenAndroid(2100177474, "f78f4286e17cfe74215a9913bce06b2a", entity.getInfotype(), jsonObject.toString(), bean.getToken());
                			System.out.println("===================xgmsg:"+xgmsg);
                			
                			// 插入数据
                			Special_record_mgrEntity newentity= new Special_record_mgrEntity();
                			newentity.setId(UUID.randomUUID().toString());
                			/**司机电话*/
                			newentity.setCellphone(phoneno);
                			newentity.setEventdate(new Date());
                			/**发送标识*/
                			newentity.setSendflag("发送成功");
                			// 未读
            	    		entity.setIsReaded("no");
                			/**司机姓名*/
                			newentity.setDrivername(bean.getCustomername());
                			/**发送方*/
                			newentity.setSentperson(entity.getSentperson());
                			/**信息类别*/
                			newentity.setInfotype(entity.getInfotype());
                			/**内容*/
                			newentity.setContent(entity.getContent());
                			/**金额*/
                			newentity.setAccount(entity.getAccount());
                			/**处理人*/
                			newentity.setDealperson(entity.getDealperson());
                			/**处理内容*/
                			newentity.setDealcontent(entity.getDealcontent());
                			/**年月*/
                			newentity.setMonthdate(entity.getMonthdate());
                			
                            special_record_mgrService.insert(newentity);
                		}
//                	}
                }
    		}
    	}
        return "redirect:/special_record_mgr/special_record_mgr-list.do";
    }
    
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("special_record_mgr-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Special_record_mgrEntity> entitys = special_record_mgrService.findByIds(selectedItem);
        for (Special_record_mgrEntity entity : entitys) {
            special_record_mgrService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/special_record_mgr/special_record_mgr-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
    @ResponseBody @RequestMapping("name-list")
    public String ajaxroomlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {
    	    List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
    	    page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.customermgr.service.Customer_mgrService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();
    	    return CommonUtils.getJsonFromList(list, null).toString();
    }
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("special_record_mgr-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = special_record_mgrService.pagedQuery(page, propertyFilters);
        List<Special_record_mgrEntity> beans = (List<Special_record_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("专车司机记录管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "eventdate", "sentperson", "infotype", "drivername", "content", "account", "dealperson", "dealcontent", "cellphone", "monthdate");
        tableModel.setTableName("IB_SPECIAL_RECORD_MGR");
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
    @RequestMapping("special_record_mgr-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "eventdate", "sentperson", "infotype", "drivername", "content", "account", "dealperson", "dealcontent", "cellphone", "monthdate");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.special.entity.Special_record_mgrEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/special_record_mgr/special_record_mgr-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setSpecial_record_mgrService(Special_record_mgrService special_record_mgrService) {
        this.special_record_mgrService = special_record_mgrService;
    }
    @Resource
    public void setCustomer_mgrService(Customer_mgrService customer_mgrService) {
        this.customer_mgrService = customer_mgrService;
    }
}
