/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wechat.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 绑定成功Entity
 * @author 赵俊飞
 * @version 2018-09-25
 */
public class WeixinUser extends DataEntity<WeixinUser> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// OPENID
	private String userNo;		// 学号或教师号
	
	public WeixinUser() {
		super();
	}

	public WeixinUser(String id){
		super(id);
	}

	@Length(min=1, max=30, message="OPENID长度必须介于 1 和 30 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=1, max=64, message="学号或教师号长度必须介于 1 和 64 之间")
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
}