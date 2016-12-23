package com.ibusiness.base.auth.service;

import org.springframework.stereotype.Service;

import com.ibusiness.base.auth.entity.ScopeInfo;
import com.ibusiness.common.page.HibernateEntityDao;
/**
 * SAAS多租户范围管理service
 * @author JiangBo
 *
 */
@Service
public class ScopeInfoService extends HibernateEntityDao<ScopeInfo> {
}
