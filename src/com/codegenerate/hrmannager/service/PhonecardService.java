package com.codegenerate.hrmannager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.hrmannager.entity.PhonecardEntity;

/**   
 * @Title: Service
 * @Description: 电话卡管理页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class PhonecardService extends HibernateEntityDao<PhonecardEntity> {
    /**
     * 插入
     * @param entity
     */
    public <T> void insert(T entity) {
        super.saveInsert(entity);
    }
    /**
     * 删除
     * @param entity
     */
    public void remove(PhonecardEntity entity) {
        super.remove(entity);
    }
}
