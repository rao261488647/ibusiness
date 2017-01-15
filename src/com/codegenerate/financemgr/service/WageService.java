package com.codegenerate.financemgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.financemgr.entity.WageEntity;

/**   
 * @Title: Service
 * @Description: 工资方案页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class WageService extends HibernateEntityDao<WageEntity> {
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
    public void remove(WageEntity entity) {
        super.remove(entity);
    }
}
