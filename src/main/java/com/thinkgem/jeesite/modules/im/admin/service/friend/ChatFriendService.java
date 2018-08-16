/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.service.friend;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.im.admin.dao.friend.ChatFriendDao;
import com.thinkgem.jeesite.modules.im.admin.entity.friend.ChatFriend;

/**
 * 我的好友Service
 * @author 赵俊飞
 * @version 2018-08-10
 */
@Service
@Transactional(readOnly = true)
public class ChatFriendService extends CrudService<ChatFriendDao, ChatFriend> {

	public ChatFriend get(String id) {
		return super.get(id);
	}
	
	public List<ChatFriend> findList(ChatFriend chatFriend) {
		return super.findList(chatFriend);
	}
	
	public Page<ChatFriend> findPage(Page<ChatFriend> page, ChatFriend chatFriend) {
		return super.findPage(page, chatFriend);
	}
	
	@Transactional(readOnly = false)
	public void save(ChatFriend chatFriend) {
		super.save(chatFriend);
	}
	
	@Transactional(readOnly = false)
	public void delete(ChatFriend chatFriend) {
		super.delete(chatFriend);
	}
	
}