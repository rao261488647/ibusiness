package com.codegenerate.special.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.codegenerate.carmgr.service.Car_mgrService;
import com.codegenerate.customermgr.entity.Customer_mgrEntity;
import com.codegenerate.customermgr.service.Customer_mgrService;
import com.codegenerate.special.entity.Special_driver_infoEntity;
import com.codegenerate.special.entity.Special_record_mgrEntity;
import com.codegenerate.special.service.Special_driver_infoService;
import com.codegenerate.special.service.Special_record_mgrService;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;
import com.xg.src.com.tencent.xinge.XingeApp;

/**   
 * @Title: Controller
 * @Description: 专车司机信息页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("special_driver_info")
public class Special_driver_infoController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Special_driver_infoService special_driver_infoService;
    private Car_mgrService car_mgrService;
    private Customer_mgrService customer_mgrService;
    private Special_record_mgrService special_record_mgrService;
    
   /**
     * 列表
     */
    @RequestMapping("special_driver_info-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = special_driver_infoService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/special/special_driver_info-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("special_driver_info-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Special_driver_infoEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = special_driver_infoService.get(id);
        } else {
            entity = new Special_driver_infoEntity();
        }
        
        // 默认值公式
        entity = (Special_driver_infoEntity) new FormulaCommon().defaultValue(entity, "IB_SPECIAL_DRIVER_INFO");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sexFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> sexItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sexFTCMap.get("SEX").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sexItems", sexItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> precartypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> precartypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(precartypeFTCMap.get("PRECARTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("precartypeItems", precartypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> drivertypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> drivertypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(drivertypeFTCMap.get("DRIVERTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("drivertypeItems", drivertypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> statusFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> statusItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(statusFTCMap.get("STATUS").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("statusItems", statusItems);
        return "codegenerate/special/special_driver_info-input.jsp";
    }

    /**
     * 续约
     */
    @RequestMapping("special_driver_info-renew")
    public String renew(@RequestParam(value = "id", required = false) String id, Model model) {
        Special_driver_infoEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = special_driver_infoService.get(id);
        } else {
            entity = new Special_driver_infoEntity();
            return "codegenerate/special/special_driver_info-input.jsp";
        }
        
        // 默认值公式
        entity = (Special_driver_infoEntity) new FormulaCommon().defaultValue(entity, "IB_SPECIAL_DRIVER_INFO");
        // 设置ID为空,这样保存的时候属于 新建数据
        entity.setId("");
        entity.setStatus("正常");
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sexFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> sexItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sexFTCMap.get("SEX").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sexItems", sexItems);
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> precartypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> precartypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(precartypeFTCMap.get("PRECARTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("precartypeItems", precartypeItems);
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> drivertypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> drivertypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(drivertypeFTCMap.get("DRIVERTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("drivertypeItems", drivertypeItems);
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> statusFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_DRIVER_INFO", "specialdriverinfo");List<com.ibusiness.common.model.ConfSelectItem> statusItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(statusFTCMap.get("STATUS").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("statusItems", statusItems);
        return "codegenerate/special/special_driver_info-input.jsp";
    }
    
    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("special_driver_info-save")
    public String save(@ModelAttribute Special_driver_infoEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            special_driver_infoService.insert(entity);
        } else {
        	Special_driver_infoEntity entity_old= special_driver_infoService.get(id);
        	// 修改车辆状态为【在库】
	          List<Car_mgrEntity> carmgrlist = car_mgrService.find("from Car_mgrEntity where carnum=?", entity_old.getCarnum());
	          if (carmgrlist != null && carmgrlist.size() > 0) {
	          	Car_mgrEntity carMgrEntity = carmgrlist.get(0);
	          	carMgrEntity.setCarstatus("在库");
	          	car_mgrService.update(carMgrEntity);
	          }
            entity_old.setAccount(entity.getAccount());
        	entity_old.setName(entity.getName());
        	entity_old.setCellphone(entity.getCellphone());
        	entity_old.setEid(entity.getEid());
        	entity_old.setSex(entity.getSex());
        	entity_old.setAddress(entity.getAddress());
        	entity_old.setPubliahgrd(entity.getPubliahgrd());
        	entity_old.setBirth(entity.getBirth());
        	entity_old.setNation(entity.getNation());
        	entity_old.setEidurl(entity.getEidurl());
        	entity_old.setDrivernum(entity.getDrivernum());
        	entity_old.setDriverfilenum(entity.getDriverfilenum());
        	entity_old.setPredriverdate(entity.getPredriverdate());
        	entity_old.setPrecartype(entity.getPrecartype());
        	entity_old.setValidstartdate(entity.getValidstartdate());
        	entity_old.setValidtrem(entity.getValidtrem());
        	entity_old.setDrivernumurl(entity.getDrivernumurl());
        	entity_old.setCarname(entity.getCarname());
        	entity_old.setCarnum(entity.getCarnum());
        	entity_old.setCarframenum(entity.getCarframenum());
        	entity_old.setCarcolor(entity.getCarcolor());
        	entity_old.setDrivertype(entity.getDrivertype());
        	entity_old.setContractid(entity.getContractid());
        	entity_old.setContractterm(entity.getContractterm());
        	entity_old.setContractsumdeposit(entity.getContractsumdeposit());
        	entity_old.setContractvalidday(entity.getContractvalidday());
        	entity_old.setContractoverdate(entity.getContractoverdate());
        	entity_old.setPermonthrent(entity.getPermonthrent());
        	entity_old.setPermonthday(entity.getPermonthday());
        	entity_old.setCardeposit(entity.getCardeposit());
        	entity_old.setContractcopyupload(entity.getContractcopyupload());
        	entity_old.setStatus(entity.getStatus());
        	entity_old.setScopeid(entity.getScopeid());
        	entity_old.setPlatformto(entity.getPlatformto());
        	entity_old.setLogintel(entity.getLogintel());
        	entity_old.setTosource(entity.getTosource());
        	entity_old.setRecommended(entity.getRecommended());
        	entity_old.setBusipeople(entity.getBusipeople());
        	entity_old.setSalesman(entity.getSalesman());
        	entity_old.setRemarks(entity.getRemarks());
        	// 
        	entity_old.setCompanybankact(entity.getCompanybankact());
        	entity_old.setCompanybank(entity.getCompanybank());
        	entity_old.setPayrollbankact(entity.getPayrollbankact());
        	entity_old.setPayrollbank(entity.getPayrollbank());
        	entity_old.setUdnumber(entity.getUdnumber());
        	entity_old.setUdusername(entity.getUdusername());
        	entity_old.setUbername(entity.getUbername());
        	entity_old.setUberpassword(entity.getUberpassword());
        	entity_old.setUberonline(entity.getUberonline());
        	entity_old.setDdonline(entity.getDdonline());
        	entity_old.setWageplan(entity.getWageplan());
        	entity_old.setTtcard(entity.getTtcard());
        	entity_old.setBydcard(entity.getBydcard());
        	entity_old.setPtdkcard(entity.getPtdkcard());
        	entity_old.setSbcard(entity.getSbcard());
            // 更新
            special_driver_infoService.update(entity_old);
        }
        // 
        // 修改车辆状态为【已出库】
        List<Car_mgrEntity> carmgrlist = car_mgrService.find("from Car_mgrEntity where carnum=?", entity.getCarnum());
        if (carmgrlist != null && carmgrlist.size() > 0) {
        	Car_mgrEntity carMgrEntity = carmgrlist.get(0);
        	if ("正常".equals(entity.getStatus())) {
        		carMgrEntity.setCarstatus("已出库");
        	} else if("已解约".equals(entity.getStatus())) {
        		carMgrEntity.setCarstatus("在库");
        	}
        	car_mgrService.update(carMgrEntity);
        }
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/special_driver_info/special_driver_info-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("special_driver_info-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Special_driver_infoEntity> entitys = special_driver_infoService.findByIds(selectedItem);
        for (Special_driver_infoEntity entity : entitys) {
        	// 修改车辆状态为【在库】
            List<Car_mgrEntity> carmgrlist = car_mgrService.find("from Car_mgrEntity where carnum=?", entity.getCarnum());
            if (carmgrlist != null && carmgrlist.size() > 0) {
            	Car_mgrEntity carMgrEntity = carmgrlist.get(0);
            	carMgrEntity.setCarstatus("在库");
            	car_mgrService.update(carMgrEntity);
            }
        	// 删除
            special_driver_infoService.remove(entity);
        }
        
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/special_driver_info/special_driver_info-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
	@ResponseBody
	@RequestMapping("customername-list")
	public String ajaxroomlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {
		List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
//		page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(
//				Customer_mgrService.class)
//				.pagedQuery(page, propertyFilters);
//		List list = (java.util.ArrayList) page.getResult();
//		return CommonUtils.getJsonFromList(list, null).toString();
		// 
		String matchValue = "";
		if (null!= propertyFilters && propertyFilters.size() > 0) {
			matchValue = String.valueOf(propertyFilters.get(0).getMatchValue());
		}
		// TODO 已经有的数据就不要再显示了
		List<Map<String, Object>> listmap = special_driver_infoService.getJdbcTemplate().queryForList("select icm.* from IB_CUSTOMER_MGR icm left join IB_SPECIAL_DRIVER_INFO isdi ON icm.customercell=isdi.cellphone where isdi.cellphone is null AND icm.customername like '%"+matchValue+"%' ");
		JSONArray jsonArray = new JSONArray();
		for (Map<String, Object> map: listmap) {
			JSONObject object = JSONObject.fromObject(map);
			jsonArray.add(object);
		}
		return CommonUtils.toLowerCase(jsonArray.toString());
	}
    @ResponseBody @RequestMapping("eidurl-upload")  public String eidurlUpload(@org.springframework.beans.factory.annotation.Qualifier("attachment") MultipartFile attachment, HttpServletResponse response) { 	com.ibusiness.doc.store.StoreDTO storeDTO = null; 	if (null != attachment && attachment.getSize() > 0) {    	try {			storeDTO = storeConnector.save("ibfile", attachment.getInputStream(), attachment.getOriginalFilename());		} catch (Exception e) {e.printStackTrace();}}    return null == storeDTO ? "" : storeDTO.getKey();} @RequestMapping("eidurl-download") public void eidurlDownload(@RequestParam("path") String path, @RequestParam("filename") String filename, HttpServletResponse response) throws Exception {    java.io.InputStream is = null;    try {        com.ibusiness.core.util.ServletUtils.setFileDownloadHeader(response, filename);        is = storeConnector.get("ibfile", path).getInputStream();        com.ibusiness.core.util.IoUtils.copyStream(is, response.getOutputStream());    } finally { if (is != null) {is.close();}} }
    @ResponseBody @RequestMapping("drivernumurl-upload")  public String drivernumurlUpload(@org.springframework.beans.factory.annotation.Qualifier("attachment") MultipartFile attachment, HttpServletResponse response) { 	com.ibusiness.doc.store.StoreDTO storeDTO = null; 	if (null != attachment && attachment.getSize() > 0) {    	try {			storeDTO = storeConnector.save("ibfile", attachment.getInputStream(), attachment.getOriginalFilename());		} catch (Exception e) {e.printStackTrace();}}    return null == storeDTO ? "" : storeDTO.getKey();} @RequestMapping("drivernumurl-download") public void drivernumurlDownload(@RequestParam("path") String path, @RequestParam("filename") String filename, HttpServletResponse response) throws Exception {    java.io.InputStream is = null;    try {        com.ibusiness.core.util.ServletUtils.setFileDownloadHeader(response, filename);        is = storeConnector.get("ibfile", path).getInputStream();        com.ibusiness.core.util.IoUtils.copyStream(is, response.getOutputStream());    } finally { if (is != null) {is.close();}} }

	@ResponseBody
	@RequestMapping("typename-list")
	public String ajaxtypenamelist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {
		parameterMap.put("filter_LIKES_carstatus", "在库");
		List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
		page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(
				com.codegenerate.carmgr.service.Car_mgrService.class)
				.pagedQuery(page, propertyFilters);
		List list = (java.util.ArrayList) page.getResult();
		return CommonUtils.getJsonFromList(list, null).toString();
	}

  @ResponseBody @RequestMapping("contractcopyupload-upload")  public String contractcopyuploadUpload(@org.springframework.beans.factory.annotation.Qualifier("attachment") MultipartFile attachment, HttpServletResponse response) { 	com.ibusiness.doc.store.StoreDTO storeDTO = null; 	if (null != attachment && attachment.getSize() > 0) {    	try {			storeDTO = storeConnector.save("ibfile", attachment.getInputStream(), attachment.getOriginalFilename());		} catch (Exception e) {e.printStackTrace();}}    return null == storeDTO ? "" : storeDTO.getKey();} @RequestMapping("contractcopyupload-download") public void contractcopyuploadDownload(@RequestParam("path") String path, @RequestParam("filename") String filename, HttpServletResponse response) throws Exception {    java.io.InputStream is = null;    try {        com.ibusiness.core.util.ServletUtils.setFileDownloadHeader(response, filename);        is = storeConnector.get("ibfile", path).getInputStream();        com.ibusiness.core.util.IoUtils.copyStream(is, response.getOutputStream());    } finally { if (is != null) {is.close();}} }
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("special_driver_info-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = special_driver_infoService.pagedQuery(page, propertyFilters);
        List<Special_driver_infoEntity> beans = (List<Special_driver_infoEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("专车司机信息页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("account", "name", "cellphone", "eid", "sex", "address", "publiahgrd", "birth", "nation", "eidurl", "drivernum", "driverfilenum", "predriverdate", "precartype", "validstartdate", "validtrem", "drivernumurl", "carname", "carnum", "carframenum", "carcolor", "drivertype", "contractid", "contractterm", "contractsumdeposit", "contractvalidday", "contractoverdate", "permonthrent", "permonthday", "cardeposit", "contractcopyupload", "status", "id", "scopeid", "platformto", "logintel");
        tableModel.setTableName("IB_SPECIAL_DRIVER_INFO");
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
    @RequestMapping("special_driver_info-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("account", "name", "cellphone", "eid", "sex", "address", "publiahgrd", "birth", "nation", "eidurl", "drivernum", "driverfilenum", "predriverdate", "precartype", "validstartdate", "validtrem", "drivernumurl", "carname", "carnum", "carframenum", "carcolor", "drivertype", "contractid", "contractterm", "contractsumdeposit", "contractvalidday", "contractoverdate", "permonthrent", "permonthday", "cardeposit", "contractcopyupload", "status", "id", "scopeid", "platformto", "logintel");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.special.entity.Special_driver_infoEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/special_driver_info/special_driver_info-list.do";
    }
    /**
     * 群发页面
     */
    @RequestMapping("special_record_mgr-mass-input")
    public String input_mass(@RequestParam("selectedItem") List<String> selectedItem, Model model) {
        Special_record_mgrEntity entity = new Special_record_mgrEntity();
        entity.setEventdate(new Date());
        String cellphone = "";
        if (null!=selectedItem) {
        	for (String item : selectedItem) {
        		item = item.replaceAll("'", "");
        		if (!CommonUtils.isNull(item)) {
        			Special_driver_infoEntity sdiEntity = special_driver_infoService.get(item);
        			cellphone = cellphone + "," + sdiEntity.getCellphone();
        		}
        	}
        	entity.setCellphone(cellphone);
        }
        entity.setAccount("0");
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sentpersonFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> sentpersonItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sentpersonFTCMap.get("SENTPERSON").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sentpersonItems", sentpersonItems);
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> infotypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIAL_RECORD_MGR", "specialrecordmgr");List<com.ibusiness.common.model.ConfSelectItem> infotypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(infotypeFTCMap.get("INFOTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("infotypeItems", infotypeItems);
        return "codegenerate/special/special_driver_info-mass-input.jsp";
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
                		} else {
                			System.out.println("bean.getToken  is null～～～ ");
                		}
//                	}
                }
    		}
    	}
        return "redirect:/special_driver_info/special_driver_info-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setSpecial_driver_infoService(Special_driver_infoService special_driver_infoService) {
        this.special_driver_infoService = special_driver_infoService;
    }
    @Resource
    public void setCar_mgrService(Car_mgrService car_mgrService) {
        this.car_mgrService = car_mgrService;
    }
    @Resource
	public void setCustomer_mgrService(Customer_mgrService customer_mgrService) {
	    this.customer_mgrService = customer_mgrService;
	}
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
    @Resource
	public void setSpecial_record_mgrService(Special_record_mgrService special_record_mgrService) {
	    this.special_record_mgrService = special_record_mgrService;
	}
}
