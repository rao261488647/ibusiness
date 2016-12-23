package com.ibusiness.base.user.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.base.user.service.UserService;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * 个人信息
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("user")
public class ProfileController {
    private UserBaseDao userBaseDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();
    private UserService userService;

    /**
     * 显示
     * 
     * @param model
     * @return
     */
    @RequestMapping("profile-list")
    public String list(Model model) {
        UserBase userBase = userBaseDao.findUniqueBy("username", SpringSecurityUtils.getCurrentUsername());
        model.addAttribute("model", userBase);

        return "common/user/profile-list.jsp";
    }

    /**
     * 保存
     * 
     * @param userBase
     * @param userRepoId
     * @param parameterMap
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping("profile-save")
    public String save(@ModelAttribute UserBase userBase, @RequestParam("userRepoId") String userRepoId, 
            @RequestParam Map<String, Object> parameterMap, RedirectAttributes redirectAttributes) throws Exception {
        String id = userBase.getId();
        // 再进行数据复制
        UserBase dest = null;
        if (id != null) {
            dest = userBaseDao.get(id);
            beanMapper.copy(userBase, dest);
            dest.setUserRepoId("1");
            userService.updateUser(dest);
        } else {
            dest = userBase;
            dest.setUserRepoId("1");
            userService.insertUser(dest);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/user/profile-list.do";
    }

    // ======================================================================
    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
