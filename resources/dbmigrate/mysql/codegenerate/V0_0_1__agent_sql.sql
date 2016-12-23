/* =============================  IB_CONF_COMPONENT 业务模块组件管理表 --- =============================  */
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10001','agent','代驾模块','0','sModule');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10002','agent','表存储设计器','10001','Table');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10003','agent','表单设计器','10001','Form');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10004','agent','流程表设计器','10001','BpmTable');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10005','agent','流程表单设计器','10001','BpmForm');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10006','agent','流程设计器','10001','Bpm');
/* ================= 菜单管理表  ==================   */
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('agentmOne1','代驾模块','1','#','URL','4','0');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('agentmTwo1','代驾司机列表管理','2','/agent_driver_mgr/agent_driver_mgr-list.do','URL','1','agentmOne1');

INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('agentmOne1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('agentmTwo1','2');

/* ======================== 业务模块组件管理表 ====================  */
/* ====== 代驾司机管理 =====  */
DROP TABLE IF EXISTS IB_AGENT_DRIVER_MGR;
CREATE TABLE IB_AGENT_DRIVER_MGR (NUM VARCHAR(32), NAME VARCHAR(16), WORKCELL VARCHAR(18), CELL VARCHAR(18), EID VARCHAR(24), SEX VARCHAR(8), ADDRESS VARCHAR(64), PUBLIAHGRD VARCHAR(32), BIRTH DATETIME, NATION VARCHAR(8), DRIVERNUM VARCHAR(24), DRIVERFILENUM VARCHAR(18), PREDRIVERDATE DATETIME, PRECARTYPE VARCHAR(18), VALIDSTARTDATE DATETIME, VALIDTREM VARCHAR(16), CARNAME VARCHAR(16), CONTRACTERM VARCHAR(8), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;

/* ================================================================================   */
/* ================================  代驾司机管理表    =================================   */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='agentdrivermgr';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('e5a9a347-9f74-4ac2-855d-b9db45ddb3fc','agent','agentdrivermgr','代驾司机列表管理','/agent_driver_mgr/agent_driver_mgr-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
delete from ib_conf_form_table where FORMNAME='agentdrivermgr';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='agentdrivermgr';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.NUM','司机编号','NUM','IB_AGENT_DRIVER_MGR',1,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.NAME','司机姓名','NAME','IB_AGENT_DRIVER_MGR',2,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.WORKCELL','司机工作手机','WORKCELL','IB_AGENT_DRIVER_MGR',3,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.CELL','司机手机','CELL','IB_AGENT_DRIVER_MGR',4,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.EID','司机身份证号码','EID','IB_AGENT_DRIVER_MGR',5,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.SEX','性别','SEX','IB_AGENT_DRIVER_MGR',6,'6','','','1','1','2','1','','[{"key":"男","value":"男"},{"key":"女","value":"女"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.ADDRESS','地址','ADDRESS','IB_AGENT_DRIVER_MGR',7,'2','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.PUBLIAHGRD','发证机关','PUBLIAHGRD','IB_AGENT_DRIVER_MGR',8,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.BIRTH','出生日期','BIRTH','IB_AGENT_DRIVER_MGR',9,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.NATION','民族','NATION','IB_AGENT_DRIVER_MGR',10,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.DRIVERNUM','司机驾驶证编号','DRIVERNUM','IB_AGENT_DRIVER_MGR',11,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.DRIVERFILENUM','司机驾驶证档案号','DRIVERFILENUM','IB_AGENT_DRIVER_MGR',12,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.PREDRIVERDATE','驾照初领日期','PREDRIVERDATE','IB_AGENT_DRIVER_MGR',13,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.PRECARTYPE','准驾车型','PRECARTYPE','IB_AGENT_DRIVER_MGR',14,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.VALIDSTARTDATE','有效起始日期','VALIDSTARTDATE','IB_AGENT_DRIVER_MGR',15,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.VALIDTREM','有效期限','VALIDTREM','IB_AGENT_DRIVER_MGR',16,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.CARNAME','车型名称','CARNAME','IB_AGENT_DRIVER_MGR',17,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('agent','agentdrivermgr','IB_AGENT_DRIVER_MGR.CONTRACTERM','合同期限','CONTRACTERM','IB_AGENT_DRIVER_MGR',18,'1','','','1','1','2','','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_AGENT_DRIVER_MGR';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('dce5b6d1-bfdc-4835-8b82-eedbde086cf6','agent','IB_AGENT_DRIVER_MGR','代驾司机列表管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_AGENT_DRIVER_MGR';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','NUM','司机编号','VARCHAR','32','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','NAME','司机姓名','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','WORKCELL','司机工作手机','VARCHAR','18','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','CELL','司机手机','VARCHAR','18','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','EID','司机身份证号码','VARCHAR','24','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','SEX','性别','VARCHAR','8','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','ADDRESS','地址','VARCHAR','64','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','PUBLIAHGRD','发证机关','VARCHAR','32','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','BIRTH','出生日期','DATE','0','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','NATION','民族','VARCHAR','8','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','DRIVERNUM','司机驾驶证编号','VARCHAR','24','是','',11);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','DRIVERFILENUM','司机驾驶证档案号','VARCHAR','18','是','',12);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','PREDRIVERDATE','驾照初领日期','DATE','0','是','',13);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','PRECARTYPE','准驾车型','VARCHAR','18','是','',14);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','VALIDSTARTDATE','有效起始日期','DATE','0','是','',15);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','VALIDTREM','有效期限','VARCHAR','16','是','',16);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','CARNAME','车型名称','VARCHAR','16','是','',17);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','CONTRACTERM','合同期限','VARCHAR','8','是','',18);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_AGENT_DRIVER_MGR','SCOPEID','范围','VARCHAR','64','否','',92);


