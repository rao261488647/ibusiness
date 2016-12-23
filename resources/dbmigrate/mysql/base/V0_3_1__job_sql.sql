
/*==============================================================*/
/* 公司 company   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_COMPANY;
CREATE TABLE IB_COMPANY(
        ID                 VARCHAR(64),
        NAME          VARCHAR(256),
	    DESCN        VARCHAR(256),
        STATUS       INTEGER,
	    REF             VARCHAR(256),
	    SCOPE_ID   VARCHAR(64),
        CONSTRAINT PK_ORG_COMPANY PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* 部门 department  */
/*==============================================================*/
DROP TABLE IF EXISTS IB_DEPARTMENT;
CREATE TABLE IB_DEPARTMENT(
        ID                   VARCHAR(64),
        COMPANYID VARCHAR(64),
        NAME            VARCHAR(200),
	    DESCN           VARCHAR(200),
        STATUS          INTEGER,
	    REF               VARCHAR(200),
	    SCOPE_ID     VARCHAR(50),
        CONSTRAINT PK_ORG_DEPARTMENT PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* 小组 group  */
/*==============================================================*/
DROP TABLE IF EXISTS IB_GROUP;
CREATE TABLE IB_GROUP(
        ID                      VARCHAR(64),
        COMPANYID    VARCHAR(64),
        DEPTID            VARCHAR(64),
        NAME              VARCHAR(256),
	    DESCN             VARCHAR(256),
        STATUS            INTEGER,
	    REF                  VARCHAR(256),
	    SCOPE_ID       VARCHAR(64),
        CONSTRAINT PK_ORG_GROUP PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* job type 职务类型表  */
/*==============================================================*/
DROP TABLE IF EXISTS IB_JOB_TYPE;
CREATE TABLE IB_JOB_TYPE(
        ID                 VARCHAR(64),
	    NAME          VARCHAR(64),
	    PARENT_ID VARCHAR(64),
	    SCOPE_ID    VARCHAR(64),
        CONSTRAINT PK_IB_JOB_TYPE PRIMARY KEY(ID)
        /*,CONSTRAINT FK_IB_JOB_TYPE_PARENT FOREIGN KEY(PARENT_ID) REFERENCES IB_JOB_TYPE(ID) */
) engine=innodb;

/*==============================================================*/
/* job title 职位名称管理表 */
/*==============================================================*/
DROP TABLE IF EXISTS IB_JOB_TITLE;
CREATE TABLE IB_JOB_TITLE(
        ID                VARCHAR(64),
	    NAME         VARCHAR(64),
	    ORDERS     INTEGER,
	    SCOPE_ID   VARCHAR(64),
        CONSTRAINT PK_IB_JOB_TITLE PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* job info 职务管理表 */
/*==============================================================*/
DROP TABLE IF EXISTS IB_JOB_INFO;
CREATE TABLE IB_JOB_INFO(
        ID               VARCHAR(64),
	    NAME        VARCHAR(64),
	    TYPE_ID    VARCHAR(64),
	    TITLE_ID   VARCHAR(64), 
	    SCOPE_ID  VARCHAR(64),
        CONSTRAINT PK_IB_JOB_INFO PRIMARY KEY(ID)
        /*,CONSTRAINT FK_IB_JOB_INFO_TYPE FOREIGN KEY(TYPE_ID) REFERENCES IB_JOB_TYPE(ID),
        CONSTRAINT FK_IB_JOB_INFO_TITLE FOREIGN KEY(TITLE_ID) REFERENCES IB_JOB_TITLE(ID) */
) engine=innodb;

