package com.webservice.rs;

import java.util.HashMap;
import java.util.Map;


/**
 * 公用方法
 * @author JiangBo
 *
 */
public class MemoryCommon {
	/**
     * 单例模式
     */
	private static MemoryCommon instance = new MemoryCommon();
	public static MemoryCommon getInstance() {
        return instance;
    }
    private MemoryCommon() {}
    // 验证码列表
    public Map<String, Object> verifyCodeList = new HashMap<String, Object>();
    
}
