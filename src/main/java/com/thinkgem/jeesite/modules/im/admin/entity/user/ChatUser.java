/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.entity.user;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 个人资料Entity
 * @author 赵俊飞
 * @version 2018-08-10
 */
public class ChatUser extends DataEntity<ChatUser> {
	
	private static final long serialVersionUID = 1L;
	private String online;		// online
	private String sign;		// sign
	private String avatar;		// avatar
	private String username;		// username
	
	public ChatUser() {
		super();
	}

	public ChatUser(String id){
		super(id);
	}

	@Length(min=0, max=64, message="online长度必须介于 0 和 64 之间")
	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}
	
	@Length(min=0, max=200, message="sign长度必须介于 0 和 200 之间")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@Length(min=0, max=200, message="avatar长度必须介于 0 和 200 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Length(min=0, max=200, message="username长度必须介于 0 和 200 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}