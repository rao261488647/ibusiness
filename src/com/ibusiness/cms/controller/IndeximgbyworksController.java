package com.ibusiness.cms.controller;

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

import com.ibusiness.cms.entity.IndeximgbyworksEntity;
import com.ibusiness.cms.service.IndeximgbyworksService;
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
 * 作品展示表页面
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("indeximgbyworks")
public class IndeximgbyworksController {

	private MessageHelper messageHelper;
	private com.ibusiness.doc.store.StoreConnector storeConnector;
	private IndeximgbyworksService indeximgbyworksService;

	/**
	 * 列表
	 */
	@RequestMapping("indeximgbyworks-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {
		// 查询条件Filter过滤器
		List<PropertyFilter> propertyFilters = PropertyFilter
				.buildFromMap(parameterMap);
		// 添加当前公司(用户范围)ID查询
		propertyFilters = CommonBusiness.getInstance().editPFByScopeId(
				propertyFilters);
		// 根据条件查询数据
		page = indeximgbyworksService.pagedQuery(page, propertyFilters);
		model.addAttribute("page", page);
		// 返回JSP
		return "ibusiness/cms/indeximgbyworks-list.jsp";
	}

	/**
	 * 插入
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("indeximgbyworks-input")
	public String input(
			@RequestParam(value = "id", required = false) String id, Model model) {
		IndeximgbyworksEntity entity = null;
		if (!CommonUtils.isNull(id)) {
			entity = indeximgbyworksService.get(id);
		} else {
			entity = new IndeximgbyworksEntity();
		}

		// 默认值公式
		entity = (IndeximgbyworksEntity) new FormulaCommon().defaultValue(
				entity, "IB_INDEXIMGBYWORKS");

		model.addAttribute("model", entity);

		// 在controller中设置页面控件用的数据
		List<ConfSelectItem> showflagItems = (List<ConfSelectItem>) CommonUtils.getListFromJson("[{\"key\":\"是\",\"value\":\"是\"},{\"key\":\"否\",\"value\":\"否\"}]", ConfSelectItem.class);
		model.addAttribute("showflagItems", showflagItems);
		return "ibusiness/cms/indeximgbyworks-input.jsp";
	}

	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("indeximgbyworks-save")
	public String save(@ModelAttribute IndeximgbyworksEntity entity,
			RedirectAttributes redirectAttributes) throws Exception {
		// 先进行校验
		// 再进行数据复制
		String id = entity.getId();
		if (CommonUtils.isNull(id)) {
			entity.setId(UUID.randomUUID().toString());
			indeximgbyworksService.insert(entity);
		} else {
			indeximgbyworksService.update(entity);
		}
		messageHelper.addFlashMessage(redirectAttributes, "core.success.save",
				"保存成功");
		return "redirect:/indeximgbyworks/indeximgbyworks-list.do";
	}

	/**
	 * 删除
	 * 
	 * @param selectedItem
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("indeximgbyworks-remove")
	public String remove(
			@RequestParam("selectedItem") List<String> selectedItem,
			RedirectAttributes redirectAttributes) {
		List<IndeximgbyworksEntity> entitys = indeximgbyworksService
				.findByIds(selectedItem);
		for (IndeximgbyworksEntity entity : entitys) {
			indeximgbyworksService.remove(entity);
		}
		messageHelper.addFlashMessage(redirectAttributes,
				"core.success.delete", "删除成功");

		return "redirect:/indeximgbyworks/indeximgbyworks-list.do";
	}

	/**
	 * 控件添加的方法 ========
	 */
	@ResponseBody
	@RequestMapping("imgurl-upload")
	public String imgurlUpload(
			@org.springframework.beans.factory.annotation.Qualifier("attachment") MultipartFile attachment,
			HttpServletResponse response) {
		com.ibusiness.doc.store.StoreDTO storeDTO = null;
		if (null != attachment && attachment.getSize() > 0) {
			try {
				storeDTO = storeConnector.save("imgworks",
						attachment.getInputStream(),
						attachment.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null == storeDTO ? "" : storeDTO.getKey();
	}

	@RequestMapping("imgurl-download")
	public void imgurlDownload(@RequestParam("path") String path,
			@RequestParam("filename") String filename,
			HttpServletResponse response) throws Exception {
		java.io.InputStream is = null;
		try {
			com.ibusiness.core.util.ServletUtils.setFileDownloadHeader(
					response, filename);
			is = storeConnector.get("imgworks", path).getInputStream();
			com.ibusiness.core.util.IoUtils.copyStream(is,
					response.getOutputStream());
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * excel导出
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("indeximgbyworks-export")
	public void excelExport(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap,
			HttpServletResponse response) {
		List<PropertyFilter> propertyFilters = PropertyFilter
				.buildFromMap(parameterMap);
		// 根据当前公司(用户范围)ID进行查询
		propertyFilters = CommonBusiness.getInstance().editPFByScopeId(
				propertyFilters);
		page = indeximgbyworksService.pagedQuery(page, propertyFilters);
		List<IndeximgbyworksEntity> beans = (List<IndeximgbyworksEntity>) page
				.getResult();

		TableModel tableModel = new TableModel();
		// excel文件名
		tableModel.setExcelName("作品展示表页面"
				+ CommonUtils.getInstance().getCurrentDateTime());
		// 列名
		tableModel.addHeaders("scopeid", "id", "imgurl", "imginfo", "showflag");
		tableModel.setTableName("IB_INDEXIMGBYWORKS");
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
	@RequestMapping("indeximgbyworks-importExcel")
	public String importExport(
			@RequestParam("attachment") MultipartFile attachment,
			HttpServletResponse response) {
		try {
			File file = new File("test.xls");
			attachment.transferTo(file);
			//
			TableModel tableModel = new TableModel();
			// 列名
			tableModel.addHeaders("scopeid", "id", "imgurl", "imginfo",
					"showflag");
			// 导入
			new ExcelCommon().uploadExcel(file, tableModel,
					"com.codegenerate.test.entity.IndeximgbyworksEntity");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/indeximgbyworks/indeximgbyworks-list.do";
	}

	// ======================================================================
	@Resource
	public void setMessageHelper(MessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	@Resource
	public void setIndeximgbyworksService(
			IndeximgbyworksService indeximgbyworksService) {
		this.indeximgbyworksService = indeximgbyworksService;
	}

	@Resource
	public void setStoreConnector(
			com.ibusiness.doc.store.StoreConnector storeConnector) {
		this.storeConnector = storeConnector;
	}
}
