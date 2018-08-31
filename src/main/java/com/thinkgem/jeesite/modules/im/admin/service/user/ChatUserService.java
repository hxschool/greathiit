/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.im.admin.dao.user.ChatUserDao;
import com.thinkgem.jeesite.modules.im.admin.entity.user.ChatUser;

/**
 * 个人资料Service
 * @author 赵俊飞
 * @version 2018-08-10
 */
@Service
@Transactional(readOnly = true)
public class ChatUserService extends CrudService<ChatUserDao, ChatUser> {

	public ChatUser get(String id) {
		return super.get(id);
	}
	
	public List<ChatUser> findList(ChatUser chatUser) {
		return super.findList(chatUser);
	}
	
	public Page<ChatUser> findPage(Page<ChatUser> page, ChatUser chatUser) {
		return super.findPage(page, chatUser);
	}
	
	@Transactional(readOnly = false)
	public void save(ChatUser chatUser) {
		super.save(chatUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(ChatUser chatUser) {
		super.delete(chatUser);
	}
	
}