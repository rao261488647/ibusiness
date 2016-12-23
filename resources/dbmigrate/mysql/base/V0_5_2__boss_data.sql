/*===============  BOSS平台模块菜单配置  =============================*/
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('bossThree1','生产进度曲线','3','/produce-schedule-chart/list-chart.do','URL','1','bossTwo3');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('bossThree2','产品销售柱形图','3','/produce-schedule-chart/list-productSell.do','URL','2','bossTwo3');
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('bossThree3','产品销售额饼图','3','/produce-schedule-chart/list-pieDonut.do','URL','2','bossTwo3');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('bossThree1','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('bossThree2','2');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('bossThree3','2');

/*===============  全景图菜单配置  =============================*/
INSERT INTO IB_MENU(ID,MENUNAME,MENULEVEL,MENUURL,MENUIFRAME,MENUORDER,PARENTID) VALUES('panoramaThree1','全景图','3','/panorama/panorama-list.do','URL','1','panoramaTwo4');
INSERT INTO IB_MENU_ROLE_DEF(MENU_ID,ROLE_DEF_ID) VALUES('panoramaThree1','2');
/*===============  全景图初始化数据  =============================*/
insert into ib_panorama(SCOPEID,ID,NAME,PANORAMAADDRESS,PANORAMAURL) values ('1','xiyidian-0001','洗衣店全景图','11111','/ibusiness/panorama/output/index.html');
