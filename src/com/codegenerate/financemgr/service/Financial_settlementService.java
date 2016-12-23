package com.codegenerate.financemgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.financemgr.entity.Financial_settlementEntity;

/**   
 * @Title: Service
 * @Description: 财务结算页面页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Financial_settlementService extends HibernateEntityDao<Financial_settlementEntity> {
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
    public void remove(Financial_settlementEntity entity) {
        super.remove(entity);
    }
}
