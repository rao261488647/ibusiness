package com.codegenerate.financemgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.financemgr.entity.Flow_driver_financialEntity;

/**   
 * @Title: Service
 * @Description: 司机流水页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Flow_driver_financialService extends HibernateEntityDao<Flow_driver_financialEntity> {
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
    public void remove(Flow_driver_financialEntity entity) {
        super.remove(entity);
    }
}
