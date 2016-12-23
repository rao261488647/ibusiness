package com.ibusiness.doc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.ibusiness.doc.entity.DoctypeEntity;

/**
 * 文件类型页面
 * 
 * @author JiangBo
 * 
 */
@Service
@Transactional
public class DoctypeService extends HibernateEntityDao<DoctypeEntity> {
    /**
     * 插入
     * 
     * @param entity
     */
    public <T> void insert(T entity) {
        super.saveInsert(entity);
    }

    /**
     * 删除
     * 
     * @param entity
     */
    public void remove(DoctypeEntity entity) {
        super.remove(entity);
    }
}
