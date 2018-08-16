/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.dao.user;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.im.admin.entity.user.ChatUser;

/**
 * 个人资料DAO接口
 * @author 赵俊飞
 * @version 2018-08-10
 */
@MyBatisDao
public interface ChatUserDao extends CrudDao<ChatUser> {
	
}