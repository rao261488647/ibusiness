package com.codegenerate.special.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.special.entity.Special_record_mgrEntity;

/**   
 * @Title: Service
 * @Description: 专车司机记录管理页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Special_record_mgrService extends HibernateEntityDao<Special_record_mgrEntity> {
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
    public void remove(Special_record_mgrEntity entity) {
        super.remove(entity);
    }
}
