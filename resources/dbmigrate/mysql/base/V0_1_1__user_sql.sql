
/*-------------------------------------------------------------------------------*/
/*--  user repo 用户库列表  */
/*-------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS IB_USER_REPO;
CREATE TABLE IB_USER_REPO(
        ID         VARCHAR(64),
		CODE   VARCHAR(64),
        NAME  VARCHAR(64),
		REF      VARCHAR(64),
		SCOPE_ID VARCHAR(64),
        CONSTRAINT PK_U_USER_REPO PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* user base 用户表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_USER_BASE;
CREATE TABLE IB_USER_BASE(
        ID               VARCHAR(64),
        USERNAME         VARCHAR(64),
		DISPLAY_NAME     VARCHAR(64),
        PASSWORD         VARCHAR(64),
        DEVICE           VARCHAR(32),
        IMEI             VARCHAR(64),
        STATUS           INTEGER,
		REF              INTEGER,
		USER_REPO_ID     VARCHAR(64),
		SCOPE_ID         VARCHAR(64),
		email            VARCHAR(128),
		mobile           VARCHAR(64),
		JOB_INFO_ID      BIGINT,
		ROLE_DEF_ID      VARCHAR(64),
		COMPANYID        VARCHAR(64),
		CSS              VARCHAR(64),
		signMsg          VARCHAR(256),
		photoFile        VARCHAR(256),
        CONSTRAINT PK_USER_BASE PRIMARY KEY(ID)
       /* ,CONSTRAINT FK_USER_BASE_REPO FOREIGN KEY(USER_REPO_ID) REFERENCES IB_USER_REPO(ID) */
) engine=innodb;
