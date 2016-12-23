package com.ibusiness.doc.controller;

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

import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBaseService;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.doc.entity.DocInfo;
import com.ibusiness.doc.service.DocInfoService;
import com.ibusiness.doc.store.StoreConnector;
import com.ibusiness.security.api.scope.ScopeHolder;
import com.ibusiness.security.util.SpringSecurityUtils;

@Controller
@RequestMapping("doc")
public class DocInfoController {
    private DocInfoService docInfoService;
    private BeanMapper beanMapper = new BeanMapper();
    private MessageHelper messageHelper;
    private StoreConnector storeConnector;

    /**
     * 文档查询列表
     */
    @RequestMapping("doc-info-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 当前用户
        String userId = SpringSecurityUtils.getCurrentUserId();
        propertyFilters.add(new PropertyFilter("EQS_userId", userId));
        page = docInfoService.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);

        return "ibusiness/doc/doc-info-list.jsp";
    }

    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("doc-info-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            DocInfo docInfo = docInfoService.get(id);
            model.addAttribute("model", docInfo);
        }
        // 在controller中设置页面控件用的数据
        JSONObject nameJsonObj = JSONObject.fromObject("{\"sql\":\"select typename vKey, typename vValue from ib_doctype \"}");
        String nameSql = nameJsonObj.getString("sql");
        List<Map<String, Object>> nameList = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(nameSql);
        List<ConfSelectItem> nameItems = new java.util.ArrayList<ConfSelectItem>();
        for (Map<String, Object> mapBean : nameList) {
            ConfSelectItem confSelectItem = new ConfSelectItem();
            confSelectItem.setKey(mapBean.get("vKey").toString());
            confSelectItem.setValue(mapBean.get("vValue").toString());
            nameItems.add(confSelectItem);
        }
        model.addAttribute("nameItems", nameItems);
        // 
        return "ibusiness/doc/doc-info-input.jsp";
    }

    /**
     * 保存上传文件
     */
    @RequestMapping("doc-info-save")
    public String save(@ModelAttribute DocInfo docInfo, @RequestParam Map<String, Object> parameterMap, RedirectAttributes redirectAttributes) throws Exception {
        DocInfo dest = null;
        String id = docInfo.getId();
        // 保存文件信息
        if (id != null) {
            dest = docInfoService.get(id);
            beanMapper.copy(docInfo, dest);
            docInfoService.save(dest);
        } else {
            // 插入新上传文件信息
            dest = docInfo;
            dest.setCreateTime(new Date());
            String userId = SpringSecurityUtils.getCurrentUserId();
            dest.setUserId(userId);
            dest.setId(UUID.randomUUID().toString());
            dest.setScopeid(ScopeHolder.getScopeId());
            docInfoService.saveInsert(dest);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/doc/doc-info-list.do";
    }

    /**
     * 上传
     */
    @ResponseBody
    @RequestMapping("docinfo-upload")
    public String nameUpload(@org.springframework.beans.factory.annotation.Qualifier("attachment")
    MultipartFile attachment, HttpServletResponse response) {
        com.ibusiness.doc.store.StoreDTO storeDTO = null;
        if (null != attachment && attachment.getSize() > 0) {
            try {
                storeDTO = storeConnector.save("docinfo", attachment.getInputStream(), attachment.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null == storeDTO ? "" : storeDTO.getKey();
    }
    /**
     * 下载文件
     * @param id
     * @param response
     * @throws Exception
     */
    @RequestMapping("doc-info-download")
    public void nameDownload(@RequestParam("path") String path, @RequestParam("filename") String filename, HttpServletResponse response) throws Exception {
        java.io.InputStream is = null;
        try {
            com.ibusiness.core.util.ServletUtils.setFileDownloadHeader(response, filename);
            is = storeConnector.get("docinfo", path).getInputStream();
            com.ibusiness.core.util.IoUtils.copyStream(is, response.getOutputStream());
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    /**
     * 删除文件
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("doc-info-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<DocInfo> docInfos = docInfoService.findByIds(selectedItem);
        docInfoService.removeAll(docInfos);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
        return "redirect:/doc/doc-info-list.do";
    }

    // ~ ======================================================================
    @Resource
    public void setDocInfoService(DocInfoService docInfoService) {
        this.docInfoService = docInfoService;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setStoreConnector(StoreConnector storeConnector) {
        this.storeConnector = storeConnector;
    }
}
