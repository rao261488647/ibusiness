package com.ibusiness.security.api.scope;
/**
 * 多租户应用DTO
 * 
 * @author JiangBo
 *
 */
public class ScopeDTO {
    /** 普通的管理后台的类型. */
    public static final int TYPE_NORMAL = 0;

    /** cms前端显示静态页面的类型. */
    public static final int TYPE_CMS = 1;

    /** 数据库里的逻辑id. */
    private String id;

    /** 用户可以理解的名称 */
    private String name;

//    /** 可以放到url里的代码. */
//    private String code;

    /** 与外部系统协商的唯一标识. */
    private String ref;

    /** 这个应用的资源是否会被其他应用共享. */
    private boolean shared;

//    /** 我们可以认为每个scope都是一个租户，每个租户当然需要关联一种登录的方式. */
//    private String userRepoRef;

    /** 类型. */
    private int type;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the shared
	 */
	public boolean isShared() {
		return shared;
	}
	/**
	 * @param shared the shared to set
	 */
	public void setShared(boolean shared) {
		this.shared = shared;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
