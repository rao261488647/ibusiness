package com.codegenerate.customermgr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codegenerate.customermgr.entity.Car_rentalEntity;
import com.ibusiness.common.page.HibernateEntityDao;

/**   
 * 租车预订信息页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Car_rentalService extends HibernateEntityDao<Car_rentalEntity> {
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
    public void remove(Car_rentalEntity entity) {
        super.remove(entity);
    }
}
