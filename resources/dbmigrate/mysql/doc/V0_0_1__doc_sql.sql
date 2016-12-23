/*==============================================================*/
/* 文档类型表  */
/*==============================================================*/
DROP TABLE IF EXISTS IB_DOCTYPE;
CREATE TABLE IB_DOCTYPE (TYPENAME VARCHAR(32), ORDERNO INT(8), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;

/*==============================================================*/
/* doc info 文档表  */
/*==============================================================*/
DROP TABLE IF EXISTS IB_DOC_INFO;
CREATE TABLE IB_DOC_INFO(
        ID                 VARCHAR(64),
		NAME               VARCHAR(256),
		PATH               VARCHAR(256),
		DOCNAME            VARCHAR(64),
		DOCTYPE            VARCHAR(32),
		CREATE_TIME        DATETIME,
		USER_ID            VARCHAR(64),
		DESCN              VARCHAR(255),
		SCOPEID            VARCHAR(64),
        CONSTRAINT PK_IB_DOC_INFO PRIMARY KEY(ID)
) engine=innodb;

