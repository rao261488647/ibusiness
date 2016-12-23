package com.ibusiness.base.cssui.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * UI CSS样式设置
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("uicss")
public class UicssController {
    private UserBaseDao userBaseDao;
    /**
     * 设置 UI CSS样式
     */
    @RequestMapping("uicss-save")
    public String save(@RequestParam("userCSS") String userCSS, HttpSession session) {

        String userId = SpringSecurityUtils.getCurrentUserId();
        UserBase userBase = userBaseDao.get(userId);
        userBase.setCss(userCSS);
        userBaseDao.save(userBase);
        // 清空设置内存中对象
        CommonBusiness.getInstance().setUserBaseMap(new HashMap<String, UserBase>());
        // 设置request
        session.setAttribute("userCSS",userBase.getCss());
        return "redirect:/portal/portal.do";
    }
    /**
     * @return the userBaseDao
     */
    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }
}
