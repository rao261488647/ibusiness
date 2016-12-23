package com.codegenerate.carmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.carmgr.entity.Car_type_infoEntity;

/**   
 * @Title: Service
 * @Description: 车型管理页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Car_type_infoService extends HibernateEntityDao<Car_type_infoEntity> {
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
    public void remove(Car_type_infoEntity entity) {
        super.remove(entity);
    }
}
