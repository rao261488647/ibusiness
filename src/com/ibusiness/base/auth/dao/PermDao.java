package com.ibusiness.base.auth.dao;

import org.springframework.stereotype.Service;

import com.ibusiness.base.auth.entity.Perm;
import com.ibusiness.common.page.HibernateEntityDao;
/**
 * 标签级权限管理
 * @author JiangBo
 *
 */
@Service
public class PermDao extends HibernateEntityDao<Perm> {
}
