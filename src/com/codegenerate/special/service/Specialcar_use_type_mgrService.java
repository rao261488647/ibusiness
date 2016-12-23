package com.codegenerate.special.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.special.entity.Specialcar_use_type_mgrEntity;

/**   
 * @Title: Service
 * @Description: 专车使用车型管理页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Specialcar_use_type_mgrService extends HibernateEntityDao<Specialcar_use_type_mgrEntity> {
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
    public void remove(Specialcar_use_type_mgrEntity entity) {
        super.remove(entity);
    }
}
