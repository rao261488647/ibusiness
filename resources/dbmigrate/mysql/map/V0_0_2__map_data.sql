/* ========================= 地图基础配置表 ========================= */
truncate table IB_MAP_CONF;
insert into IB_MAP_CONF(MAPIP,MAPPORT,MAPCITY,MAPSHOWONLINE,MAPGRID,MAPCENTERLONGITUDE,MAPCENTERLATITUDE) 
values ('localhost','8088','haerbin','false','3','0','0');
commit;

/*=============客户表  =================================================*/
truncate table IB_CUSTOMER_INFO;
insert into IB_CUSTOMER_INFO(ID,CUSTOMERNO,CUSTOMERSTATE,CUSTOMERNAME,CUSTOMERTYPE,ADDRESS,PHONE,TELEPHONE)
values ('0001', 'tx01', '激活', '天翔', 'A类', '哈尔滨', '123321312', '86108888');
commit;

/* ============================= 图标名称表  =============================  */
truncate table ib_map_icon;
insert into ib_map_icon(id, iconname)
values ('P', '结点');
insert into ib_map_icon(id, iconname)
values ('R', '公司');
insert into ib_map_icon(id, iconname)
values ('Z', '客户');
commit;

/*==============================================================*/
/* 图标类型表 */
/*==============================================================*/
truncate table ib_map_icon_type;
insert into ib_map_icon_type(id, iconId, iconname, iconstatusname, iconpath, iconstatusid)
values ('0003','P', '结点', '正常', 'map/images/pointTB.png', 0);
insert into ib_map_icon_type(id, iconId, iconname, iconstatusname, iconpath, iconstatusid)
values ('0002','R', '公司', '正常', 'map/images/heatSourceNormal.png', 0);
insert into ib_map_icon_type(id, iconId, iconname, iconstatusname, iconpath, iconstatusid)
values ('0001','Z', '客户', '正常', 'map/images/siteNormal.png', 0);
commit;

