/* =============================  IB_CONF_COMPONENT 业务模块组件管理表 --- =============================  */
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('30001','carmgr','车辆管理模块','0','sModule');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('30002','carmgr','表存储设计器','30001','Table');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('30003','carmgr','表单设计器','30001','Form');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('30004','carmgr','流程表设计器','30001','BpmTable');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('30005','carmgr','流程表单设计器','30001','BpmForm');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('30006','carmgr','流程设计器','30001','Bpm');
/* ================= 菜单管理表  ==================   */
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('carmgrmOne1','车辆管理模块','1','#','URL','8','0');
delete from IB_MENU where ID='carmgrmTwo1';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('carmgrmTwo1','车库管理','2','/garage/garage-list.do','URL','1','carmgrmOne1');
delete from IB_MENU where ID='carmgrmTwo2';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('carmgrmTwo2','车型管理','2','/car_type_info/car_type_info-list.do','URL','2','carmgrmOne1');
delete from IB_MENU where ID='carmgrmTwo3';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('carmgrmTwo3','指标公司管理','2','/target_compant_mgr/target_compant_mgr-list.do','URL','3','carmgrmOne1');
delete from IB_MENU where ID='carmgrmTwo4';
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('carmgrmTwo4','车辆库存管理','2','/car_mgr/car_mgr-list.do','URL','4','carmgrmOne1');

INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('carmgrmOne1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('carmgrmTwo1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('carmgrmTwo2','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('carmgrmTwo3','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('carmgrmTwo4','2');
/* ================================================================================   */
/* ================================  车库管理表    =================================   */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='garage';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('70f2fdb9-aeeb-4ec8-8d7a-1dbfd4bcba7e','carmgr','garage','车库信息管理','/garage/garage-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
delete from ib_conf_form_table where FORMNAME='garage';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('carmgr','garage','IB_GARAGE','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='garage';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','garage','IB_GARAGE.NAME','车库名称','NAME','IB_GARAGE',1,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','garage','IB_GARAGE.ADDRESS','车库位置','ADDRESS','IB_GARAGE',2,'2','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','garage','IB_GARAGE.TOTALCAPACITY','车库总容量','TOTALCAPACITY','IB_GARAGE',3,'5','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','garage','IB_GARAGE.PARKEDCAR','已停车辆','PARKEDCAR','IB_GARAGE',4,'5','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','garage','IB_GARAGE.ADDUSER','添加人','ADDUSER','IB_GARAGE',5,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','garage','IB_GARAGE.ADDDATE','添加日期','ADDDATE','IB_GARAGE',6,'3','','','1','1','2','1','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_GARAGE';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('33bed708-8030-4703-9f03-0e95a4c481cb','carmgr','IB_GARAGE','车库信息管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_GARAGE';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','NAME','车库名称','VARCHAR','32','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','ADDRESS','车库位置','VARCHAR','128','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','TOTALCAPACITY','车库总容量','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','PARKEDCAR','已停车辆','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','ADDUSER','添加人','VARCHAR','8','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','ADDDATE','添加日期','DATE','0','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_GARAGE','SCOPEID','范围','VARCHAR','64','否','',92);


/* ================================================================================   */
/* ================================  车型管理表    =================================   */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='cartypeinfo';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('be5c3427-dd16-4ff9-9bec-6f6b84e0fb9a','carmgr','cartypeinfo','车型管理','/car_type_info/car_type_info-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
delete from ib_conf_form_table where FORMNAME='cartypeinfo';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='cartypeinfo';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.TYPENAME','车型名称','TYPENAME','IB_CAR_TYPE_INFO',1,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.POWERTYPE','动力类型','POWERTYPE','IB_CAR_TYPE_INFO',2,'6','','','1','1','2','1','','[{"key":"汽油车","value":"汽油车"},{"key":"柴油车","value":"柴油车"},{"key":"油电混合车","value":"油电混合车"},{"key":"纯电动车","value":"纯电动车"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.CARTYPE','车辆类型','CARTYPE','IB_CAR_TYPE_INFO',3,'6','','','1','1','2','1','','[{"key":"小型轿车","value":"小型轿车"},{"key":"经济型轿车","value":"经济型轿车"},{"key":"豪华型轿车","value":"豪华型轿车"},{"key":"超豪华型轿车","value":"超豪华型轿车"},{"key":"MPV","value":"MPV"},{"key":"SUV","value":"SUV"},{"key":"豪华SUV","value":"豪华SUV"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.CARRIAGECOUNT','车辆箱数','CARRIAGECOUNT','IB_CAR_TYPE_INFO',4,'6','','','1','1','2','1','','[{"key":"1厢车","value":"1厢车"},{"key":"2厢车","value":"2厢车"},{"key":"3厢车","value":"3厢车"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.GEARBOX','变速箱','GEARBOX','IB_CAR_TYPE_INFO',5,'6','','','1','1','2','1','','[{"key":"手动挡","value":"手动挡"},{"key":"自动挡","value":"自动挡"},{"key":"手自一体","value":"手自一体"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.SEATCOUNT','座位数','SEATCOUNT','IB_CAR_TYPE_INFO',6,'6','','','1','1','2','1','','[{"key":"2座","value":"2座"},{"key":"4座","value":"4座"},{"key":"5座","value":"5座"},{"key":"7座","value":"7座"},{"key":"8座","value":"8座"},{"key":"8座以上","value":"8座以上"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.SWEPT','排量','SWEPT','IB_CAR_TYPE_INFO',7,'1','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.DISPLAYMENT','发动机功率','DISPLAYMENT','IB_CAR_TYPE_INFO',8,'1','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.LISTYEAR','上市年份','LISTYEAR','IB_CAR_TYPE_INFO',9,'1','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','cartypeinfo','IB_CAR_TYPE_INFO.UPLOADURL','图片URL','UPLOADURL','IB_CAR_TYPE_INFO',11,'13','','','1','1','2','1','','{"pathName":"ibimg"}');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_CAR_TYPE_INFO';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('478c978a-b600-4f8e-a6af-8f78f25c0c12','carmgr','IB_CAR_TYPE_INFO','车型管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_CAR_TYPE_INFO';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','TYPENAME','车型名称','VARCHAR','32','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','POWERTYPE','动力类型','VARCHAR','32','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','CARTYPE','车辆类型','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','CARRIAGECOUNT','车辆箱数','VARCHAR','8','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','GEARBOX','变速箱','VARCHAR','16','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','SEATCOUNT','座位数','VARCHAR','8','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','SWEPT','排量','VARCHAR','8','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','DISPLAYMENT','发动机功率','VARCHAR','16','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','LISTYEAR','上市年份','VARCHAR','8','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','UPLOADURL','图片URL','VARCHAR','128','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_TYPE_INFO','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ================================  指标公司管理    =================================   */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='targetcompantmgr';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('54701a00-0c84-4459-960c-b06b957f2170','carmgr','targetcompantmgr','指标公司管理','/target_compant_mgr/target_compant_mgr-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
 delete from ib_conf_form_table where FORMNAME='targetcompantmgr';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='targetcompantmgr';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.COMPANYNAME','公司名称','COMPANYNAME','IB_TARGET_COMPANT_MGR',1,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.COMPANYEXECUTOR','公司法人','COMPANYEXECUTOR','IB_TARGET_COMPANT_MGR',2,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.EXECUTORCELL','法人联系方式','EXECUTORCELL','IB_TARGET_COMPANT_MGR',3,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.LOTTARYTARGET','摇号指标数','LOTTARYTARGET','IB_TARGET_COMPANT_MGR',4,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.UPDATETARGETCOUNT','更新指标数','UPDATETARGETCOUNT','IB_TARGET_COMPANT_MGR',5,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.OTHERTARGETCOUNT','其他指标数','OTHERTARGETCOUNT','IB_TARGET_COMPANT_MGR',6,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.REGISTERDATE','注册日期','REGISTERDATE','IB_TARGET_COMPANT_MGR',7,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.ISRETURN','是否转让','ISRETURN','IB_TARGET_COMPANT_MGR',8,'6','','','1','1','2','1','','[{"key":"未转","value":"未转"},{"key":"已转让","value":"已转让"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','targetcompantmgr','IB_TARGET_COMPANT_MGR.REMARK','备注','REMARK','IB_TARGET_COMPANT_MGR',9,'2','','','1','1','2','1','','');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_TARGET_COMPANT_MGR';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('22cd47d1-6c13-460c-acce-6f49ad976e99','carmgr','IB_TARGET_COMPANT_MGR','指标公司管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_TARGET_COMPANT_MGR';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','COMPANYNAME','公司名称','VARCHAR','32','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','COMPANYEXECUTOR','公司法人','VARCHAR','8','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','EXECUTORCELL','法人联系方式','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','LOTTARYTARGET','摇号指标数','VARCHAR','16','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','UPDATETARGETCOUNT','更新指标数','VARCHAR','16','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','OTHERTARGETCOUNT','其他指标数','VARCHAR','16','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','REGISTERDATE','注册日期','DATE','0','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','ISRETURN','是否转让','VARCHAR','8','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','REMARK','备注','VARCHAR','512','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_TARGET_COMPANT_MGR','SCOPEID','范围','VARCHAR','64','否','',92);

/* ================================================================================   */
/* ================================  车辆库存管理    =================================   */
/* ================================================================================   */
/*======= 表单 ==========*/
delete from ib_conf_form where FORMNAME='carmanage';
insert into ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,FORMURL, ISEDIT,ISADD,ISDELETE,ISQUERY,ISEXCELEXPORT,ISIMPORTEXPORT,ISBPMFORM) values ('68062add-7dab-437d-9892-09f301ec858d','carmgr','carmanage','车辆库存管理','/car_mgr/car_mgr-list.do',1,1,1,1,1,2,2);
/*======= 表单对应表 ==========*/
delete from ib_conf_form_table where FORMNAME='carmanage';
insert into ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) values ('carmgr','carmanage','IB_CAR_MGR','main');
/*======= 表单字段 ==========*/
 delete from ib_conf_form_table_colums where FORMNAME='carmanage';
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.INTYPE','入库类型','INTYPE','IB_CAR_MGR',1,'6','','','1','1','2','1','','[{"key":"新车入库","value":"新车入库"},{"key":"二手车入库","value":"二手车入库"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.TYPENAME','型号名称','TYPENAME','IB_CAR_MGR',2,'7','','','1','1','2','1','','{"sql":"select typename vKey, typename vValue from IB_CAR_TYPE_INFO "}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.CARNUM','车牌号','CARNUM','IB_CAR_MGR',3,'1','','','1','1','1','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.CARFRAMENUM','车架号','CARFRAMENUM','IB_CAR_MGR',4,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.CAROUTFAYDATE','车辆出厂日期','CAROUTFAYDATE','IB_CAR_MGR',5,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.SIGNDATE','上牌日期','SIGNDATE','IB_CAR_MGR',6,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.SAVEFACTORY','存放仓库','SAVEFACTORY','IB_CAR_MGR',7,'7','','','1','1','2','1','','{"sql":"select name vKey, name vValue from IB_GARAGE "}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.CARTYPE','车辆类型','CARTYPE','IB_CAR_MGR',8,'6','','','1','1','2','1','','[{"key":"轿车","value":"轿车"},{"key":"越野车","value":"越野车"},{"key":"商务车","value":"商务车"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.TARGETCOMPANY','指标公司','TARGETCOMPANY','IB_CAR_MGR',9,'7','','','1','1','2','1','','{"sql":"select companyname vKey, companyname vValue from IB_TARGET_COMPANT_MGR "}');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.CARCOLOR','车辆颜色','CARCOLOR','IB_CAR_MGR',10,'6','','','1','1','2','1','','[{"key":"银色","value":"银色"},{"key":"红色","value":"红色"},{"key":"香槟色","value":"香槟色"},{"key":"白色","value":"白色"},{"key":"黑色","value":"黑色"},{"key":"绿色","value":"绿色"},{"key":"棕色","value":"棕色"},{"key":"黄色","value":"黄色"},{"key":"蓝色","value":"蓝色"}]');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.BUYFEX','购置税','BUYFEX','IB_CAR_MGR',11,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.SIGNNUMFEE','上牌费','SIGNNUMFEE','IB_CAR_MGR',12,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.FIRSTSECUREFEE','初次保险费','FIRSTSECUREFEE','IB_CAR_MGR',13,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.FIRSTSECUREDATE','初次保险到期日','FIRSTSECUREDATE','IB_CAR_MGR',14,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.REGISTEREID','登记证号','REGISTEREID','IB_CAR_MGR',15,'1','','','1','1','2','','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.YEARCHECKDATE','年审到期日','YEARCHECKDATE','IB_CAR_MGR',16,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.INFACTORYDATE','入库日期','INFACTORYDATE','IB_CAR_MGR',17,'3','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.CARSTATUS','车辆状态','CARSTATUS','IB_CAR_MGR',18,'1','','','1','1','2','1','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.REMARK','备注','REMARK','IB_CAR_MGR',19,'2','','','1','1','2','2','','');
insert into ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN, TABLENAME,COLUMNNO,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCMUST,FCDEFAULT, CONFSELECTINFO) values ('carmgr','carmanage','IB_CAR_MGR.UPLOADPRCTURE','上传图片','UPLOADPRCTURE','IB_CAR_MGR',20,'13','','','1','1','2','2','','{"pathName":"ibimg"}');
/*======= 表 ==========*/
 delete from ib_conf_table where TABLENAME='IB_CAR_MGR';
insert into ib_conf_table(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,TABLETYPE, PARENTTABLEID,ISBPMTABLE) values ('12d63662-4201-4e43-809a-9cfc510ac524','carmgr','IB_CAR_MGR','车辆库存管理','1','null',2);
/*======= 表字段 ==========*/
 delete from ib_conf_table_columns where TABLENAME='IB_CAR_MGR';
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','INTYPE','入库类型','VARCHAR','16','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','TYPENAME','型号名称','VARCHAR','32','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','CARNUM','车牌号','VARCHAR','16','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','CARFRAMENUM','车架号','VARCHAR','32','是','',4);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','CAROUTFAYDATE','车辆出厂日期','DATE','0','是','',5);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','SIGNDATE','上牌日期','DATE','0','是','',6);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','SAVEFACTORY','存放仓库','VARCHAR','16','是','',7);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','CARTYPE','车辆类型','VARCHAR','16','是','',8);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','TARGETCOMPANY','指标公司','VARCHAR','32','是','',9);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','CARCOLOR','车辆颜色','VARCHAR','16','是','',10);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','BUYFEX','购置税','VARCHAR','16','是','',11);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','SIGNNUMFEE','上牌费','VARCHAR','16','是','',12);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','FIRSTSECUREFEE','初次保险费','VARCHAR','18','是','',13);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','FIRSTSECUREDATE','初次保险到期日','DATE','0','是','',14);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','REGISTEREID','登记证号','VARCHAR','32','是','',15);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','YEARCHECKDATE','年审到期日','DATE','0','是','',16);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','INFACTORYDATE','入库日期','DATE','0','是','',17);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','CARSTATUS','车辆状态','VARCHAR','16','是','',18);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','REMARK','备注','VARCHAR','256','是','',19);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','UPLOADPRCTURE','上传图片','VARCHAR','128','是','',20);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_CAR_MGR','SCOPEID','范围','VARCHAR','64','否','',92);

