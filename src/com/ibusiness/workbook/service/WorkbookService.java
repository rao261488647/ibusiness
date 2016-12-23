package com.ibusiness.workbook.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import com.ibusiness.workbook.entity.WorkbookEntity;

/**   
 * @Title: Service
 * @Description: 工作日志
 * @author JiangBo
 *
 */
@Service
@Transactional
public class WorkbookService extends HibernateEntityDao<WorkbookEntity> {
}
