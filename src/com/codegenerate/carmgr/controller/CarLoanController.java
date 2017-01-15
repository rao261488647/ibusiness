package com.codegenerate.carmgr.controller;

import java.io.File;
import java.util.Date;
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

import com.codegenerate.carmgr.entity.CarLoanEntity;
import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.codegenerate.carmgr.service.CarLoanService;
import com.codegenerate.carmgr.service.Car_mgrService;
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
import com.report.utils.DateUtil;

/**   
 * @Title: Controller
 * @Description: 车辆借出管理
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("carLoan")
public class CarLoanController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private CarLoanService carLoanService;
    
    private Car_mgrService car_mgrService;
    
    private Special_driver_infoService special_driver_infoService;
   /**
     * 列表
     */
    @RequestMapping("list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
    	page.setOrderBy("carloandate");
    	page.setOrder("desc");
        // 根据条件查询数据
        page = carLoanService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
     // 在controller中设置页面控件用的数据
        model.addAttribute("caridItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_LOAN", "carloan","CARID"));
        // 状态
        model.addAttribute("statusItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_LOAN", "carloan","STATUS"));
        // 借出人
        model.addAttribute("specialidItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_LOAN", "carloan","SPECIALID"));
        // 返回JSP
        return "codegenerate/carLoan/list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        CarLoanEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = carLoanService.get(id);
        } else {
            entity = new CarLoanEntity();
            entity.setDate(DateUtil.toDate(DateUtil.toString(new Date())));
            entity.setLoandate(DateUtil.toDate(DateUtil.toString(new Date())));
            entity.setStatus("借出中");
            //entity.setCarstatus("在库");
        }
        
        // 默认值公式
        entity = (CarLoanEntity) new FormulaCommon().defaultValue(entity, "IB_CAR_LOAN");
//        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        model.addAttribute("caridItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_LOAN", "carloan","CARID"));
        // 状态
        model.addAttribute("statusItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_LOAN", "carloan","STATUS"));
        // 借出人
        model.addAttribute("specialidItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_LOAN", "carloan","SPECIALID"));
        return "codegenerate/carLoan/input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    public String save(@ModelAttribute CarLoanEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        
        if ("已归还".equals(entity.getStatus())) {
        	if (entity.getBackdate() == null) {
        		messageHelper.addFlashMessage(redirectAttributes, "core.error.save", "保存成功");
        		return null;
        	}
        }
        Car_mgrEntity car_mgrEntity = car_mgrService.get(entity.getCarid());
        if (car_mgrEntity != null) {
        	entity.setCarnum(car_mgrEntity.getCarnum());
        	entity.setTypename(car_mgrEntity.getTypename());
        	car_mgrEntity.setIsloan(entity.getStatus());
        	if ("已归还".equals(entity.getStatus())) {
        		car_mgrEntity.setCarstatus("在库");
        	} else {
        		car_mgrEntity.setCarstatus("借出");
        	}
        	car_mgrService.update(car_mgrEntity);
        }
        
//        Special_driver_infoEntity special_driver_infoEntity = special_driver_infoService.get(entity.getSpecialid());
//        if (special_driver_infoEntity != null) {
//        	entity.setSpecialname(special_driver_infoEntity.getName());
//        }
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            carLoanService.insert(entity);
        } else {
        	carLoanService.update(entity);
        }
        
        return "redirect:/carLoan/list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<CarLoanEntity> entitys = carLoanService.findByIds(selectedItem);
        for (CarLoanEntity entity : entitys) {
        	carLoanService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/carLoan/list.do";
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
        page = carLoanService.pagedQuery(page, propertyFilters);
        List<CarLoanEntity> beans = (List<CarLoanEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("车辆借出管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("id", "date", "specialname", "typename", "carnum", "loandate", "status", "backdate", "remark");
        tableModel.setTableName("IB_CAR_LOAN");
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
        return "redirect:/carLoan/list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    
    @Resource
	public void setCarLoanService(
			CarLoanService carLoanService) {
		this.carLoanService = carLoanService;
	}

    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
    
    @Resource
	public void setCar_mgrService(Car_mgrService car_mgrService) {
		this.car_mgrService = car_mgrService;
	}
    @Resource
	public void setSpecial_driver_infoService(
			Special_driver_infoService special_driver_infoService) {
		this.special_driver_infoService = special_driver_infoService;
	}
    
    
}
