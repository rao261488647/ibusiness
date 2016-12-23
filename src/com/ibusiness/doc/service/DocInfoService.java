package com.ibusiness.doc.service;

import org.springframework.stereotype.Service;

import com.ibusiness.common.page.HibernateEntityDao;
import com.ibusiness.doc.entity.DocInfo;

@Service
public class DocInfoService extends HibernateEntityDao<DocInfo> {
}
