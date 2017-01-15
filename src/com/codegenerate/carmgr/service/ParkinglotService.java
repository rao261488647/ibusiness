package com.codegenerate.carmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.carmgr.entity.ParkinglotEntity;

/**   
 * @Title: Service
 * @Description: 停车场出入库管理页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class ParkinglotService extends HibernateEntityDao<ParkinglotEntity> {
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
    public void remove(ParkinglotEntity entity) {
        super.remove(entity);
    }
}
