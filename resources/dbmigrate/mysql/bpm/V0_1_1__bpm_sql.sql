/*==============================================================*/
/* IB_CONF_BPM_FLOW BPM流程管理表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_PROCESS;
CREATE TABLE IB_BPM_PROCESS (
	     ID  VARCHAR(64),
	     PACKAGENAME VARCHAR(128),
	     FLOWNAME VARCHAR(128),
	     FLOWTITLE VARCHAR(128),
	     PRIORITY INTEGER,
	     DESCN VARCHAR(512),
	     VERSIONID VARCHAR(64),
	     USETASKCONF VARCHAR(64),
	     FORMID VARCHAR(64),
	     FLOWURL VARCHAR(256),
        CONSTRAINT PK_IB_BPM_PROCESS PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* IB_BPM_PROCESS_VERSION 流程版本管理   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_PROCESS_VERSION;
CREATE TABLE IB_BPM_PROCESS_VERSION (
        ID VARCHAR(64),
		BPMPROSESSID VARCHAR(128),
		BPMPROSESSKEY VARCHAR(128),
		BPMPROSESSVERSION INTEGER,
        CONSTRAINT PK_BPM_PROCESS_VERSION PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* IB_BPM_FLOW_NODE 流程节点配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_FLOW_NODE;
CREATE TABLE IB_BPM_FLOW_NODE (
        ID VARCHAR(64),
        PACKAGENAME VARCHAR(128),
	    FLOWID VARCHAR(64),
	    FLOWVERSIONID VARCHAR(64),
		NODECODE VARCHAR(256),
		NODENAME VARCHAR(256),
		NODETYPE VARCHAR(256),
		CONFUSER VARCHAR(64),
		CONFLISTENER VARCHAR(64),
		CONFRULE VARCHAR(64),
		CONFFORM VARCHAR(64),
		CONFOPERATION VARCHAR(64),
		CONFNOTICE VARCHAR(64),
		PRIORITY  INTEGER,
        CONSTRAINT PK_IB_BPM_FLOW_NODE PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/*  流程节点人员(参与者)配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_NODE_USER;
CREATE TABLE IB_BPM_NODE_USER (
        ID VARCHAR(64),
        PACKAGENAME VARCHAR(128),
	    FLOWID VARCHAR(64),
	    FLOWVERSIONID VARCHAR(64),
		NODEID VARCHAR(64),
		USERVALUE VARCHAR(256),
		USERTYPE VARCHAR(64),
		USERSTATUS VARCHAR(64),
		PRIORITY  INTEGER,
        CONSTRAINT PK_IB_BPM_NODE_USER PRIMARY KEY(ID)
) ENGINE=INNODB;
/*==============================================================*/
/*  流程节点事件配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_NODE_LISTENER;
CREATE TABLE IB_BPM_NODE_LISTENER (
        ID VARCHAR(64),
        PACKAGENAME VARCHAR(128),
	    FLOWID VARCHAR(64),
	    FLOWVERSIONID VARCHAR(64),
		NODEID VARCHAR(64),
		LISTENERVALUE VARCHAR(256),
		LISTENERTYPE INTEGER,
		LISTENERSTATUS VARCHAR(64),
		PRIORITY  INTEGER,
        CONSTRAINT PK_BPM_NODE_LISTENER PRIMARY KEY(ID)
) ENGINE=INNODB;
/*==============================================================*/
/*  流程节点表单配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_NODE_FORM;
CREATE TABLE IB_BPM_NODE_FORM (
        ID VARCHAR(64),
        PACKAGENAME VARCHAR(128),
	    FLOWID VARCHAR(64),
	    FLOWVERSIONID VARCHAR(64),
		NODEID VARCHAR(64),
		FORMVALUE VARCHAR(256),
		FORMTYPE INTEGER,
		ORIGINFORMVALUE  VARCHAR(256),
		ORIGINFORMTYPE VARCHAR(64),
		FORMSTATUS VARCHAR(64),
        CONSTRAINT PK_BPM_NODE_FORM PRIMARY KEY(ID)
) ENGINE=INNODB;
/*==============================================================*/
/*  流程节点(会签)关联配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_NODE_COUNTERSIGN;
CREATE TABLE IB_BPM_NODE_COUNTERSIGN (
        ID VARCHAR(64),
        PACKAGENAME VARCHAR(128),
	    FLOWID VARCHAR(64),
	    FLOWVERSIONID VARCHAR(64),
		NODEID VARCHAR(64),
		SEQUENTIAL INTEGER,
		PARTICIPANT VARCHAR(256),
		COUNTERSIGNTYPE VARCHAR(64),
		COUNTERSIGNRATE INTEGER,
        CONSTRAINT PK_BPM_NODE_COUNTERSIGN PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* 流程图配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_CONF_FLOW_CHART;
CREATE TABLE IB_CONF_FLOW_CHART (
	     ID               VARCHAR(64),
	     FLOWID    VARCHAR(64),  
	     ITEMID      VARCHAR(64), 
	     NODEID    VARCHAR(64), 
	     CONTEXT  VARCHAR(2000), 
        CONSTRAINT PK_IB_CONF_FLOW_CHART PRIMARY KEY(ID)
) ENGINE=INNODB;

/*==============================================================*/
/* 流程节点字段配置表   */
/*==============================================================*/
DROP TABLE IF EXISTS IB_BPM_NODE_COLUMS;
CREATE TABLE IB_BPM_NODE_COLUMS (
        ID                                       VARCHAR(64),  
		PACKAGENAME                VARCHAR(128),  
		FLOWID                            VARCHAR(64),  
		FORMNAME                      VARCHAR(128),  
		NODEID                             VARCHAR(64),  
		FORMCOLUMN                 VARCHAR(128),  
		FORMCOLUMNTITLE       VARCHAR(256),  
		TABLENAME                    VARCHAR(128),  
		TABLECOLUMN                VARCHAR(128),  
		FCDISPLAY                       VARCHAR(8),  
		FCEDIT                              VARCHAR(8),  
        CONSTRAINT PK_IB_BPM_NODE_COLUMS PRIMARY KEY(ID)
) ENGINE=INNODB;

