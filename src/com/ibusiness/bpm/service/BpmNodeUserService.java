package com.ibusiness.bpm.service;

import org.springframework.stereotype.Service;

import com.ibusiness.bpm.entity.BpmNodeUser;
import com.ibusiness.common.page.HibernateEntityDao;

/**
 * 流程节点人员(参与者)配置表DAO
 * 
 * @author JiangBo
 *
 */
@Service
public class BpmNodeUserService extends HibernateEntityDao<BpmNodeUser> {

}
