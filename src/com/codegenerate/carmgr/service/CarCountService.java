package com.codegenerate.carmgr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codegenerate.carmgr.entity.CarCountEntity;
import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.ibusiness.common.page.HibernateEntityDao;

/**   
 * @Title: Service
 * @Description: 车辆库存统计
 * @author JiangBo
 *
 */
@Service
@Transactional
public class CarCountService extends HibernateEntityDao<CarCountEntity> {
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
    public void remove(Car_mgrEntity entity) {
        super.remove(entity);
    }
}
