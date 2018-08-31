/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.entity.group;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 好友分组Entity
 * @author 赵俊飞
 * @version 2018-08-13
 */
public class ChatGroup extends DataEntity<ChatGroup> {
	
	private static final long serialVersionUID = 1L;
	private String groupType;		// group_type
	private String online;		// online
	private String avatar;		// avatar
	private String groupname;		// groupname
	
	public ChatGroup() {
		super();
	}

	public ChatGroup(String id){
		super(id);
	}

	@Length(min=0, max=11, message="group_type长度必须介于 0 和 11 之间")
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	@Length(min=0, max=64, message="online长度必须介于 0 和 64 之间")
	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}
	
	@Length(min=0, max=200, message="avatar长度必须介于 0 和 200 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Length(min=0, max=200, message="groupname长度必须介于 0 和 200 之间")
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
}