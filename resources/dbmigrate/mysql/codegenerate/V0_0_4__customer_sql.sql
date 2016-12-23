/* =============================  IB_CONF_COMPONENT 业务模块组件管理表 --- =============================  */
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('40001','customermgr','客户管理模块','0','sModule');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('40002','customermgr','表存储设计器','40001','Table');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('40003','customermgr','表单设计器','40001','Form');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('40004','customermgr','流程表设计器','40001','BpmTable');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('40005','customermgr','流程表单设计器','40001','BpmForm');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('40006','customermgr','流程设计器','40001','Bpm');
/* ================= 菜单管理表  ==================   */
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('customermgrmOne1','客户管理模块','1','#','URL','9','0');
delete from IB_MENU where ID='customermgrmTwo1';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('customermgrmTwo1','客户管理','2','/customer_mgr/customer_mgr-list.do','URL','1','customermgrmOne1');
delete from IB_MENU where ID='customermgrmTwo2';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('customermgrmTwo2','租车预订信息','2','/car_rental/car_rental-list.do','URL','2','customermgrmOne1');

INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('customermgrmOne1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('customermgrmTwo1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('customermgrmTwo2','2');
/* ======================== 业务模块组件管理表 ====================  */
/* ================================================================================   */
/* ================================  客户管理表    =================================   */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_CUSTOMER_MGR;
CREATE TABLE IB_CUSTOMER_MGR (CUSTOMERNAME VARCHAR(18), CUSTOMERCELL VARCHAR(16), UNITNAME VARCHAR(32), POST VARCHAR(16), CUSTOMERADD VARCHAR(128), CUSTOMERTYPE VARCHAR(16), CUSTOMERTOBE VARCHAR(16), PASSWORD VARCHAR(32), DEVICE VARCHAR(64), IMEI VARCHAR(64), APPSYSVERSION VARCHAR(8), TOKEN VARCHAR(64), RECOMMENDMAN VARCHAR(24), SALESMAN VARCHAR(16), REMARKS VARCHAR(512), CREATEDATETIME DATETIME, TOSOURCE VARCHAR(64), USERSTATUS VARCHAR(8), SEX VARCHAR(8), ORIGIN VARCHAR(8), OCCUPATION VARCHAR(8), IDCARD VARCHAR(24), ISCRIME VARCHAR(4), CONTRACTSTATUS VARCHAR(8), CONTRACTPLAN VARCHAR(16), INTENTION VARCHAR(8), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/* ====== 插入数据 =====  */
insert into ib_customer_mgr(CUSTOMERNAME,CUSTOMERCELL,UNITNAME,POST,CUSTOMERADD,CUSTOMERTYPE,CUSTOMERTOBE,ID,SCOPEID,PASSWORD) values ('刘青','13825636548','','','北京','个人','代驾司机','413ea265-748a-4160-9c59-750817b33b96',NULL,'1'),('吴桐','13825636547','一菲科技有限公司','经理','一菲科技有限公司','单位','其他','4d326fa3-7733-48ee-a990-8c0abf777cbb',NULL,'1');

/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='customermgr';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('bb88c882-9b72-4950-b32c-53d3a04431e0','customermgr','customermgr','客户管理','/customer_mgr/customer_mgr-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='customermgr';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('customermgr','customermgr','IB_CUSTOMER_MGR','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='customermgr';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CUSTOMERNAME','客户姓名','CUSTOMERNAME','IB_CUSTOMER_MGR',1,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.SEX','性别','SEX','IB_CUSTOMER_MGR',2,'6','','','1','1','2','1','','[{"key":"男","value":"男"},{"key":"女","value":"女"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.IMEI','设备唯一标识','IMEI','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.TOKEN','手机token','TOKEN','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.DEVICE','设备型号','DEVICE','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.IDCARD','身份证号码','IDCARD','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.ORIGIN','籍贯','ORIGIN','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.ISCRIME','有无犯罪记录','ISCRIME','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.PASSWORD','密码','PASSWORD','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.TOSOURCE','客户来源','TOSOURCE','IB_CUSTOMER_MGR',2,'6','','','1','1','2','1','','[{"key":"电话邀约","value":"电话邀约"},{"key":"司机来电","value":"司机来电"},{"key":"司机到场","value":"司机到场"},{"key":"已签约","value":"已签约"},{"key":"已离职","value":"已离职"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.INTENTION','入职意向','INTENTION','IB_CUSTOMER_MGR',2,'6','','','1','1','2','1','','[{"key":"A","value":"A:有意向近两天到店签约"},{"key":"B","value":"B:有意向近两天到店洽谈"},{"key":"C","value":"C:有意向但未知何时到店洽谈"},{"key":"D","value":"D:无意向"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.OCCUPATION','职业','OCCUPATION','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.USERSTATUS','客户状态','USERSTATUS','IB_CUSTOMER_MGR',2,'6','','','1','1','2','1','','[{"key":"网络","value":"网络"},{"key":"地面广告","value":"地面广告"},{"key":"司机介绍","value":"司机介绍"},{"key":"业务邀约","value":"业务邀约"},{"key":"其他","value":"其他"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CONTRACTPLAN','签约方案','CONTRACTPLAN','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CUSTOMERCELL','客户电话','CUSTOMERCELL','IB_CUSTOMER_MGR',2,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.RECOMMENDMAN','推荐人','RECOMMENDMAN','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.APPSYSVERSION','系统版本','APPSYSVERSION','IB_CUSTOMER_MGR',2,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CONTRACTSTATUS','签约状况','CONTRACTSTATUS','IB_CUSTOMER_MGR',2,'6','','','1','1','2','1','','[{"key":"是","value":"是"},{"key":"否","value":"否"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.UNITNAME','单位名称','UNITNAME','IB_CUSTOMER_MGR',4,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.POST','职务','POST','IB_CUSTOMER_MGR',5,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CUSTOMERADD','客户地址','CUSTOMERADD','IB_CUSTOMER_MGR',6,'2','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CUSTOMERTYPE','客户类别','CUSTOMERTYPE','IB_CUSTOMER_MGR',7,'6','','','1','1','1','1','','[{"key":"单位","value":"单位"},{"key":"个人","value":"个人"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CUSTOMERTOBE','客户归属','CUSTOMERTOBE','IB_CUSTOMER_MGR',8,'6','','','1','1','1','1','','[{"key":"其他","value":"其他"},{"key":"代驾司机","value":"代驾司机"},{"key":"专车司机","value":"专车司机"},{"key":"买车客户","value":"买车客户"},{"key":"解约专车","value":"解约专车"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.SALESMAN','业务员','SALESMAN','IB_CUSTOMER_MGR',15,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.REMARKS','备注','REMARKS','IB_CUSTOMER_MGR',16,'2','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','customermgr','IB_CUSTOMER_MGR.CREATEDATETIME','新建日期','CREATEDATETIME','IB_CUSTOMER_MGR',16,'3','','','1','1','2','1','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_CUSTOMER_MGR';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('a025714e-9dc8-4038-992c-76e5040bd494','customermgr','IB_CUSTOMER_MGR','客户管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_CUSTOMER_MGR';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CUSTOMERNAME','客户姓名','VARCHAR','18','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CUSTOMERCELL','客户电话','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','UNITNAME','单位名称','VARCHAR','32','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','POST','职务','VARCHAR','16','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CUSTOMERADD','客户地址','VARCHAR','128','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CUSTOMERTYPE','客户类别','VARCHAR','16','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CUSTOMERTOBE','客户归属','VARCHAR','16','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','PASSWORD','密码','VARCHAR','32','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','DEVICE','设备型号','VARCHAR','64','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','IMEI','设备唯一标识','VARCHAR','64','是','',11);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','APPSYSVERSION','系统版本','VARCHAR','8','是','',12);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','TOKEN','手机token','VARCHAR','64','是','',13);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','RECOMMENDMAN','推荐人','VARCHAR','24','是','',14);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','SALESMAN','业务员','VARCHAR','16','是','',17);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','REMARKS','备注','VARCHAR','512','是','',18);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CREATEDATETIME','新建日期','DATE','0','是','',19);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','TOSOURCE','客户来源','VARCHAR','64','是','',20);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','USERSTATUS','客户状态','VARCHAR','8','是','',21);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','SEX','性别','VARCHAR','8','是','',22);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','ORIGIN','籍贯','VARCHAR','8','是','',23);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','OCCUPATION','职业','VARCHAR','8','是','',24);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','IDCARD','身份证号码','VARCHAR','24','是','',25);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','ISCRIME','有无犯罪记录','VARCHAR','4','是','',26);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CONTRACTSTATUS','签约状况','VARCHAR','8','是','',27);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','CONTRACTPLAN','签约方案','VARCHAR','16','是','',28);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','INTENTION','入职意向','VARCHAR','8','是','',29);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CUSTOMER_MGR','SCOPEID','范围','VARCHAR','64','否','',92);

/* ====== 租车预订信息  =====  */
DROP TABLE IF EXISTS IB_CAR_RENTAL;
CREATE TABLE IB_CAR_RENTAL (NAME VARCHAR(16), TELEPHONE VARCHAR(16), RENTALTYPE VARCHAR(8), SUBMITDATE DATETIME, CARDATE DATETIME, TRIPINFO VARCHAR(128), DEALPERSON VARCHAR(16), DEALRESULT VARCHAR(32), DEALTIME DATETIME, PLATFORM VARCHAR(64), TERMDATE DATETIME, SIGNDATE DATETIME, REGTIME DATETIME, ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/* ================================================================================   */
/* ================================  租车预订信息表    ================================= */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='carrental';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('ba24f2e3-4e0f-43e2-b695-5e3ff40d75db','customermgr','carrental','租车预订信息','/car_rental/car_rental-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
delete from ib_conf_form_table where FORMNAME='carrental';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('customermgr','carrental','IB_CAR_RENTAL','main');
/*======= 表单字段 ==========*/
delete from ib_conf_form_table_colums where FORMNAME='carrental';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.NAME','姓名','NAME','IB_CAR_RENTAL',1,'10','','','1','1','2','1','','{"jsplist":[{"inputKey":"name","inputValue":"customername","inputTitle":"客户姓名"},{"inputKey":"telephone","inputValue":"customercell","inputTitle":"客户电话"}],"className":"IB_CUSTOMER_MGR","queryTitle":"客户姓名","queryName":"customername"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.TELEPHONE','电话','TELEPHONE','IB_CAR_RENTAL',2,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.RENTALTYPE','预约类型','RENTALTYPE','IB_CAR_RENTAL',3,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.SUBMITDATE','提交日期','SUBMITDATE','IB_CAR_RENTAL',4,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.CARDATE','用车日期时间','CARDATE','IB_CAR_RENTAL',5,'3','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.TRIPINFO','行程','TRIPINFO','IB_CAR_RENTAL',6,'2','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.DEALPERSON','处理人','DEALPERSON','IB_CAR_RENTAL',7,'1','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.DEALRESULT','处理结果','DEALRESULT','IB_CAR_RENTAL',8,'2','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.DEALTIME','处理时间','DEALTIME','IB_CAR_RENTAL',9,'3','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.PLATFORM','租车平台','PLATFORM','IB_CAR_RENTAL',10,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.TERMDATE','合同期','TERMDATE','IB_CAR_RENTAL',11,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.SIGNDATE','预约上门签约时间','SIGNDATE','IB_CAR_RENTAL',12,'3','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('customermgr','carrental','IB_CAR_RENTAL.REGTIME','到场注册时间','REGTIME','IB_CAR_RENTAL',13,'3','','','1','1','2','2','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_CAR_RENTAL';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('38581e42-162c-42bc-a438-bfae414f0072','customermgr','IB_CAR_RENTAL','租车预订信息','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_CAR_RENTAL';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','NAME','姓名','VARCHAR','16','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','TELEPHONE','电话','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','RENTALTYPE','预约类型','VARCHAR','8','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','SUBMITDATE','提交日期','DATE','0','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','CARDATE','用车日期时间','DATE','0','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','TRIPINFO','行程','VARCHAR','128','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','DEALPERSON','处理人','VARCHAR','16','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','DEALRESULT','处理结果','VARCHAR','32','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','DEALTIME','处理时间','DATE','0','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','PLATFORM','租车平台','VARCHAR','64','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','TERMDATE','合同期','DATE','0','是','',11);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','SIGNDATE','预约上门签约时间','DATE','0','是','',12);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','REGTIME','到场注册时间','DATE','0','是','',13);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_RENTAL','SCOPEID','范围','VARCHAR','64','否','',92);
