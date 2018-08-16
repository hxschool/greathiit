/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.entity.friend;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 我的好友Entity
 * @author 赵俊飞
 * @version 2018-08-10
 */
public class ChatFriend extends DataEntity<ChatFriend> {
	
	private static final long serialVersionUID = 1L;
	private String gid;		// uid
	private String uid;		// uid
	private String fid;		// fid
	private String master;		// fid
	public ChatFriend() {
		super();
	}

	public ChatFriend(String id){
		super(id);
	}


	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	@Length(min=1, max=64, message="uid长度必须介于 1 和 64 之间")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	@Length(min=1, max=64, message="fid长度必须介于 1 和 64 之间")
	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
}