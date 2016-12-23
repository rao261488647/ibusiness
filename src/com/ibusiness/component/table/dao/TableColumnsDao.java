package com.ibusiness.component.table.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.dao.BaseEntityDao;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * 表字段结构管理DAO
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class TableColumnsDao extends BaseEntityDao<ConfTableColumns> {
    
}
