/* ================= 菜单管理表  ==================   */
delete from IB_MENU where ID='hrmanageTwo6';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo6','以租代购 ','2','/carlease/carlease-list.do','URL','6','hrmanageOne');
delete from IB_MENU where ID='hrmanageTwo7';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('hrmanageTwo7','纯租 ','2','/carrental/carrental-list.do','URL','7','hrmanageOne');

/* ================================================================================   */
/* ========================= 纯租 =================================  */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_CARRENTAL;
CREATE TABLE IB_CARRENTAL (DRIVERNAME VARCHAR(8), OPTIONPLAN VARCHAR(16), CARNAME VARCHAR(32), CARNUM VARCHAR(16), FIXEDVALUE VARCHAR(16), EVENTDATE DATETIME, INITIALKM VARCHAR(16), ENDMONTHKM VARCHAR(16), THANKM VARCHAR(16), AMOUNT VARCHAR(16), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='CCARRENTAL';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('39b9ff58-ed8a-4143-a98d-d7986130ff85','hrmannager','CCARRENTAL','纯租','/carrental/carrental-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='CCARRENTAL';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','CCARRENTAL','IB_CARRENTAL','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='CCARRENTAL';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.DRIVERNAME','司机姓名','DRIVERNAME','IB_CARRENTAL',1,'10','','','1','1','1','1','','{"jsplist":[{"inputKey":"drivername","inputValue":"name","inputTitle":"司机姓名"},{"inputKey":"carnum","inputValue":"carnum","inputTitle":"车牌号码"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"司机姓名","queryName":"name"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.OPTIONPLAN','方案','OPTIONPLAN','IB_CARRENTAL',2,'6','','','1','1','2','1','','[{"key":"1","value":"腾势半年5999"},{"key":"2","value":"腾势月租6999"},{"key":"3","value":"比亚迪E5半年5200"},{"key":"3","value":"比亚迪E5月租5800"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.CARNAME','车型','CARNAME','IB_CARRENTAL',3,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.CARNUM','车牌','CARNUM','IB_CARRENTAL',4,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.FIXEDVALUE','固定值','FIXEDVALUE','IB_CARRENTAL',5,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.EVENTDATE','提车日期','EVENTDATE','IB_CARRENTAL',6,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.INITIALKM','初始公里数','INITIALKM','IB_CARRENTAL',7,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.ENDMONTHKM','月末公里数','ENDMONTHKM','IB_CARRENTAL',8,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.THANKM','超出公里数','THANKM','IB_CARRENTAL',9,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CCARRENTAL','IB_CARRENTAL.AMOUNT','扣款金额','AMOUNT','IB_CARRENTAL',10,'5','','','1','1','2','1','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_CARRENTAL';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('1646a317-f0ca-4fef-a9b7-3d5fe6660876','hrmannager','IB_CARRENTAL','纯组','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_CARRENTAL';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','DRIVERNAME','司机姓名','VARCHAR','8','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','OPTIONPLAN','方案','VARCHAR','16','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','CARNAME','车型','VARCHAR','32','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','CARNUM','车牌','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','FIXEDVALUE','固定值','VARCHAR','16','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','EVENTDATE','提车日期','DATE','0','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','INITIALKM','初始公里数','VARCHAR','16','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','ENDMONTHKM','月末公里数','VARCHAR','16','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','THANKM','超出公里数','VARCHAR','16','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','AMOUNT','扣款金额','VARCHAR','16','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARRENTAL','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ========================= 已租代购  =================================  */
/* ================================================================================   */
/*======= 创建表 ==========*/
DROP TABLE IF EXISTS IB_CARLEASE;
CREATE TABLE IB_CARLEASE (DRIVERNAME VARCHAR(8), CARNAME VARCHAR(32), STARTDAY DATETIME, PAYMENTAMOUNT VARCHAR(16), CONTRACTTERM VARCHAR(16), FIRSTMONTH VARCHAR(16), SECONDMONTH VARCHAR(16), ID VARCHAR(64), SCOPEID VARCHAR(64),  PRIMARY KEY (ID)) ENGINE=INNODB;
/*======= 表单 ==========*/
 delete from ib_conf_form where FORMNAME='CARLEASE';
 insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('56454e60-68a4-4353-80ec-1abc1b81f0bd','hrmannager','CARLEASE','以租代购','/carlease/carlease-list.do',1,1,1,2,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='CARLEASE';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('hrmannager','CARLEASE','IB_CARLEASE','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='CARLEASE';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CARLEASE','IB_CARLEASE.DRIVERNAME','司机姓名','DRIVERNAME','IB_CARLEASE',1,'10','','','1','1','2','1','','{"jsplist":[{"inputKey":"drivername","inputValue":"name","inputTitle":"司机姓名"},{"inputKey":"carname","inputValue":"carname","inputTitle":"车型名称"},{"inputKey":"startday","inputValue":"contractvalidday","inputTitle":"合同生效日"},{"inputKey":"contractterm","inputValue":"contractterm","inputTitle":"合同期限"}],"className":"IB_SPECIAL_DRIVER_INFO","queryTitle":"司机姓名","queryName":"name"}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CARLEASE','IB_CARLEASE.CARNAME','车型','CARNAME','IB_CARLEASE',2,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CARLEASE','IB_CARLEASE.STARTDAY','签约日期','STARTDAY','IB_CARLEASE',3,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CARLEASE','IB_CARLEASE.PAYMENTAMOUNT','首付金额','PAYMENTAMOUNT','IB_CARLEASE',4,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CARLEASE','IB_CARLEASE.CONTRACTTERM','签约期数','CONTRACTTERM','IB_CARLEASE',5,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CARLEASE','IB_CARLEASE.FIRSTMONTH','第一次月供','FIRSTMONTH','IB_CARLEASE',6,'5','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('hrmannager','CARLEASE','IB_CARLEASE.SECONDMONTH','第二次月供','SECONDMONTH','IB_CARLEASE',7,'5','','','1','1','2','2','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_CARLEASE';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('9390f974-78ef-47fb-bda9-f0b2af846cc6','hrmannager','IB_CARLEASE','以租代购','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_CARLEASE';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','DRIVERNAME','司机姓名','VARCHAR','8','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','CARNAME','车型','VARCHAR','32','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','STARTDAY','签约日期','DATE','0','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','PAYMENTAMOUNT','首付金额','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','CONTRACTTERM','签约期数','VARCHAR','16','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','FIRSTMONTH','第一次月供','VARCHAR','16','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','SECONDMONTH','第二次月供','VARCHAR','16','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CARLEASE','SCOPEID','范围','VARCHAR','64','否','',92);


/* ================================================================================   */
/* ========================= 电卡管理  =================================  */
/* ================================================================================   */


/* ================================================================================   */
/* ========================= 电卡管理  =================================  */
/* ================================================================================   */


/* ================================================================================   */
/* ========================= 电卡管理  =================================  */
/* ================================================================================   */

