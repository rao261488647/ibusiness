package com.ibusiness.cms.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.cms.entity.CmsArticle;
import com.ibusiness.cms.service.CmsArticleService;
import com.ibusiness.cms.service.CmsCatalogService;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.doc.store.StoreConnector;
import com.ibusiness.doc.store.StoreDTO;
import com.ibusiness.security.api.scope.ScopeHolder;
import com.ibusiness.security.util.SpringSecurityUtils;
/**
 * 公告,文章管理controller
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("/cms")
public class CmsArticleController {
    private CmsArticleService cmsArticleService;
    private CmsCatalogService cmsCatalogService;
    private BeanMapper beanMapper = new BeanMapper();
    private MessageHelper messageHelper;
    private StoreConnector storeConnector;
    private UserBaseDao userBaseDao;

    /**
     * 列表
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("cms-article-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = cmsArticleService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "ibusiness/cms/cms-article-list.jsp";
    }

    /**
     * 新建/编辑
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("cms-article-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            CmsArticle cmsArticle = cmsArticleService.get(id);
            model.addAttribute("model", cmsArticle);
        }

        model.addAttribute("cmsCatalogs", cmsCatalogService.findBy("scopeid", CommonBusiness.getInstance().getCurrentUserScopeId()));

        return "ibusiness/cms/cms-article-input.jsp";
    }

    /**
     * 保存
     * @param cmsArticle
     * @param cmsCatalogId
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("cms-article-save")
    public String save(@ModelAttribute CmsArticle cmsArticle, RedirectAttributes redirectAttributes) {
        String id = cmsArticle.getId();
        String cmsCatalogId = cmsArticle.getCmsCatalog().getId();
        CmsArticle dest = null;

        if (id != null) {
            dest = cmsArticleService.get(id);
            beanMapper.copy(cmsArticle, dest);
            dest.setCmsCatalog(cmsCatalogService.get(cmsCatalogId));
            cmsArticleService.save(dest);
        } else {
            dest = cmsArticle;
            dest.setUserId(SpringSecurityUtils.getCurrentUserId());
            dest.setCreateTime(new Date());
            dest.setCmsCatalog(cmsCatalogService.get(cmsCatalogId));
            dest.setId(UUID.randomUUID().toString());
            dest.setScopeid(ScopeHolder.getScopeId());
            cmsArticleService.saveInsert(dest);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/cms/cms-article-list.do";
    }

    /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("cms-article-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem,
            RedirectAttributes redirectAttributes) {
        List<CmsArticle> cmsArticles = cmsArticleService.findByIds(selectedItem);
        cmsArticleService.removeAll(cmsArticles);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/cms/cms-article-list.do";
    }

    @RequestMapping("cms-article-checkName")
    @ResponseBody
    public boolean checkName(@RequestParam("name") String name, @RequestParam(value = "id", required = false) String id)
            throws Exception {
        String hql = "from CmsArticle where name=?";
        Object[] params = { name };

        if (id != null) {
            hql = "from CmsArticle where name=? and id<>?";
            params = new Object[] { name, id };
        }

        CmsArticle cmsArticle = cmsArticleService.findUnique(hql, params);

        boolean result = (cmsArticle == null);

        return result;
    }

    /**
     * 内容,编辑内容的，样式，格式，字体等
     */
    @RequestMapping("cms-article-uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("CKEditorFuncNum") String callback, @RequestParam("upload") MultipartFile attachment) throws Exception {
        StoreDTO storeDto = storeConnector.save("cms/html/r/images", attachment.getInputStream(), attachment.getOriginalFilename());

        return "<script type='text/javascript'>" + "window.parent.CKEDITOR.tools.callFunction(" + callback
                + ",'" + "r/images/" + storeDto.getKey() + "','')" + "</script>";
    }
    /**
     * 显示/预览 TODO
     */
    @RequestMapping("cms-article-view")
    public String view(@RequestParam("id") String id, Model model) {
        CmsArticle cmsArticle = cmsArticleService.get(id);
        String userName = "";
        
//        String html = "";
//        html = html + "<html dir=\"ltr\">";
//        html = html + "<head>";
//        html = html + "  <title>" + cmsArticle.getTitle() + "</title>";
//        html = html + "  <link type=\"text/css\" rel=stylesheet href=\"/iBusiness/plugin/ckeditor/contents.css\">";
//        html = html + "</head>";
//        html = html + "<body class=\"cke_show_borders\" >";
//        html = html + cmsArticle.getContent();
//        // 
//        html = html + "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 500px;\">";
//        html = html + "    <tbody>";
//        html = html + "        <tr>";
//        html = html + "            <td>发布人：</td>";
//        //
//        if (!CommonUtils.isNull(cmsArticle.getUserId())) {
//            UserBase userBase = userBaseDao.get(cmsArticle.getUserId());
//            if (null != userBase) {
//                userName = userBase.getDisplayName();
//            }
//        }
//        html = html + "            <td>"+userName+"</td>";
//        html = html + "        </tr>";
//        html = html + "        <tr>";
//        html = html + "            <td>发布时间：</td>";
//        html = html + "            <td>"+cmsArticle.getCreateTime()+"</td>";
//        html = html + "        </tr>";
//        html = html + "    </tbody>";
//        html = html + "</table>";
//        // 
//        html = html + "</body>";
//        html = html + "</html>";
//        // 
//        model.addAttribute("html", html);
        
        ///////////////////////////////////////////////////////////////////////////////////
        if (!CommonUtils.isNull(cmsArticle.getUserId())) {
            UserBase userBase = userBaseDao.get(cmsArticle.getUserId());
            if (null != userBase) {
                userName = userBase.getDisplayName();
            }
        }
        model.addAttribute("title", cmsArticle.getTitle());
        model.addAttribute("content", cmsArticle.getContent());
        model.addAttribute("userName", userName);
        model.addAttribute("creatTime", cmsArticle.getCreateTime());
        model.addAttribute("id", id);

        return "ibusiness/cms/cms-article-view.jsp";
    }
    /**
     * 公告查看列表页面
     * 
     * @return
     */
    @RequestMapping("cms-article-view-list")
    public String viewList(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 设置排序信息
        page.setOrderBy("createTime");
        page.setOrder("DESC");
        page = cmsArticleService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "ibusiness/cms/cms-article-view-list.jsp";
    }
    // ~ ======================================================================
    @Resource
    public void setCmsArticleService(CmsArticleService cmsArticleService) {
        this.cmsArticleService = cmsArticleService;
    }
    @Resource
    public void setCmsCatalogService(CmsCatalogService cmsCatalogService) {
        this.cmsCatalogService = cmsCatalogService;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setStoreConnector(StoreConnector storeConnector) {
        this.storeConnector = storeConnector;
    }
    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }
}
