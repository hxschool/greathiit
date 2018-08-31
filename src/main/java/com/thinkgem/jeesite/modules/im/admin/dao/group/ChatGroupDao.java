/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.dao.group;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.im.admin.entity.group.ChatGroup;

/**
 * 好友分组DAO接口
 * @author 赵俊飞
 * @version 2018-08-13
 */
@MyBatisDao
public interface ChatGroupDao extends CrudDao<ChatGroup> {
	List<ChatGroup> myGroupByUid(@Param("groupType") String groupType,@Param("uid") String uid);
}