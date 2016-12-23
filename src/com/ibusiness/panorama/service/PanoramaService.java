package com.ibusiness.panorama.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.ibusiness.panorama.entity.PanoramaEntity;

/**   
 * @Title: Service
 * @Description: 全景图管理表页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class PanoramaService extends HibernateEntityDao<PanoramaEntity> {
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
    public void remove(PanoramaEntity entity) {
        super.remove(entity);
    }
}
