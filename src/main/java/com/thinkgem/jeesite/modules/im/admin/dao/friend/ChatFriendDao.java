/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.dao.friend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.im.admin.entity.friend.ChatFriend;

/**
 * 我的好友DAO接口
 * @author 赵俊飞
 * @version 2018-08-10
 */
@MyBatisDao
public interface ChatFriendDao extends CrudDao<ChatFriend> {
	public List<ChatFriend> getChatFriendByUserid(@Param("uid")String uid);
}