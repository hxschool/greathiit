/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.entity.msg;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 历史消息Entity
 * @author 赵俊飞
 * @version 2018-08-10
 */
public class ChatMsgHistory extends DataEntity<ChatMsgHistory> {
	
	private static final long serialVersionUID = 1L;
	private String gid;		// gid
	private String fromUser;		// from_user
	private String toUser;		// to_user
	private String msg;		// msg
	private String chatType;		// chat_type
	private String sendDate;		// send_date
	private String msgType;		// msg_type

	
	public ChatMsgHistory() {
		super();
	}

	public ChatMsgHistory(String id){
		super(id);
	}

	@Length(min=1, max=64, message="gid长度必须介于 1 和 64 之间")
	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}
	
	@Length(min=0, max=64, message="from_user长度必须介于 0 和 64 之间")
	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	
	@Length(min=0, max=64, message="to_user长度必须介于 0 和 64 之间")
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
	@Length(min=0, max=200, message="msg长度必须介于 0 和 200 之间")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}
	
	@Length(min=0, max=200, message="send_date长度必须介于 0 和 200 之间")
	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
	@Length(min=0, max=200, message="msg_type长度必须介于 0 和 200 之间")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	
}