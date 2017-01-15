package com.codegenerate.carmgr.controller;

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

import com.codegenerate.carmgr.entity.CarMaintainFactoryEntity;
import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.codegenerate.carmgr.service.CarMaintainFactoryService;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.common.model.SumItem;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;
import com.report.utils.DateUtil;
import com.report.utils.SqlFormat;

/**   
 * @Title: Controller
 * @Description: 维修厂管理
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("carMaintainManage")
public class CarMaintainManageController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private CarMaintainFactoryService carMaintainFactoryService;
   /**
     * 列表
     */
    @RequestMapping("list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = carMaintainFactoryService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 入库类型
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> intypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");
        List<com.ibusiness.common.model.ConfSelectItem> intypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(intypeFTCMap.get("INTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);
        model.addAttribute("intypeItems", intypeItems);
        // 型号名称
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> typenameFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");JSONObject typenameJsonObj = JSONObject.fromObject(typenameFTCMap.get("TYPENAME").getConfSelectInfo());String typenameSql = typenameJsonObj.getString("sql");List<Map<String,Object>> typenameList = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(typenameSql);List<ConfSelectItem> typenameItems = new java.util.ArrayList<ConfSelectItem>();for (Map<String,Object> mapBean : typenameList) {    ConfSelectItem confSelectItem = new ConfSelectItem();    confSelectItem.setKey(mapBean.get("vKey").toString());    confSelectItem.setValue(mapBean.get("vValue").toString());    typenameItems.add(confSelectItem);}model.addAttribute("typenameItems", typenameItems);
        // 存放仓库
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> savefactoryFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");
        JSONObject savefactoryJsonObj = JSONObject.fromObject(savefactoryFTCMap.get("SAVEFACTORY").getConfSelectInfo());
        String savefactorySql = savefactoryJsonObj.getString("sql");
        List<Map<String,Object>> savefactoryList = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(savefactorySql);
        List<ConfSelectItem> savefactoryItems = new java.util.ArrayList<ConfSelectItem>();
        for (Map<String,Object> mapBean : savefactoryList) {
        	ConfSelectItem confSelectItem = new ConfSelectItem();
        	confSelectItem.setKey(mapBean.get("vKey").toString());
        	confSelectItem.setValue(mapBean.get("vValue").toString());
        	savefactoryItems.add(confSelectItem);
        }
        model.addAttribute("savefactoryItems", savefactoryItems);
//        String date = DateUtil.toString(new Date());
//        // 违章周统计
//        SumItem sumItem = SqlFormat.getBySql6("2016-07-06");
//        SqlFormat.getBySql1("2016-08-07");
//        System.out.println(JSONObject.fromObject(sumItem).toString());
        // 返回JSP
        return "codegenerate/carMaintainManage/list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        CarMaintainFactoryEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = carMaintainFactoryService.get(id);
        } else {
            entity = new CarMaintainFactoryEntity();
            //entity.setCarstatus("在库");
        }
        
        // 默认值公式
        entity = (CarMaintainFactoryEntity) new FormulaCommon().defaultValue(entity, "IB_CAR_MAINTAIN_FACTORY");
//        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> intypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");List<com.ibusiness.common.model.ConfSelectItem> intypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(intypeFTCMap.get("INTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("intypeItems", intypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> typenameFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");JSONObject typenameJsonObj = JSONObject.fromObject(typenameFTCMap.get("TYPENAME").getConfSelectInfo());String typenameSql = typenameJsonObj.getString("sql");List<Map<String,Object>> typenameList = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(typenameSql);List<ConfSelectItem> typenameItems = new java.util.ArrayList<ConfSelectItem>();for (Map<String,Object> mapBean : typenameList) {    ConfSelectItem confSelectItem = new ConfSelectItem();    confSelectItem.setKey(mapBean.get("vKey").toString());    confSelectItem.setValue(mapBean.get("vValue").toString());    typenameItems.add(confSelectItem);}model.addAttribute("typenameItems", typenameItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> savefactoryFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");JSONObject savefactoryJsonObj = JSONObject.fromObject(savefactoryFTCMap.get("SAVEFACTORY").getConfSelectInfo());String savefactorySql = savefactoryJsonObj.getString("sql");List<Map<String,Object>> savefactoryList = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(savefactorySql);List<ConfSelectItem> savefactoryItems = new java.util.ArrayList<ConfSelectItem>();for (Map<String,Object> mapBean : savefactoryList) {    ConfSelectItem confSelectItem = new ConfSelectItem();    confSelectItem.setKey(mapBean.get("vKey").toString());    confSelectItem.setValue(mapBean.get("vValue").toString());    savefactoryItems.add(confSelectItem);}model.addAttribute("savefactoryItems", savefactoryItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> cartypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");List<com.ibusiness.common.model.ConfSelectItem> cartypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(cartypeFTCMap.get("CARTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("cartypeItems", cartypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> targetcompanyFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");JSONObject targetcompanyJsonObj = JSONObject.fromObject(targetcompanyFTCMap.get("TARGETCOMPANY").getConfSelectInfo());String targetcompanySql = targetcompanyJsonObj.getString("sql");List<Map<String,Object>> targetcompanyList = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(targetcompanySql);List<ConfSelectItem> targetcompanyItems = new java.util.ArrayList<ConfSelectItem>();for (Map<String,Object> mapBean : targetcompanyList) {    ConfSelectItem confSelectItem = new ConfSelectItem();    confSelectItem.setKey(mapBean.get("vKey").toString());    confSelectItem.setValue(mapBean.get("vValue").toString());    targetcompanyItems.add(confSelectItem);}model.addAttribute("targetcompanyItems", targetcompanyItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> carcolorFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_MGR", "carmanage");List<com.ibusiness.common.model.ConfSelectItem> carcolorItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(carcolorFTCMap.get("CARCOLOR").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("carcolorItems", carcolorItems);
        return "codegenerate/carMaintainManage/input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    public String save(@ModelAttribute CarMaintainFactoryEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
//            entity.setCarstatus("在库");
//            entity.setInfactorydate(new Date());
            carMaintainFactoryService.insert(entity);
        } else {
        	carMaintainFactoryService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/carMaintainManage/list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<CarMaintainFactoryEntity> entitys = carMaintainFactoryService.findByIds(selectedItem);
        for (CarMaintainFactoryEntity entity : entitys) {
        	carMaintainFactoryService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/carMaintainManage/list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                 @ResponseBody @RequestMapping("uploadprcture-upload")  public String uploadprctureUpload(@org.springframework.beans.factory.annotation.Qualifier("attachment") MultipartFile attachment, HttpServletResponse response) {   com.ibusiness.doc.store.StoreDTO storeDTO = null;    if (null != attachment && attachment.getSize() > 0) {       try {           storeDTO = storeConnector.save("ibimg", attachment.getInputStream(), attachment.getOriginalFilename());       } catch (Exception e) {e.printStackTrace();}}    return null == storeDTO ? "" : storeDTO.getKey();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = carMaintainFactoryService.pagedQuery(page, propertyFilters);
        List<Car_mgrEntity> beans = (List<Car_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("维修厂管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("id", "maintainname", "type", "address", "phone", "linkman", "linkmanphone", "maintainproject", "remark");
        tableModel.setTableName("IB_CAR_MAINTAIN_FACTORY");
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
    @RequestMapping("importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "intype", "typename", "carnum", "carframenum", "caroutfaydate", "signdate", "savefactory", "cartype", "targetcompany", "carcolor", "buyfex", "signnumfee", "firstsecurefee", "firstsecuredate", "registereid", "yearcheckdate", "infactorydate", "carstatus", "remark", "uploadprcture");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.carmgr.entity.Car_mgrEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/carMaintainManage/list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    
    @Resource
	public void setCarMaintainFactoryService(
			CarMaintainFactoryService carMaintainFactoryService) {
		this.carMaintainFactoryService = carMaintainFactoryService;
	}

    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
