package com.ibusiness.cms.service;

import org.springframework.stereotype.Service;

import com.ibusiness.cms.entity.CmsComment;
import com.ibusiness.common.page.HibernateEntityDao;
/**
 * 公告文章评论表Service
 * 
 * @author JiangBo
 *
 */
@Service
public class CmsCommentService extends HibernateEntityDao<CmsComment> {
}
