package com.codegenerate.special.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.special.entity.Special_driver_infoEntity;

/**   
 * @Title: Service
 * @Description: 专车司机信息页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Special_driver_infoService extends HibernateEntityDao<Special_driver_infoEntity> {
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
    public void remove(Special_driver_infoEntity entity) {
        super.remove(entity);
    }
}
