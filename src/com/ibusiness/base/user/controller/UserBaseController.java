package com.ibusiness.base.user.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.auth.dao.RoleDefDao;
import com.ibusiness.base.group.dao.JobInfoDao;
import com.ibusiness.base.group.dao.OrgCompanyDao;
import com.ibusiness.base.group.entity.OrgCompany;
import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.base.user.service.UserService;
import com.ibusiness.bridge.user.UserCache;
import com.ibusiness.bridge.user.UserDTO;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.util.SimplePasswordEncoder;
import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * 用户管理
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("user")
public class UserBaseController {
    private UserBaseDao userBaseDao;
    private UserCache userCache;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();
    private SimplePasswordEncoder simplePasswordEncoder;
    private UserService userService;
    // 职务管理表DAO
    private JobInfoDao jobInfoDao;
    // 角色模板表DAO
    private RoleDefDao roleDefDao;
    // 公司信息
    private OrgCompanyDao orgCompanyDao;

    /**
     * 查看
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("user-base-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
    	// 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
    	// 设置排序信息
        page.setOrderBy("ref");
        page.setOrder("ASC");
    	page = userBaseDao.pagedQuery(page, propertyFilters);
    	
        // 查询公司名字
        List<UserBase> list = (List<UserBase>) page.getResult();
        Map<String, OrgCompany> map = CommonBusiness.getInstance().getCompanyMap();
        for (UserBase bean : list) {
        	if (map.containsKey(bean.getCompanyId())) {
        		bean.setCompanyName(map.get(bean.getCompanyId()).getName());
        	}
        }
        model.addAttribute("page", page);

        return "common/user/user-base-list.jsp";
    }

    /**
     * 插入页面
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("user-base-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        UserBase userBase = null;
        // 
        if (!CommonUtils.isNull(id)) {
            userBase = userBaseDao.get(id);
        } else {
            userBase = new UserBase();
            String companyId = userBaseDao.get(SpringSecurityUtils.getCurrentUserId()).getCompanyId();
            userBase.setCompanyId(companyId);
            userBase.setUserRepoId("1");
        }
        // 
        model.addAttribute("model", userBase);
        // 设置职务管理下拉数据
        model.addAttribute("jobInfos", jobInfoDao.getAll());
        // 角色模板管理下拉数据
        model.addAttribute("roleDefs", roleDefDao.getAll());
        // 公司信息下拉数据
        model.addAttribute("companyidItems", orgCompanyDao.getAll());
        // 当前用户
        model.addAttribute("currentUserName", SpringSecurityUtils.getCurrentUsername());

        return "common/user/user-base-input.jsp";
    }

    /**
     * 保存
     * 
     * @param userBase
     * @param confirmPassword
     * @param userRepoId
     * @param parameterMap
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping("user-base-save")
    public String save(@ModelAttribute UserBase userBase, @RequestParam(value = "confirmPassword", required = false)
    String confirmPassword, @RequestParam("userRepoId") String userRepoId, @RequestParam("jobId") String jobId, @RequestParam("roleId") String roleId,
    @RequestParam Map<String, Object> parameterMap, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        if (userBase.getPassword() != null) {
            if (!userBase.getPassword().equals(confirmPassword)) {
                messageHelper.addFlashMessage(redirectAttributes, "user.user.input.passwordnotequals", "两次输入密码不符");

                return "common/user/user-base-input.jsp";
            }
            if (simplePasswordEncoder != null) {
                userBase.setPassword(simplePasswordEncoder.encode(userBase.getPassword()));
            }
        }
        // 再进行数据复制
        UserBase dest = null;
        String id = userBase.getId();

        if (!CommonUtils.isNull(id)) {
            dest = userBaseDao.get(id);
            dest.setStatus(0);
            beanMapper.copy(userBase, dest);
            // 保存职务信息
            dest.setJobInfo(jobInfoDao.get(jobId));
            // 保存角色信息
            dest.setRoleDef(roleDefDao.get(roleId));
            // 用户组织ID
            dest.setUserRepoId("1");
            // 设置范围ID
            dest.setScopeid(dest.getCompanyId());
            // 更新用户基础信息
            userService.updateUser(dest);
        } else {
            dest = userBase;
            // 保存职务信息
            dest.setJobInfo(jobInfoDao.get(jobId));
            // 保存角色信息
            dest.setRoleDef(roleDefDao.get(roleId));
            // 设置默认的css样式
            dest.setCss("Cerulean");
            // UUID
            dest.setId(UUID.randomUUID().toString());
            // 设置范围ID
            dest.setScopeid(dest.getCompanyId());
            dest.setUserRepoId("1");
            userService.insertUser(dest);
        }
        // 更新重设内存数据
        CommonBusiness.getInstance().setUserBaseMap(new HashMap<String, UserBase>());

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        // 帐号/用户缓存接口
        UserDTO userDto = new UserDTO();
        userDto.setId(dest.getId());
        userDto.setUsername(dest.getUsername());
        userDto.setRef(null == dest.getRef()? "9" : dest.getRef().toString());
        userDto.setUserRepoRef(userRepoId);
        userCache.removeUser(userDto);

        return "redirect:/user/user-base-list.do";
    }

    /**
     * 删除
     * 
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("user-base-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<UserBase> userBases = userBaseDao.findByIds(selectedItem);

        for (UserBase userBase : userBases) {
            userService.removeUser(userBase);

            UserDTO userDto = new UserDTO();
            userDto.setId(userBase.getId());
            userDto.setUsername(userBase.getUsername());
            userDto.setRef(null == userBase.getRef() ? "9" : userBase.getRef().toString());
            userDto.setUserRepoRef("1");
            userCache.removeUser(userDto);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/user/user-base-list.do";
    }

    /**
     * 查询功能
     * 
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("user-base-search")
    public String search(@ModelAttribute
    Page page, Model model) {
        StringBuilder buff = new StringBuilder("select ub from UserBase ub");

        Map<String, Object> params = new HashMap<String, Object>();

        if (!params.isEmpty()) {
            page = userBaseDao.pagedQuery(buff.toString(), page.getPageNo(), page.getPageSize(), params);
            model.addAttribute("page", page);
        }

        return "common/user/user-base-search.jsp";
    }

    /**
     * check
     * 
     * @param username
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("user-base-checkUsername")
    @ResponseBody
    public boolean checkUsername(@RequestParam("username") String username, 
            @RequestParam(value = "id", required = false) String id) throws Exception {
        String hql = "from UserBase where username=?";
        Object[] params = {
            username };

        if (id != null) {
            hql = "from UserBase where username=? and id<>?";
            params = new Object[] {
                    username, id };
        }

        boolean result = userBaseDao.findUnique(hql, params) == null;
        return result;
    }

    // ~ ======================================================================
    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }
    @Resource
    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setSimplePasswordEncoder(SimplePasswordEncoder simplePasswordEncoder) {
        this.simplePasswordEncoder = simplePasswordEncoder;
    }
    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Resource
    public void setJobInfoDao(JobInfoDao jobInfoDao) {
        this.jobInfoDao = jobInfoDao;
    }
    @Resource
    public void setRoleDefDao(RoleDefDao roleDefDao) {
        this.roleDefDao = roleDefDao;
    }
    // 公司信息
    @Resource
    public void setOrgCompanyDao(OrgCompanyDao orgCompanyDao) {
        this.orgCompanyDao = orgCompanyDao;
    }
}
