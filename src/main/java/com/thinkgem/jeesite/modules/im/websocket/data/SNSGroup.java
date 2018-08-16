package com.thinkgem.jeesite.modules.im.websocket.data;

public class SNSGroup {
	
	private String id;//群组ID
	private String groupname; //群组名
	private String avatar; //群组头像
	
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}