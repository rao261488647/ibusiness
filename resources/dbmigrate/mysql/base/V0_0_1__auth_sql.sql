/*-------------------------------------------------------------------------------*/
/*--  scope info 应用管理表   */
/*-------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS IB_SCOPE_INFO;
CREATE TABLE IB_SCOPE_INFO(
        ID         VARCHAR(64),
        NAME  VARCHAR(64),
		CODE   VARCHAR(64),
		REF      VARCHAR(64),
		SHARED INTEGER,
		USER_REPO_REF VARCHAR(64),
		TYPE INTEGER,
        CONSTRAINT PK_SCOPE_INFO PRIMARY KEY(ID)
) engine=innodb;

/*-------------------------------------------------------------------------------*/
/*--  role def 角色模板管理表 */
/*-------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS IB_AUTH_ROLE_DEF;
CREATE TABLE IF NOT EXISTS IB_AUTH_ROLE_DEF(
        ID              VARCHAR(64),
        NAME       VARCHAR(64),
        DESCN      VARCHAR(256),
	    SCOPE_ID VARCHAR(64),
        CONSTRAINT PK_AUTH_ROLE_DEF PRIMARY KEY(ID)
) engine=innodb;

/*-------------------------------------------------------------------------------*/
/*--  permission type 授权类型表 */
/*-------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS IB_AUTH_PERM_TYPE;
CREATE TABLE IF NOT EXISTS IB_AUTH_PERM_TYPE(
        ID        VARCHAR(64),
        NAME VARCHAR(64),
	    TYPE INTEGER,
	    PRIORITY INTEGER,
        DESCN VARCHAR(256),
	    SCOPE_ID VARCHAR(64),
        CONSTRAINT PK_AUTH_PERM_TYPE PRIMARY KEY(ID)
) engine=innodb;

/*-------------------------------------------------------------------------------*/
/*--  permission 授权管理表  */
/*-------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS IB_AUTH_PERM;
CREATE TABLE IF NOT EXISTS IB_AUTH_PERM(
        ID VARCHAR(64),
		CODE VARCHAR(200),
		NAME VARCHAR(200),
		PERM_TYPE_ID VARCHAR(64) NOT NULL,
		PRIORITY INTEGER,
		SCOPE_ID VARCHAR(64),
        CONSTRAINT PK_AUTH_PERM PRIMARY KEY(ID)
	/* ,CONSTRAINT FK_AUTH_PERM_TYPE FOREIGN KEY(PERM_TYPE_ID) REFERENCES IB_AUTH_PERM_TYPE(ID) */
) engine=innodb;

/*-------------------------------------------------------------------------------*/
/*--  permission role def  授权和角色模板关联表   */
/*-------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS IB_AUTH_PERM_ROLE_DEF;
CREATE TABLE IB_AUTH_PERM_ROLE_DEF(
        PERM_ID VARCHAR(64) NOT NULL,
        ROLE_DEF_ID VARCHAR(64) NOT NULL,
        CONSTRAINT PK_AUTH_PERM_ROLE_DEF PRIMARY KEY(PERM_ID,ROLE_DEF_ID)
      /* , CONSTRAINT FK_AUTH_PERM_ROLE_DEF_PERM FOREIGN KEY(PERM_ID) REFERENCES IB_AUTH_PERM(ID),
        CONSTRAINT FK_AUTH_PERM_ROLE_DEF_ROLE_DEF FOREIGN KEY(ROLE_DEF_ID) REFERENCES IB_AUTH_ROLE_DEF(ID)
        */
) engine=innodb;

/*-------------------------------------------------------------------------------*/
/*--  access 资源访问权限表  */
/*-------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS IB_AUTH_ACCESS;
CREATE TABLE IB_AUTH_ACCESS(
	ID             VARCHAR(64),
	TYPE         VARCHAR(64),
	VALUE       VARCHAR(256),
	PERM_ID   VARCHAR(64),
	PRIORITY  INTEGER,
	DESCN       VARCHAR(256),
	SCOPE_ID  VARCHAR(64),
	MENU_ID  VARCHAR(64),
	CONSTRAINT PK_AUTH_ACCESS PRIMARY KEY(ID)
/*	,CONSTRAINT FK_AUTH_ACCESS_PERM FOREIGN KEY(PERM_ID) REFERENCES IB_AUTH_PERM(ID) */
) engine=innodb;
