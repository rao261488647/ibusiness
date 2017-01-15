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

import com.codegenerate.carmgr.entity.CarViolationEntity;
import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.codegenerate.carmgr.service.CarViolationService;
import com.codegenerate.carmgr.service.Car_mgrService;
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
 * @Description: 车辆违章录入
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("carViolation")
public class CarViolationController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private CarViolationService carViolationService;
    
    private Car_mgrService car_mgrService;
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
        page = carViolationService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
     // 车辆id
        model.addAttribute("caridItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_VIOLATION", "carviolation","CARID"));
        // 车辆星号
        model.addAttribute("typenameItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_VIOLATION", "carviolation","TYPENAME"));
        // 是否已处理
        model.addAttribute("isdisposeItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_VIOLATION", "carviolation","ISDISPOSE"));
        // 违章项目
        model.addAttribute("violationtypeItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_VIOLATION", "carviolation","VIOLATIONTYPE"));
        // 返回JSP
        return "codegenerate/carViolation/list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        CarViolationEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = carViolationService.get(id);
        } else {
            entity = new CarViolationEntity();
            entity.setIsdispose("未处理");
            //entity.setCarstatus("在库");
        }
        
        // 默认值公式
        entity = (CarViolationEntity) new FormulaCommon().defaultValue(entity, "IB_CAR_VIOLATION");
//        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
     // 车辆id
        model.addAttribute("caridItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_VIOLATION", "carviolation","CARID"));
        // 车辆星号
        model.addAttribute("typenameItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_VIOLATION", "carviolation","TYPENAME"));
        // 是否已处理
        model.addAttribute("isdisposeItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_VIOLATION", "carviolation","ISDISPOSE"));
        // 违章项目
        model.addAttribute("violationtypeItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_VIOLATION", "carviolation","VIOLATIONTYPE"));
        return "codegenerate/carViolation/input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    public String save(@ModelAttribute CarViolationEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        Car_mgrEntity car_mgrEntity = car_mgrService.get(entity.getCarid());
        if (car_mgrEntity != null) {
        	entity.setCarnum(car_mgrEntity.getCarnum());
        	entity.setTypename(car_mgrEntity.getTypename());
        }
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
//            entity.setCarstatus("在库");
//            entity.setInfactorydate(new Date());
            carViolationService.insert(entity);
        } else {
        	carViolationService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/carViolation/list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<CarViolationEntity> entitys = carViolationService.findByIds(selectedItem);
        for (CarViolationEntity entity : entitys) {
        	carViolationService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/carViolation/list.do";
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
        page = carViolationService.pagedQuery(page, propertyFilters);
        List<Car_mgrEntity> beans = (List<Car_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("车辆违章录入页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("id", "carnum", "typename", "violationdate", "violationtype", "violationproject", "violationaddress", "points", "finemoney", "isdispose", "disposedate", "remark");
        tableModel.setTableName("IB_CAR_VIOLATION");
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
        return "redirect:/carViolation/list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    
    @Resource
	public void setCarViolationService(
			CarViolationService carViolationService) {
		this.carViolationService = carViolationService;
	}

    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
    
    @Resource
	public void setCar_mgrService(Car_mgrService car_mgrService) {
		this.car_mgrService = car_mgrService;
	}
}
