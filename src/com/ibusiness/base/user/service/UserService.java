package com.ibusiness.base.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 用户业务类
 * 
 * @author JiangBo
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    private UserBaseDao userBaseDao;

    public void insertUser(UserBase userBase) {
        userBase.setScopeid(ScopeHolder.getScopeId());
        userBaseDao.save(userBase);

    }

    public void updateUser(UserBase userBase) {
        userBaseDao.save(userBase);
    }

    public void removeUser(UserBase userBase) {
        userBaseDao.remove(userBase);
    }

    // ==============================================================
    @Resource
    public void setUserBaseDao(UserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }
}
