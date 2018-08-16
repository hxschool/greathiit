package com.thinkgem.jeesite.modules.im.websocket.data;

import java.util.List;

public class SNSFriend {
	private String groupname;
	private String id;
	private String online;
	private List<SNSUser> list;
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public List<SNSUser> getList() {
		return list;
	}
	public void setList(List<SNSUser> list) {
		this.list = list;
	}
	
}