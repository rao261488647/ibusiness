package com.ibusiness.component.portal.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.ibusiness.component.portal.entity.ConfComponent;
/**
 * 业务模块组件Dao
 * 
 * @author JiangBo
 *
 */
@Service
@Transactional
public class ComponentDao extends HibernateEntityDao<ConfComponent> {

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
    public <T> void delete(T entity) {
        super.remove(entity);

    }
}
