package com.jsp.common.controller;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibusiness.doc.store.StoreConnector;

/**
 * JSP共用controller
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("jsp_common")
public class JspCommonController {

	private SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private StoreConnector storeConnector;

    // ~ ======================================================================
    @Resource
	public void setStoreConnector(StoreConnector storeConnector) {
		this.storeConnector = storeConnector;
	}
}
