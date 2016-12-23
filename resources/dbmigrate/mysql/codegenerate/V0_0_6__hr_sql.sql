/* ==========================================================================================  */
/* =============================  IB_CONF_COMPONENT 业务模块组件管理表    =============================  */
/* ==========================================================================================  */
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('hrmanage0000','hrmannager','行政管理','0','sModule');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('hrmanage0001','hrmannager','表存储设计器','hrmanage0000','Table');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('hrmanage0002','hrmannager','表单设计器','hrmanage0000','Form');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('hrmanage0003','hrmannager','流程表设计器','hrmanage0000','BpmTable');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('hrmanage0004','hrmannager','流程表单设计器','hrmanage0000','BpmForm');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('hrmanage0005','hrmannager','流程设计器','hrmanage0000','Bpm');
/* ================= 菜单管理表  ==================   */
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('hrmanageOne','行政管理','1','#','URL','8','0');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo1','请假管理','2','/leavemng/leavemng-list.do','URL','1','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo2','出险管理 ','2','/insurance/insurance-list.do','URL','2','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo3','宿舍管理 ','2','/dormitory/dormitory-list.do','URL','3','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo4','电话卡管理 ','2','/phonecard/phonecard-list.do','URL','4','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo5','违章管理 ','2','/illegal/illegal-list.do','URL','5','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo6','以租代购 ','2','/carlease/carlease-list.do','URL','6','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo7','纯租 ','2','/carrental/carrental-list.do','URL','7','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo8','电卡管理 ','2','/electricitycard/electricitycard-list.do','URL','8','hrmanageOne');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo9','工资方案 ','2','#','URL','9','hrmanageOne');


/*===============  菜单和角色模板关联表  =============================*/
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageOne','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo2','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo3','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo4','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo5','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo6','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo7','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo8','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('hrmanageTwo9','2');

/* ================================================================================   */
/* ========================= 请假管理 =================================  */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_LEAVEMNG;
CREATE TABLE IB_LEAVEMNG (DRIVERNAME VARCHAR(8), DRIVERPHONE VARCHAR(16), DAYSS VARCHAR(8), BEGINDATE DATETIME, ENDDATE DATETIME, CARFLAG VARCHAR(8), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='LEAVEMNG';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('e7403649-2a1a-44fa-9547-6e4430fe0701','hrmannager','LEAVEMNG','请假管理','/leavemng/leavemng-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='LEAVEMNG';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','LEAVEMNG','IB_LEAVEMNG','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='LEAVEMNG';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','LEAVEMNG','IB_LEAVEMNG.DRIVERNAME','司机姓名','DRIVERNAME','IB_LEAVEMNG',1,'10','','','1','1','1','1','','{"jsplist":[{"inputKey":"drivername","inputValue":"name","inputTitle":"司机姓名"},{"inputKey":"driverphone","inputValue":"cellphone","inputTitle":"司机电话"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"司机姓名","queryName":"name"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','LEAVEMNG','IB_LEAVEMNG.DRIVERPHONE','联系方式','DRIVERPHONE','IB_LEAVEMNG',2,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','LEAVEMNG','IB_LEAVEMNG.DAYSS','请假天数','DAYSS','IB_LEAVEMNG',3,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','LEAVEMNG','IB_LEAVEMNG.BEGINDATE','开始日期','BEGINDATE','IB_LEAVEMNG',4,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','LEAVEMNG','IB_LEAVEMNG.ENDDATE','结束日期','ENDDATE','IB_LEAVEMNG',5,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','LEAVEMNG','IB_LEAVEMNG.CARFLAG','车辆是否回收','CARFLAG','IB_LEAVEMNG',6,'6','','','1','1','2','1','','[{"key":"是","value":"是"},{"key":"否","value":"否"}]');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_LEAVEMNG';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('45bc7195-3ea2-4420-8152-fe059516cacf','hrmannager','IB_LEAVEMNG','请假管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_LEAVEMNG';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','DRIVERNAME','司机姓名','VARCHAR','8','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','DRIVERPHONE','联系方式','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','DAYSS','请假天数','VARCHAR','8','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','BEGINDATE','开始日期','DATE','0','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','ENDDATE','结束日期','DATE','0','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','CARFLAG','车辆是否回收','VARCHAR','8','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_LEAVEMNG','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ========================= 出险管理 =================================  */
/* ================================================================================   */
/* ====== 出险管理 =====  */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_INSURANCE;
CREATE TABLE IB_INSURANCE (DRIVERNAME VARCHAR(8), DRIVERPHONE VARCHAR(16), CARNAME VARCHAR(32), CARNUM VARCHAR(16), EVENTDATE DATETIME, RESPONSIBILITY VARCHAR(4), MAINTTIME VARCHAR(8), CARFLAG VARCHAR(8), NEWCARNAME VARCHAR(32), NEWCARNUM VARCHAR(16), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='INSURANCE';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('b59f4fec-1bce-4e78-bca2-45d31e82dee3','hrmannager','INSURANCE','出险管理','/insurance/insurance-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='INSURANCE';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','INSURANCE','IB_INSURANCE','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='INSURANCE';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.DRIVERNAME','司机姓名','DRIVERNAME','IB_INSURANCE',1,'10','','','1','1','1','1','','{"jsplist":[{"inputKey":"drivername","inputValue":"name","inputTitle":"司机姓名"},{"inputKey":"driverphone","inputValue":"cellphone","inputTitle":"司机电话"},{"inputKey":"carname","inputValue":"carname","inputTitle":"车型名称"},{"inputKey":"carnum","inputValue":"carnum","inputTitle":"车牌号码"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"司机姓名","queryName":"name"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.DRIVERPHONE','联系方式','DRIVERPHONE','IB_INSURANCE',2,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.CARNAME','车型名称','CARNAME','IB_INSURANCE',3,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.CARNUM','车牌号码','CARNUM','IB_INSURANCE',4,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.EVENTDATE','出险日期','EVENTDATE','IB_INSURANCE',5,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.RESPONSIBILITY','责任','RESPONSIBILITY','IB_INSURANCE',6,'6','','','1','1','2','1','','[{"key":"他责","value":"他责"},{"key":"己责","value":"己责"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.MAINTTIME','停运天数','MAINTTIME','IB_INSURANCE',7,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.CARFLAG','替换车使用','CARFLAG','IB_INSURANCE',8,'6','','','1','1','2','1','','[{"key":"有","value":"有"},{"key":"无","value":"无"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.NEWCARNAME','替换车型','NEWCARNAME','IB_INSURANCE',9,'10','','','1','1','2','2','','{"jsplist":[{"inputKey":"carname","inputValue":"typename","inputTitle":"型号名称"},{"inputKey":"carnum","inputValue":"carnum","inputTitle":"车牌号"}],"className":"IB_CAR_MGR","queryTitle":"车牌号","queryName":"carnum"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','INSURANCE','IB_INSURANCE.NEWCARNUM','替换车牌','NEWCARNUM','IB_INSURANCE',10,'1','','','1','1','2','2','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_INSURANCE';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('f7069d6b-3259-441a-ac97-4a066a890ac6','hrmannager','IB_INSURANCE','出险管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_INSURANCE';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','DRIVERNAME','司机姓名','VARCHAR','8','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','DRIVERPHONE','联系方式','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','CARNAME','车型名称','VARCHAR','32','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','CARNUM','车牌号码','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','EVENTDATE','出险日期','DATE','0','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','RESPONSIBILITY','责任','VARCHAR','4','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','MAINTTIME','停运天数','VARCHAR','8','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','CARFLAG','替换车使用','VARCHAR','8','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','NEWCARNAME','替换车型','VARCHAR','32','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','NEWCARNUM','替换车牌','VARCHAR','16','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INSURANCE','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ========================= 宿舍管理 =================================  */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_DORMITORY;
CREATE TABLE IB_DORMITORY (NAME VARCHAR(8), CALLPHONE VARCHAR(16), EID VARCHAR(18), ENTRYDATE DATETIME, CHECKINTIME DATETIME, ADDERSS VARCHAR(64), ROOMNO VARCHAR(8), USEFLAG VARCHAR(8), DEPARTUREDATE DATETIME, ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='DORMITORY';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('195d9054-c316-46b6-98f3-a48f05fbf796','hrmannager','DORMITORY','宿舍管理','/dormitory/dormitory-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='DORMITORY';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','DORMITORY','IB_DORMITORY','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='DORMITORY';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.NAME','姓名','NAME','IB_DORMITORY',1,'10','','','1','1','1','1','','{"jsplist":[{"inputKey":"name","inputValue":"name","inputTitle":"司机姓名"},{"inputKey":"callphone","inputValue":"cellphone","inputTitle":"司机电话"},{"inputKey":"eid","inputValue":"eid","inputTitle":"司机身份证号码"},{"inputKey":"entrydate","inputValue":"contractvalidday","inputTitle":"合同生效日"},{"inputKey":"departuredate","inputValue":"contractoverdate","inputTitle":"合同终止日期"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"司机姓名","queryName":"name"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.CALLPHONE','联系方式','CALLPHONE','IB_DORMITORY',2,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.EID','身份证号码','EID','IB_DORMITORY',3,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.ENTRYDATE','入职时间','ENTRYDATE','IB_DORMITORY',4,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.CHECKINTIME','入住时间','CHECKINTIME','IB_DORMITORY',5,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.ADDERSS','宿舍地址','ADDERSS','IB_DORMITORY',6,'2','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.ROOMNO','房号','ROOMNO','IB_DORMITORY',7,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.USEFLAG','使用方式','USEFLAG','IB_DORMITORY',8,'6','','','1','1','2','1','','[{"key":"自费","value":"自费"},{"key":"包住","value":"包住"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','DORMITORY','IB_DORMITORY.DEPARTUREDATE','搬离日期','DEPARTUREDATE','IB_DORMITORY',9,'3','','','1','1','2','1','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_DORMITORY';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('8b62f938-a4ce-4083-bf95-3ffdd1367135','hrmannager','IB_DORMITORY','宿舍管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_DORMITORY';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','NAME','姓名','VARCHAR','8','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','CALLPHONE','联系方式','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','EID','身份证号码','VARCHAR','18','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','ENTRYDATE','入职时间','DATE','0','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','CHECKINTIME','入住时间','DATE','0','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','ADDERSS','宿舍地址','VARCHAR','64','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','ROOMNO','房号','VARCHAR','8','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','USEFLAG','使用方式','VARCHAR','8','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','DEPARTUREDATE','搬离日期','DATE','0','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_DORMITORY','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ========================= 电话卡管理 =================================  */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_PHONECARD;
CREATE TABLE IB_PHONECARD (DRIVERNAME VARCHAR(8), EVENTDATE DATETIME, DRIVERPHONE VARCHAR(16), AMOUNT VARCHAR(16), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='PHONECARD';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('5da23b3b-32d8-4764-b6e9-73ad7108d96d','hrmannager','PHONECARD','电话卡','/phonecard/phonecard-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='PHONECARD';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','PHONECARD','IB_PHONECARD','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='PHONECARD';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','PHONECARD','IB_PHONECARD.DRIVERNAME','姓名','DRIVERNAME','IB_PHONECARD',1,'10','','','1','1','1','1','','{"jsplist":[{"inputKey":"drivername","inputValue":"name","inputTitle":"司机姓名"},{"inputKey":"driverphone","inputValue":"cellphone","inputTitle":"司机电话"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"司机姓名","queryName":"name"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','PHONECARD','IB_PHONECARD.EVENTDATE','领取时间','EVENTDATE','IB_PHONECARD',2,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','PHONECARD','IB_PHONECARD.DRIVERPHONE','电话号码','DRIVERPHONE','IB_PHONECARD',3,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','PHONECARD','IB_PHONECARD.AMOUNT','扣费金额','AMOUNT','IB_PHONECARD',4,'5','','','1','1','2','1','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_PHONECARD';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('739002d4-2f44-40a4-ad52-d3fd13637c84','hrmannager','IB_PHONECARD','电话卡管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_PHONECARD';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_PHONECARD','DRIVERNAME','姓名','VARCHAR','8','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_PHONECARD','EVENTDATE','领取时间','DATE','0','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_PHONECARD','DRIVERPHONE','电话号码','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_PHONECARD','AMOUNT','扣费金额','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_PHONECARD','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_PHONECARD','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ========================= 违章管理 =================================  */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_ILLEGAL;
CREATE TABLE IB_ILLEGAL (CARNUM VARCHAR(16), DRIVERNAME VARCHAR(8), EVENTDATE DATETIME, AMOUNT VARCHAR(16), PROCESTYPE VARCHAR(8), AMOUNTTYPE VARCHAR(8), ISPOINTS VARCHAR(8), POINTS VARCHAR(8), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='ILLEGAL';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('8ba1ea2e-46b0-40af-a240-436bdf8d5c81','hrmannager','ILLEGAL','违章管理','/illegal/illegal-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='ILLEGAL';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','ILLEGAL','IB_ILLEGAL','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='ILLEGAL';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.CARNUM','车牌号码','CARNUM','IB_ILLEGAL',1,'10','','','1','1','1','1','','{"jsplist":[{"inputKey":"carnum","inputValue":"carnum","inputTitle":"车牌号码"},{"inputKey":"drivername","inputValue":"name","inputTitle":"司机姓名"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"车牌号码","queryName":"carnum"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.DRIVERNAME','使用人','DRIVERNAME','IB_ILLEGAL',2,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.EVENTDATE','违章时间','EVENTDATE','IB_ILLEGAL',3,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.AMOUNT','罚款金额','AMOUNT','IB_ILLEGAL',4,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.PROCESTYPE','处理方式','PROCESTYPE','IB_ILLEGAL',5,'6','','','1','1','2','1','','[{"key":"自费处理","value":"自费处理"},{"key":"公司代办","value":"公司代办"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.AMOUNTTYPE','费用方式','AMOUNTTYPE','IB_ILLEGAL',6,'6','','','1','1','2','1','','[{"key":"现金","value":"现金"},{"key":"工资代扣","value":"工资代扣"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.ISPOINTS','是否扣分','ISPOINTS','IB_ILLEGAL',7,'6','','','1','1','2','1','','[{"key":"是","value":"是"},{"key":"否","value":"否"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ILLEGAL','IB_ILLEGAL.POINTS','扣分','POINTS','IB_ILLEGAL',8,'1','','','1','1','2','2','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_ILLEGAL';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('70960ef5-cba8-474c-b21a-d5bfd5f017a9','hrmannager','IB_ILLEGAL','违章管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_ILLEGAL';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','CARNUM','车牌号码','VARCHAR','16','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','DRIVERNAME','使用人','VARCHAR','8','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','EVENTDATE','违章时间','DATE','0','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','AMOUNT','罚款金额','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','PROCESTYPE','处理方式','VARCHAR','8','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','AMOUNTTYPE','费用方式','VARCHAR','8','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','ISPOINTS','是否扣分','VARCHAR','8','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','POINTS','扣分','VARCHAR','8','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ILLEGAL','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ========================= 电卡管理  =================================  */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_ELECTRICITYCARD;
CREATE TABLE IB_ELECTRICITYCARD (ELECTRICCARDNO VARCHAR(32), EVENTDATE DATETIME, INITIALAMOUNT VARCHAR(16), CHARGEAMOUNT VARCHAR(16), CHARGEDATE DATETIME, REFUNDAMOUNT VARCHAR(16), REFUNDDATE DATETIME, ELECTRICSTATUS VARCHAR(8), DRIVERNAME VARCHAR(8), CARDBALANCE VARCHAR(16), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='ELECTRICITYCARD';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('ee423424-7415-48be-b0b9-322ff2c45800','hrmannager','ELECTRICITYCARD','电卡管理','/electricitycard/electricitycard-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='ELECTRICITYCARD';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='ELECTRICITYCARD';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.ELECTRICCARDNO','电卡卡号','ELECTRICCARDNO','IB_ELECTRICITYCARD',1,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.EVENTDATE','领用日期','EVENTDATE','IB_ELECTRICITYCARD',2,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.INITIALAMOUNT','初始金额','INITIALAMOUNT','IB_ELECTRICITYCARD',3,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.CHARGEAMOUNT','充值金额','CHARGEAMOUNT','IB_ELECTRICITYCARD',4,'5','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.CHARGEDATE','充值日期','CHARGEDATE','IB_ELECTRICITYCARD',5,'3','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.REFUNDAMOUNT','退款金额','REFUNDAMOUNT','IB_ELECTRICITYCARD',6,'5','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.REFUNDDATE','退款日期','REFUNDDATE','IB_ELECTRICITYCARD',7,'3','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.ELECTRICSTATUS','电卡状态','ELECTRICSTATUS','IB_ELECTRICITYCARD',8,'6','','','1','1','2','1','','[{"key":"使用中","value":"使用中"},{"key":"公司回收","value":"公司回收"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.DRIVERNAME','使用人','DRIVERNAME','IB_ELECTRICITYCARD',9,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','ELECTRICITYCARD','IB_ELECTRICITYCARD.CARDBALANCE','卡面余额','CARDBALANCE','IB_ELECTRICITYCARD',10,'5','','','1','1','2','2','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_ELECTRICITYCARD';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('7b6d01e7-6279-48b6-a9d8-bfcccde50113','hrmannager','IB_ELECTRICITYCARD','电卡管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_ELECTRICITYCARD';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','ELECTRICCARDNO','电卡卡号','VARCHAR','32','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','EVENTDATE','领用日期','DATE','0','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','INITIALAMOUNT','初始金额','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','CHARGEAMOUNT','充值金额','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','CHARGEDATE','充值日期','DATE','0','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','REFUNDAMOUNT','退款金额','VARCHAR','16','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','REFUNDDATE','退款日期','DATE','0','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','ELECTRICSTATUS','电卡状态','VARCHAR','8','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','DRIVERNAME','使用人','VARCHAR','8','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','CARDBALANCE','卡面余额','VARCHAR','16','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_ELECTRICITYCARD','SCOPEID','范围','VARCHAR','64','否','',92);


/* ================================================================================   */
/* ========================= 电卡管理  =================================  */
/* ================================================================================   */


/* ================================================================================   */
/* ========================= 电卡管理  =================================  */
/* ================================================================================   */


/* ================================================================================   */
/* ========================= 电卡管理  =================================  */
/* ================================================================================   */

