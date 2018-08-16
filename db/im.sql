drop table chat_friend;

CREATE TABLE chat_friend  ( 
    id 	varchar(64) NOT NULL,
    gid varchar(64) NOT NULL,
    uid	varchar(64) NOT NULL,
    fid	varchar(64) NOT NULL,
    create_by  	varchar(64) COMMENT '创建者'  NULL,
	create_date	datetime COMMENT '创建时间'  NULL,
	update_by  	varchar(64) COMMENT '更新者'  NULL,
	update_date	datetime COMMENT '更新时间'  NULL,
	remarks    	varchar(255) COMMENT '备注信息'  NULL,
	del_flag   	char(1) COMMENT '删除标记'  NOT NULL DEFAULT '0', 
    PRIMARY KEY(id)
)
;
drop table chat_group;
CREATE TABLE chat_group  ( 
    id       	varchar(64) NOT NULL,
    group_type  int NULL DEFAULT '0',
    online   	varchar(64) NULL,
    avatar   	varchar(200) NULL,
    groupname	varchar(200) NULL,
    create_by  	varchar(64) COMMENT '创建者'  NULL,
	create_date	datetime COMMENT '创建时间'  NULL,
	update_by  	varchar(64) COMMENT '更新者'  NULL,
	update_date	datetime COMMENT '更新时间'  NULL,
	remarks    	varchar(255) COMMENT '备注信息'  NULL,
	del_flag   	char(1) COMMENT '删除标记'  NOT NULL DEFAULT '0', 
    PRIMARY KEY(id)
)
;
drop table chat_msg_history;
CREATE TABLE chat_msg_history  ( 
    id          	int(4) AUTO_INCREMENT NOT NULL,
    gid         	varchar(64) not NULL,
    from_user    	varchar(64) NULL,
    to_user    	varchar(64) NULL,
    msg         	varchar(200) NULL,
    chat_type    	varchar(200) NULL,
    send_date 	varchar(200) NULL,
    msg_type     	varchar(200) NULL,
    message_date	varchar(200) NULL,
    create_by  	varchar(64) COMMENT '创建者'  NULL,
	create_date	datetime COMMENT '创建时间'  NULL,
	update_by  	varchar(64) COMMENT '更新者'  NULL,
	update_date	datetime COMMENT '更新时间'  NULL,
	remarks    	varchar(255) COMMENT '备注信息'  NULL,
	del_flag   	char(1) COMMENT '删除标记'  NOT NULL DEFAULT '0',
    PRIMARY KEY(id)
)
;
drop table chat_user;
CREATE TABLE chat_user  ( 
    id      	varchar(64) NOT NULL,
    online   	varchar(64) NULL,
    sign    	varchar(200) NULL,
    avatar  	varchar(200) NULL,
    username	varchar(200) NULL,
    create_by  	varchar(64) COMMENT '创建者'  NULL,
	create_date	datetime COMMENT '创建时间'  NULL,
	update_by  	varchar(64) COMMENT '更新者'  NULL,
	update_date	datetime COMMENT '更新时间'  NULL,
	remarks    	varchar(255) COMMENT '备注信息'  NULL,
	del_flag   	char(1) COMMENT '删除标记'  NOT NULL DEFAULT '0',
    PRIMARY KEY(id)
)
;
