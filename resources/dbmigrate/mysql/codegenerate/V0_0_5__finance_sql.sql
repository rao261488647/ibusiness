/* =============================  IB_CONF_COMPONENT 业务模块组件管理表 --- =============================  */
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('50001','financemgr','财务结算模块','0','sModule');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('50002','financemgr','表存储设计器','50001','Table');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('50003','financemgr','表单设计器','50001','Form');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('50004','financemgr','流程表设计器','50001','BpmTable');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('50005','financemgr','流程表单设计器','50001','BpmForm');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('50006','financemgr','流程设计器','50001','Bpm');
/* ================= 菜单管理表  ==================   */
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('financemgrmOne1','财务结算模块','1','#','URL','10','0');
delete from IB_MENU where ID='financemgrmTwo1';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('financemgrmTwo1','财务结算','2','/financial_settlement/financial_settlement-list.do','URL','1','financemgrmOne1');
delete from IB_MENU where ID='financemgrmTwo2';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('financemgrmTwo2','司机流水','2','/flow_driver_financial/flow_driver_financial-list.do','URL','2','financemgrmOne1');

INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('financemgrmOne1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('financemgrmTwo1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('financemgrmTwo2','2');
/* ======================== 业务模块组件管理表 ====================  */
/* ====== 财务结算管理 =====  */
DROP TABLE IF EXISTS IB_FINANCIAL_SETTLEMENT;
CREATE TABLE IB_FINANCIAL_SETTLEMENT (CUSTOMERNAME VARCHAR(32), TELEPHONE VARCHAR(16), SETTLEMENTTYPE VARCHAR(16), SETBUSINESSTYPE VARCHAR(16), TRANSACTIONNO VARCHAR(32), EVENTDATE DATETIME, AMOUNT VARCHAR(18), CARNUM VARCHAR(16), REMARK VARCHAR(256), MONTHDATE VARCHAR(8), DOCUMENTNUM VARCHAR(32), ISPAY VARCHAR(8), PAYMSG VARCHAR(16), ORDERFORMID VARCHAR(32), DRIVERCLASS VARCHAR(32), CARMODEL VARCHAR(32), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/* ================================================================================   */
/* ================================ 财务结算页面表    =================================   */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='financialsettlement';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('edad95a5-e494-4737-baed-dbdccb83fffb','financemgr','financialsettlement','财务结算页面','/financial_settlement/financial_settlement-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
delete from ib_conf_form_table where FORMNAME='financialsettlement';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='financialsettlement';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.CUSTOMERNAME','客户','CUSTOMERNAME','IB_FINANCIAL_SETTLEMENT',1,'10','','','1','1','1','1','','{"jsplist":[{"inputKey":"customername","inputValue":"name","inputTitle":"司机姓名"},{"inputKey":"telephone","inputValue":"cellphone","inputTitle":"司机电话"},{"inputKey":"driverclass","inputValue":"drivertype","inputTitle":"司机签约类型"},{"inputKey":"carmodel","inputValue":"carname","inputTitle":"车型名称"},{"inputKey":"carnum","inputValue":"carnum","inputTitle":"车牌号码"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"司机姓名","queryName":"name"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.CARNUM','车牌号','CARNUM','IB_FINANCIAL_SETTLEMENT',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.TELEPHONE','电话','TELEPHONE','IB_FINANCIAL_SETTLEMENT',2,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.DOCUMENTNUM','凭证号码','DOCUMENTNUM','IB_FINANCIAL_SETTLEMENT',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.SETTLEMENTTYPE','结算类型','SETTLEMENTTYPE','IB_FINANCIAL_SETTLEMENT',3,'6','','','1','1','2','1','','[{"key":"专车司机业务","value":"专车司机业务"},{"key":"车辆业务","value":"车辆业务"},{"key":"牌照业务","value":"牌照业务"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.SETBUSINESSTYPE','结算业务类型','SETBUSINESSTYPE','IB_FINANCIAL_SETTLEMENT',4,'6','','','1','1','2','1','','[{"key":"交租金","value":"交租金"},{"key":"买车","value":"买车"},{"key":"买牌","value":"买牌"},{"key":"买车月供款","value":"买车月供款"},{"key":"租牌","value":"租牌"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.TRANSACTIONNO','交易编号','TRANSACTIONNO','IB_FINANCIAL_SETTLEMENT',5,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.EVENTDATE','日期','EVENTDATE','IB_FINANCIAL_SETTLEMENT',6,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.AMOUNT','金额','AMOUNT','IB_FINANCIAL_SETTLEMENT',7,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.REMARK','备注','REMARK','IB_FINANCIAL_SETTLEMENT',9,'2','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.MONTHDATE','租金所属月份','MONTHDATE','IB_FINANCIAL_SETTLEMENT',10,'14','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.PAYMSG','支付信息','PAYMSG','IB_FINANCIAL_SETTLEMENT',12,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.ISPAY','是否支付','ISPAY','IB_FINANCIAL_SETTLEMENT',13,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.ORDERFORMID','订单ID','ORDERFORMID','IB_FINANCIAL_SETTLEMENT',14,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.DRIVERCLASS','司机类别','DRIVERCLASS','IB_FINANCIAL_SETTLEMENT',15,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','financialsettlement','IB_FINANCIAL_SETTLEMENT.CARMODEL','司机车辆型号','CARMODEL','IB_FINANCIAL_SETTLEMENT',16,'1','','','1','1','2','1','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_FINANCIAL_SETTLEMENT';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('80437ccf-0d92-4126-b7a5-93c4fc340777','financemgr','IB_FINANCIAL_SETTLEMENT','财务结算页面','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_FINANCIAL_SETTLEMENT';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','CUSTOMERNAME','客户','VARCHAR','32','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','TELEPHONE','电话','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','SETTLEMENTTYPE','结算类型','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','SETBUSINESSTYPE','结算业务类型','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','TRANSACTIONNO','交易编号','VARCHAR','32','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','EVENTDATE','日期','DATE','0','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','AMOUNT','金额','VARCHAR','18','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','CARNUM','车牌号','VARCHAR','16','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','REMARK','备注','VARCHAR','256','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','MONTHDATE','租金所属月份','VARCHAR','8','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','DOCUMENTNUM','凭证号码','VARCHAR','32','是','',11);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','ISPAY','是否支付','VARCHAR','8','是','',12);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','PAYMSG','支付信息','VARCHAR','16','是','',13);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','ORDERFORMID','订单ID','VARCHAR','32','是','',14);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','DRIVERCLASS','司机类别','VARCHAR','32','是','',15);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','CARMODEL','司机车辆型号','VARCHAR','32','是','',16);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FINANCIAL_SETTLEMENT','SCOPEID','范围','VARCHAR','64','否','',92);

/* ====== 司机流水 =====  */
DROP TABLE IF EXISTS IB_FLOW_DRIVER_FINANCIAL;
CREATE TABLE IB_FLOW_DRIVER_FINANCIAL (EVENTDATE DATETIME, CUSTOMERNAME VARCHAR(16), TELEPHONE VARCHAR(16), TRANSACTIONNO VARCHAR(32), PLATFORM VARCHAR(32), AMOUNT VARCHAR(16), CARNUM VARCHAR(16), REMARK VARCHAR(256), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/* ================================================================================   */
/* ================================ 司机流水表    =================================   */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='flowdriverfinancial';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('8c73f90f-f2d2-48fd-82a6-fade7d9fae4a','financemgr','flowdriverfinancial','司机流水','/flow_driver_financial/flow_driver_financial-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
delete from ib_conf_form_table where FORMNAME='flowdriverfinancial';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL','main');
/*======= 表单字段 ==========*/
delete from ib_conf_form_table_colums where FORMNAME='flowdriverfinancial';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.EVENTDATE','日期','EVENTDATE','IB_FLOW_DRIVER_FINANCIAL',1,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.CUSTOMERNAME','客户','CUSTOMERNAME','IB_FLOW_DRIVER_FINANCIAL',2,'10','','','1','1','2','1','','{"jsplist":[{"inputKey":"customername","inputValue":"customername","inputTitle":"客户姓名"},{"inputKey":"telephone","inputValue":"customercell","inputTitle":"客户电话"}],"className":"IB_CUSTOMER_MGR","queryTitle":"客户姓名","queryName":"customername"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.TELEPHONE','电话','TELEPHONE','IB_FLOW_DRIVER_FINANCIAL',3,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.TRANSACTIONNO','交易编号','TRANSACTIONNO','IB_FLOW_DRIVER_FINANCIAL',4,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.PLATFORM','平台','PLATFORM','IB_FLOW_DRIVER_FINANCIAL',5,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.AMOUNT','金额','AMOUNT','IB_FLOW_DRIVER_FINANCIAL',6,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.CARNUM','车牌号','CARNUM','IB_FLOW_DRIVER_FINANCIAL',7,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('financemgr','flowdriverfinancial','IB_FLOW_DRIVER_FINANCIAL.REMARK','备注','REMARK','IB_FLOW_DRIVER_FINANCIAL',8,'2','','','1','1','2','2','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_FLOW_DRIVER_FINANCIAL';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('649b7145-fa3f-4880-aeed-b00bb85902a7','financemgr','IB_FLOW_DRIVER_FINANCIAL','司机流水','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_FLOW_DRIVER_FINANCIAL';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','EVENTDATE','日期','DATE','0','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','CUSTOMERNAME','客户','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','TELEPHONE','电话','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','TRANSACTIONNO','交易编号','VARCHAR','32','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','PLATFORM','平台','VARCHAR','32','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','AMOUNT','金额','VARCHAR','16','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','CARNUM','车牌号','VARCHAR','16','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','REMARK','备注','VARCHAR','256','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_FLOW_DRIVER_FINANCIAL','SCOPEID','范围','VARCHAR','64','否','',92);
