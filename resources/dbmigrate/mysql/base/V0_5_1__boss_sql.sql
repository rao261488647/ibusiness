/*=================================================================*/
/* 创建全景图表 */
/*=================================================================*/
/*==============================================================*/
/* 全景图配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_PANORAMA;
CREATE TABLE IB_PANORAMA (NAME VARCHAR(64), PANORAMAADDRESS VARCHAR(128), PANORAMAURL VARCHAR(256), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
