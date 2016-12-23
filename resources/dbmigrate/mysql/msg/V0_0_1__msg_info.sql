/*=============================================================================================*/
/* msg info */
/*=============================================================================================*/
DROP TABLE IF EXISTS IB_MSG_INFO;
CREATE TABLE IB_MSG_INFO(
        ID BIGINT auto_increment,
	NAME VARCHAR(200),
	CONTENT VARCHAR(200),
	TYPE INTEGER,
	CREATE_TIME TIMESTAMP,
	SENDER_USERNAME VARCHAR(200),
	RECEIVER_USERNAME VARCHAR(200),
	STATUS INTEGER,
        CONSTRAINT PK_MSG_INFO PRIMARY KEY(ID)
) engine=innodb;
