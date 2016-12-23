package com.ibusiness.cms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.cms.entity.IndeximgbyworksEntity;
import com.ibusiness.common.page.HibernateEntityDao;

/**   
 * @Title: Service
 * @Description: 作品展示表页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class IndeximgbyworksService extends HibernateEntityDao<IndeximgbyworksEntity> {
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
    public void remove(IndeximgbyworksEntity entity) {
        super.remove(entity);
    }
}
