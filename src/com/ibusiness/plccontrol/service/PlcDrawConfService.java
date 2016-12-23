package com.ibusiness.plccontrol.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.ibusiness.plccontrol.entity.PlcDrawConfEntity;

/**   
 * PLC绘图页面 设备对象配置管理 service
 * @author JiangBo
 *
 */
@Service
@Transactional
public class PlcDrawConfService extends HibernateEntityDao<PlcDrawConfEntity> {
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
    public void remove(PlcDrawConfEntity entity) {
        super.remove(entity);
    }
}
