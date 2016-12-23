package com.codegenerate.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.codegenerate.agent.entity.Agent_driver_mgrEntity;

/**   
 * @Title: Service
 * @Description: 代驾司机列表管理页面
 * @author JiangBo
 *
 */
@Service
@Transactional
public class Agent_driver_mgrService extends HibernateEntityDao<Agent_driver_mgrEntity> {
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
    public void remove(Agent_driver_mgrEntity entity) {
        super.remove(entity);
    }
}
