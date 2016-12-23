/*==============================================================*/
/* 业务模块组件管理表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CONF_COMPONENT;
CREATE TABLE IB_CONF_COMPONENT (
         ID  VARCHAR(64),
		 PACKAGENAME VARCHAR(128),
		 MODULENAME VARCHAR(128),
		 PARENTID VARCHAR(64),
		 TYPEID VARCHAR(8),
		 SUBNAME VARCHAR(32),
        CONSTRAINT PK_IB_CONF_COMPONENT PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* config table info 业务表结构管理表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CONF_TABLE;
CREATE TABLE IB_CONF_TABLE(
		    ID  VARCHAR(64),
		    PACKAGENAME VARCHAR(128),
		    TABLENAME VARCHAR(128),
		    TABLENAMECOMMENT VARCHAR(128),
		    TABLETYPE  VARCHAR(8),
		    PARENTTABLEID VARCHAR(64),
		    ISBPMTABLE INTEGER,
        CONSTRAINT PK_IB_CONF_TABLE PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* config table columns info 业务表字段结构管理表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CONF_TABLE_COLUMNS;
CREATE TABLE IB_CONF_TABLE_COLUMNS (
  tableName VARCHAR(128),
  columnValue VARCHAR(128),
  columnName VARCHAR(128),
  columnType VARCHAR(32),
  columnSize VARCHAR(8),
  isNull VARCHAR(8),
  defaultValue VARCHAR(32),
  columnNo INTEGER,
        CONSTRAINT PK_IB_CONF_TABLE_COLUMNS PRIMARY KEY(tableName,COLUMNVALUE)
) ENGINE=INNODB;

/*==============================================================*/
/* IB_CONF_FORM–表单管理表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CONF_FORM;
CREATE TABLE IB_CONF_FORM (
		   ID                             VARCHAR(64),
		   PACKAGENAME      VARCHAR(128),
		   FORMNAME            VARCHAR(128),
		   FORMTITLE            VARCHAR(128),
		   FORMURL               VARCHAR(256),
		   ISEDIT                     INTEGER,
		   ISADD                      INTEGER,
		   ISDELETE                INTEGER,
		   ISQUERY                   INTEGER,
		   ISEXCELEXPORT     INTEGER,
		   ISIMPORTEXPORT   INTEGER,
		   ISBPMFORM            INTEGER,
        CONSTRAINT PK_IB_CONF_FORM PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* IB_CONF_FORM_TABLE-表单对应数据表管理表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CONF_FORM_TABLE;
CREATE TABLE IB_CONF_FORM_TABLE (
  PACKAGENAME VARCHAR(128),
  FORMNAME VARCHAR(128),
  TABLENAME VARCHAR(128),
  TABLETYPE  VARCHAR(8),
        CONSTRAINT PK_IB_CONF_FORM_TABLE PRIMARY KEY(FORMNAME, TABLENAME)
) ENGINE=INNODB;

/*==============================================================*/
/* IB_CONF_FORM_TABLE_COLUMS-表单对应字段管理表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CONF_FORM_TABLE_COLUMS;
CREATE TABLE IB_CONF_FORM_TABLE_COLUMS (
		  PACKAGENAME    VARCHAR(128),
		  FORMNAME           VARCHAR(128),
		  FORMCOLUMN      VARCHAR(128),
		  FORMCOLUMNTITLE VARCHAR(128),
		  TABLECOLUMN   VARCHAR(128),
		  TABLENAME        VARCHAR(128),
		  COLUMNNO          INTEGER,
		  FCTYPE                 VARCHAR(64),
		  FCWIDTH             VARCHAR(8),
		  FCHEIGHT            VARCHAR(8),
		  FCDISPLAY           VARCHAR(8),
		  FCEDIT                 VARCHAR(8),
		  FCQUERY              VARCHAR(8),
		  FCMUST                VARCHAR(8),
		  FCDEFAULT          VARCHAR(128),
		  CONFSELECTINFO     VARCHAR(1024),
        CONSTRAINT PK_CONF_FORM_TABLE_COLUMS PRIMARY KEY(PACKAGENAME, FORMNAME, FORMCOLUMN)
) ENGINE=INNODB;

