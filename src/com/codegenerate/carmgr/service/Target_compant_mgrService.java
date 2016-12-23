package com.codegenerate.carmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.carmgr.entity.Target_compant_mgrEntity;

/**   
 * @Title: Service
 * @Description: 指标公司管理页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Target_compant_mgrService extends HibernateEntityDao<Target_compant_mgrEntity> {
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
    public void remove(Target_compant_mgrEntity entity) {
        super.remove(entity);
    }
}
