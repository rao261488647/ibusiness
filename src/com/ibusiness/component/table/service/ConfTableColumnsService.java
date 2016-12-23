package com.ibusiness.component.table.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.ibusiness.component.table.entity.ConfTableColumns;
/**
 * 表字段存储表service
 * 
 * @author JiangBo
 *
 */
@Service
@Transactional
public class ConfTableColumnsService extends HibernateEntityDao<ConfTableColumns> {
}
