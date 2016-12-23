package com.ibusiness.base.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.util.SimplePasswordEncoder;
import com.ibusiness.security.util.SpringSecurityUtils;
/**
 * 修改密码
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("/user")
public class ChangePasswordController {
    private UserBaseDao userBaseDao;
    private MessageHelper messageHelper;
    private SimplePasswordEncoder simplePasswordEncoder;

    @RequestMapping("change-password-input")
    public String input() {
        return "common/user/change-password-input.jsp";
    }

    /**
     * 保存
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("change-password-save")
    public String save(@RequestParam("oldPassword")
    String oldPassword, @RequestParam("newPassword")
    String newPassword, @RequestParam("confirmPassword")
    String confirmPassword, RedirectAttributes redirectAttributes) {
        if (!newPassword.equals(confirmPassword)) {
            messageHelper.addFlashMessage(redirectAttributes, "user.user.input.passwordnotequals", "两次输入密码不符");

            return "redirect:/user/change-password-input.do";
        }

        UserBase userBase = userBaseDao.findUniqueBy("username", SpringSecurityUtils.getCurrentUsername());

        if (!isPasswordValid(oldPassword, userBase.getPassword())) {
            messageHelper.addFlashMessage(redirectAttributes, "user.user.input.passwordnotcorrect", "密码错误");

            return "redirect:/user/change-password-input.do";
        }

        userBase.setPassword(encodePassword(newPassword));
        userBaseDao.save(userBase);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/user/change-password-input.do";
    }

    /**
     * 密码有效性判断
     * 
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        if (simplePasswordEncoder != null) {
            return simplePasswordEncoder.matches(rawPassword, encodedPassword);
        } else {
            return rawPassword.equals(encodedPassword);
        }
    }

    public String encodePassword(String password) {
        if (simplePasswordEncoder != null) {
            return simplePasswordEncoder.encode(password);
        } else {
            return password;
        }
    }

    // ~ ======================================================================
    @Resource
    public void setUserBaseManager(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setSimplePasswordEncoder(SimplePasswordEncoder simplePasswordEncoder) {
        this.simplePasswordEncoder = simplePasswordEncoder;
    }
}
