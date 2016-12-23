/* ================= 菜单管理表  ==================   */
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('ib_indeximgbyworks0001','首页','3','/html/webpage/index.html','URL','1','mTwoindex26');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME, MENUORDER,PARENTID) VALUES('ib_indeximgbyworks0002','首页图片','3','/indeximgbyworks/indeximgbyworks-list.do','URL','2','mTwoindex26');
/*===============   菜单和角色模板关联表  =============================*/
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('ib_indeximgbyworks0001','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('ib_indeximgbyworks0002','2');
update IB_MENU set desktopIcon = '1' ,iconUrl='imac/img/Finder.png' where id = 'ib_indeximgbyworks0001';
update IB_MENU set desktopIcon = '1' ,iconUrl='imac/img/Pixelmator.png' where id = 'ib_indeximgbyworks0002';

/*==============================================================*/
/* cms 公告栏目表   */
/*==============================================================*/
TRUNCATE TABLE IB_CMS_CATALOG;
INSERT INTO IB_CMS_CATALOG(ID,name,code,logo,catalogtype,template_index,template_list,template_detail,SCOPEID) VALUES(1,'新闻','news',null,0,'/default/index.html','/default/list.html','/default/detail.html','1');

/*==============================================================*/
/* 图片管理作品展示表   */
/*==============================================================*/
/*======= 表字段 ==========
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INDEXIMGBYWORKS','IMGURL','图片地址','VARCHAR','256','是','',1);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INDEXIMGBYWORKS','IMGINFO','图片信息描述','VARCHAR','128','是','',2);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INDEXIMGBYWORKS','SHOWFLAG','是否显示标记','VARCHAR','8','是','',3);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INDEXIMGBYWORKS','ID','UUID主键','VARCHAR','64','否','',91);
insert into ib_conf_table_columns(tableName,columnValue,columnName,columnType,columnSize, isNull,defaultValue,columnNo) values ('IB_INDEXIMGBYWORKS','SCOPEID','范围','VARCHAR','64','否','',92);
*/


