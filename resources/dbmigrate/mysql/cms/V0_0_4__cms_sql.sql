
/*==============================================================*/
/* cms 公告栏目表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CMS_CATALOG;
CREATE TABLE IB_CMS_CATALOG(
        ID                    VARCHAR(64),
		NAME                  VARCHAR(64),
		CODE                  VARCHAR(256),
		LOGO                  VARCHAR(256),
		CATALOGTYPE           INTEGER,
		TEMPLATE_INDEX        VARCHAR(256),
		TEMPLATE_LIST         VARCHAR(256),
		TEMPLATE_DETAIL       VARCHAR(256),
		DESCRIPTION           VARCHAR(256),
		PARENT_ID             VARCHAR(64),
		SCOPEID               VARCHAR(64),
        CONSTRAINT PK_CMS_CATALOG PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* cms 公告文章   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CMS_ARTICLE;
CREATE TABLE IB_CMS_ARTICLE(
        ID                  VARCHAR(64),
		TITLE               VARCHAR(256),
		SHORT_TITLE         VARCHAR(256),
		SUB_TITLE           VARCHAR(256),
		CONTENT             VARCHAR(2000),
		SUMMARY             VARCHAR(256),
		LOGO                VARCHAR(256),
		TAGS                VARCHAR(256),
		ALLOW_COMMENT       INTEGER,
		STATUS              INTEGER,
		PUBLISH_TIME        TIMESTAMP,
		CLOSE_TIME          TIMESTAMP,
		ARTICLETYPE         INTEGER,
		TOP                 INTEGER,
		WEIGHT              INTEGER,
		CREATE_TIME         TIMESTAMP,
		VIEW_COUNT          INTEGER,
		RECOMMEND_ID        VARCHAR(64),
		RECOMMEND_STATUS    INTEGER,
		USER_ID             VARCHAR(64),
		CATALOG_ID          VARCHAR(64),
		SCOPEID             VARCHAR(64),
        CONSTRAINT PK_CMS_ARTICLE PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* cms 公告文章评论表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CMS_COMMENT;
CREATE TABLE IB_CMS_COMMENT(
        ID                  VARCHAR(64),
		TITLE               VARCHAR(256),
		CONTENT             VARCHAR(2000),
		STATUS              INTEGER,
		CREATE_TIME         TIMESTAMP,
		USER_ID             VARCHAR(64),
		ARTICLE_ID          VARCHAR(64),
		SCOPEID             VARCHAR(64),
        CONSTRAINT PK_CMS_COMMENT PRIMARY KEY(ID)
) engine=innodb;
/*==============================================================*/
/* 图片管理作品展示表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_INDEXIMGBYWORKS;
CREATE TABLE IB_INDEXIMGBYWORKS (
        IMGURL VARCHAR(256),
        IMGURL2 VARCHAR(256),
        IMGURL3 VARCHAR(256),
        IMGURL4 VARCHAR(256),
        IMGURL5 VARCHAR(256),
        IMGINFO VARCHAR(128),
        SHOWFLAG VARCHAR(8),
        ID VARCHAR(64),
        SCOPEID VARCHAR(64),
        PRIMARY KEY (ID)) ENGINE=INNODB;
