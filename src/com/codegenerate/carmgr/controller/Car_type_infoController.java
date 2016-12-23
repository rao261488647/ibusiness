package com.codegenerate.carmgr.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.codegenerate.carmgr.entity.Car_type_infoEntity;
import com.codegenerate.carmgr.service.Car_mgrService;
import com.codegenerate.carmgr.service.Car_type_infoService;
import com.codegenerate.special.entity.Special_driver_infoEntity;
import com.codegenerate.special.service.Special_driver_infoService;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;

/**   
 * @Title: Controller
 * @Description: 车型管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("car_type_info")
public class Car_type_infoController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Car_type_infoService car_type_infoService;
    private Car_mgrService car_mgrService;
    private Special_driver_infoService special_driver_infoService;
   /**
     * 列表
     */
    @RequestMapping("car_type_info-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = car_type_infoService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/carmgr/car_type_info-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("car_type_info-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Car_type_infoEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = car_type_infoService.get(id);
        } else {
            entity = new Car_type_infoEntity();
        }
        
        // 默认值公式
        entity = (Car_type_infoEntity) new FormulaCommon().defaultValue(entity, "IB_CAR_TYPE_INFO");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> powertypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_TYPE_INFO", "cartypeinfo");List<com.ibusiness.common.model.ConfSelectItem> powertypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(powertypeFTCMap.get("POWERTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("powertypeItems", powertypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> cartypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_TYPE_INFO", "cartypeinfo");List<com.ibusiness.common.model.ConfSelectItem> cartypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(cartypeFTCMap.get("CARTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("cartypeItems", cartypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> carriagecountFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_TYPE_INFO", "cartypeinfo");List<com.ibusiness.common.model.ConfSelectItem> carriagecountItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(carriagecountFTCMap.get("CARRIAGECOUNT").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("carriagecountItems", carriagecountItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> gearboxFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_TYPE_INFO", "cartypeinfo");List<com.ibusiness.common.model.ConfSelectItem> gearboxItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(gearboxFTCMap.get("GEARBOX").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("gearboxItems", gearboxItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> seatcountFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CAR_TYPE_INFO", "cartypeinfo");List<com.ibusiness.common.model.ConfSelectItem> seatcountItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(seatcountFTCMap.get("SEATCOUNT").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("seatcountItems", seatcountItems);
        return "codegenerate/carmgr/car_type_info-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("car_type_info-save")
    public String save(@ModelAttribute Car_type_infoEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            car_type_infoService.insert(entity);
        } else {
        	Car_type_infoEntity old_entity = car_type_infoService.get(id);
        	String old_typename = old_entity.getTypename();
        	//
        	old_entity.setTypename(entity.getTypename());
        	old_entity.setPowertype(entity.getPowertype());
        	old_entity.setCartype(entity.getCartype());
        	old_entity.setCarriagecount(entity.getCarriagecount());
        	old_entity.setGearbox(entity.getGearbox());
        	old_entity.setSeatcount(entity.getSeatcount());
        	old_entity.setSwept(entity.getSwept());
        	old_entity.setDisplayment(entity.getDisplayment());
        	old_entity.setListyear(entity.getListyear());
        	old_entity.setId(entity.getId());
        	old_entity.setScopeid(entity.getScopeid());
        	old_entity.setUploadurl(entity.getUploadurl());
        	//
            car_type_infoService.update(old_entity);
            if (null!=old_typename && !old_typename.equals(entity.getTypename())) {
            	// 更新 入库车辆, 出库车辆 的车型名称
                List<Car_mgrEntity> carList = car_mgrService.find("from Car_mgrEntity where typename=?", old_typename);
                if (null != carList && carList.size() > 0) {
                	for (Car_mgrEntity carmgr : carList) {
                		carmgr.setTypename(entity.getTypename());
                		car_mgrService.save(carmgr);
                		// 
                		List<Special_driver_infoEntity> sdList = special_driver_infoService.find("from Special_driver_infoEntity where carname=?", old_typename);
                		if (null != sdList && sdList.size() > 0) {
                			for (Special_driver_infoEntity specialdriver : sdList) {
                				specialdriver.setCarname(entity.getTypename());
                				special_driver_infoService.save(specialdriver);
                			}
                		}
                	}
                }
            }
            //
            
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/car_type_info/car_type_info-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("car_type_info-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Car_type_infoEntity> entitys = car_type_infoService.findByIds(selectedItem);
        for (Car_type_infoEntity entity : entitys) {
            car_type_infoService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/car_type_info/car_type_info-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                 @ResponseBody @RequestMapping("uploadurl-upload")  public String uploadurlUpload(@org.springframework.beans.factory.annotation.Qualifier("attachment") MultipartFile attachment, HttpServletResponse response) {   com.ibusiness.doc.store.StoreDTO storeDTO = null;    if (null != attachment && attachment.getSize() > 0) {       try {           storeDTO = storeConnector.save("ibimg", attachment.getInputStream(), attachment.getOriginalFilename());       } catch (Exception e) {e.printStackTrace();}}    return null == storeDTO ? "" : storeDTO.getKey();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("car_type_info-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = car_type_infoService.pagedQuery(page, propertyFilters);
        List<Car_type_infoEntity> beans = (List<Car_type_infoEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("车型管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("typename", "powertype", "cartype", "carriagecount", "gearbox", "seatcount", "swept", "displayment", "listyear", "id", "scopeid", "uploadurl");
        tableModel.setTableName("IB_CAR_TYPE_INFO");
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
    @RequestMapping("car_type_info-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("typename", "powertype", "cartype", "carriagecount", "gearbox", "seatcount", "swept", "displayment", "listyear", "id", "scopeid", "uploadurl");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.carmgr.entity.Car_type_infoEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/car_type_info/car_type_info-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setCar_mgrService(Car_mgrService car_mgrService) {
        this.car_mgrService = car_mgrService;
    }
    @Resource
    public void setSpecial_driver_infoService(Special_driver_infoService special_driver_infoService) {
        this.special_driver_infoService = special_driver_infoService;
    }
    @Resource
    public void setCar_type_infoService(Car_type_infoService car_type_infoService) {
        this.car_type_infoService = car_type_infoService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
