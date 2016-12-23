/*=================================================================*/
/* 创建map表 */
/*=================================================================*/
/*==============================================================*/
/* 地图基础配置表   */
/*==============================================================*/
CREATE TABLE IB_MAP_CONF (
    mapIp          VARCHAR(32),
    mapPort        VARCHAR(32),
    mapCity        VARCHAR(32),
    mapShowOnline  VARCHAR(32),
    mapGrid        VARCHAR(32),
    mapCenterLongitude       VARCHAR(32),
    mapCenterLatitude        VARCHAR(32),
    CONSTRAINT PK_IB_MAP_CONF PRIMARY KEY(mapIp)
) ENGINE=INNODB;
/*==============================================================*/
/* 客户表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CUSTOMER_INFO;
CREATE TABLE IB_CUSTOMER_INFO (
         ID  VARCHAR(64),
         CUSTOMERNO          VARCHAR(64),
         CUSTOMERSTATE       VARCHAR(16),
         CUSTOMERNAME      VARCHAR(64),
         CUSTOMERTYPE       VARCHAR(32),
         ADDRESS                  VARCHAR(512),
         PHONE                      VARCHAR(32),
         TELEPHONE               VARCHAR(32),
        CONSTRAINT PK_IB_CUSTOMER_INFO PRIMARY KEY(ID)
) ENGINE=INNODB;
/*==============================================================*/
/* 图标名称表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_MAP_ICON;
CREATE TABLE IB_MAP_ICON (
         ID  VARCHAR(64),
         ICONNAME VARCHAR(64),
        CONSTRAINT PK_IB_MAP_ICON PRIMARY KEY(ID)
) ENGINE=INNODB;
/*=============================================================================================*/
/* Table: 图标类型表 */
/*=============================================================================================*/
DROP TABLE IF EXISTS IB_MAP_ICON_TYPE;
CREATE TABLE IB_MAP_ICON_TYPE (
    ID                                VARCHAR(64),
    ICONID                       VARCHAR(64)    not null,
    ICONNAME                VARCHAR(64),
    ICONTYPE                  VARCHAR(24),
    ICONSTATUSID          INTEGER,
    ICONSTATUSNAME   VARCHAR(64),
    ICONPATH                  VARCHAR(128),
    CONSTRAINT PK_IB_MAP_ICON_TYPE PRIMARY KEY(ID)
) ENGINE=INNODB;
/*=============================================================================================*/
/* Table: (位置)标注表 */
/*=============================================================================================*/
DROP TABLE IF EXISTS IB_MAP_SITE;
CREATE TABLE IB_MAP_SITE (
	    ID                                VARCHAR(64),
		SITENO                       VARCHAR(64), 
		SITENAME                  VARCHAR(64), 
		latitude                        VARCHAR(64), 
		longitude                     VARCHAR(64), 
		iconid                          VARCHAR(64), 
		icontypeid                   VARCHAR(64), 
		isdisplay                      INTEGER DEFAULT 0, 
		isused                          INTEGER DEFAULT 0, 
		lineid                           VARCHAR(64), 
		remark                        VARCHAR(100), 
    CONSTRAINT PK_IB_MAP_SITE PRIMARY KEY(ID)
) ENGINE=INNODB;
