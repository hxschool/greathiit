/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.dao.msg;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.im.admin.entity.msg.ChatMsgHistory;

/**
 * 历史消息DAO接口
 * @author 赵俊飞
 * @version 2018-08-10
 */
@MyBatisDao
public interface ChatMsgHistoryDao extends CrudDao<ChatMsgHistory> {
	
}