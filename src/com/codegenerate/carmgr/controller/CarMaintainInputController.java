package com.codegenerate.carmgr.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codegenerate.carmgr.entity.CarMaintainFactoryEntity;
import com.codegenerate.carmgr.entity.CarMaintainInputEntity;
import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.codegenerate.carmgr.service.CarMaintainFactoryService;
import com.codegenerate.carmgr.service.CarMaintainInputService;
import com.codegenerate.carmgr.service.Car_mgrService;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;

/**   
 * @Title: Controller
 * @Description: 维修保养录入
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("carMaintainInput")
public class CarMaintainInputController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private CarMaintainInputService carMaintainInputService;
    private Car_mgrService car_mgrService;
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
    	page.setOrder("desc");
    	page.setOrderBy("enterfactorydate");
        // 根据条件查询数据
        page = carMaintainInputService.pagedQuery(page, propertyFilters);
        
        if (page.getResult() != null) {
        	List<CarMaintainInputEntity> carMaintainInputEntityList = (List<CarMaintainInputEntity>) page.getResult();
        	for (int i=0;i<carMaintainInputEntityList.size();i++) {
        		CarMaintainInputEntity carMaintainInputEntity = carMaintainInputEntityList.get(i);
        		Car_mgrEntity car_mgrEntity = car_mgrService.get(carMaintainInputEntity.getCarid());
        		if (car_mgrEntity != null) {
        			carMaintainInputEntity.setCarnum(car_mgrEntity.getCarnum());
        			carMaintainInputEntity.setTypename(car_mgrEntity.getTypename());
        		}
        		CarMaintainFactoryEntity carMaintainFactoryEntity = carMaintainFactoryService.get(carMaintainInputEntity.getMaintainid());
        		if (carMaintainFactoryEntity != null) {
        			carMaintainInputEntity.setMaintainname(carMaintainFactoryEntity.getMaintainname());
        		}
        	}
        }
        model.addAttribute("page", page);
       // 在controller中设置页面控件用的数据
        // 车辆新新
        model.addAttribute("caridItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","CARID"));
        // 进厂类别
        model.addAttribute("enterfactorytypeItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","ENTERFACTORYTYPE"));
        // 维修厂
        model.addAttribute("maintainidItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","MAINTAINID"));
        // 替换车
        model.addAttribute("isreplacecarItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","ISREPLACECAR"));
        // 返回JSP
        return "codegenerate/carMaintainInput/list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        CarMaintainInputEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = carMaintainInputService.get(id);
        } else {
            entity = new CarMaintainInputEntity();
            //entity.setCarstatus("在库");
        }
        
        // 默认值公式
        entity = (CarMaintainInputEntity) new FormulaCommon().defaultValue(entity, "IB_CAR_MAINTAIN_INPUT");
//        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        model.addAttribute("caridItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","CARID"));
        model.addAttribute("enterfactorytypeItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","ENTERFACTORYTYPE"));
        model.addAttribute("maintainidItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","MAINTAINID"));
        model.addAttribute("isreplacecarItems", CommonBusiness.getInstance().getListConfSelectItem("IB_CAR_MAINTAIN_INPUT", "carmaintaininput","ISREPLACECAR"));
        return "codegenerate/carMaintainInput/input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    public String save(@ModelAttribute CarMaintainInputEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        Car_mgrEntity car_mgrEntity = car_mgrService.get(entity.getCarid());
        if (car_mgrEntity != null) {
        	entity.setCarnum(car_mgrEntity.getCarnum());
        	entity.setTypename(car_mgrEntity.getTypename());
        	
        	if ("保养".equals(entity.getEnterfactorytype())) {
        		car_mgrEntity.setIsmaintain("正常");
        	} else {
        		car_mgrEntity.setIsmaintain("维修中");
        	}
        	// 已出厂
        	if (entity.getLeavefactorydate() != null) {
        		car_mgrEntity.setIsmaintain("正常");
        	}
        	car_mgrService.update(car_mgrEntity);
        }
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
//            entity.setCarstatus("在库");
//            entity.setInfactorydate(new Date());
            carMaintainInputService.insert(entity);
        } else {
        	carMaintainInputService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/carMaintainInput/list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<CarMaintainInputEntity> entitys = carMaintainInputService.findByIds(selectedItem);
        for (CarMaintainInputEntity entity : entitys) {
        	carMaintainInputService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/carMaintainInput/list.do";
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
        page = carMaintainInputService.pagedQuery(page, propertyFilters);
        List<Car_mgrEntity> beans = (List<Car_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("维修保养录入页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("id", "carnum", "typename", "enterfactorydate", "enterfactorytype", "maintainname", "maintaincontent", "isreplacecar", "leavefactorydate", "remark");
        tableModel.setTableName("IB_CAR_MAINTAIN_INPUT");
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
        return "redirect:/carMaintainInput/list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    
    @Resource
	public void setCarMaintainInputService(
			CarMaintainInputService carMaintainInputService) {
		this.carMaintainInputService = carMaintainInputService;
	}
    @Resource
	public void setCar_mgrService(Car_mgrService car_mgrService) {
		this.car_mgrService = car_mgrService;
	}

	@Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
	@Resource
	public void setCarMaintainFactoryService(
			CarMaintainFactoryService carMaintainFactoryService) {
		this.carMaintainFactoryService = carMaintainFactoryService;
	}
	
}
