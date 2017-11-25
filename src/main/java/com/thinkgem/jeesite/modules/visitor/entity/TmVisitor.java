/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.visitor.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 访客信息Entity
 * @author 赵俊飞
 * @version 2017-11-25
 */
public class TmVisitor extends DataEntity<TmVisitor> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 访客姓名
	private String sex;		// 访客性别
	private String mobile;		// 联系电话
	private String dromRoom;		// 拜访寝室
	private String reason;		// 访问事由
	
	public TmVisitor() {
		super();
	}

	public TmVisitor(String id){
		super(id);
	}

	@Length(min=1, max=64, message="访客姓名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="访客性别长度必须介于 1 和 64 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=1, max=64, message="联系电话长度必须介于 1 和 64 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=64, message="拜访寝室长度必须介于 1 和 64 之间")
	public String getDromRoom() {
		return dromRoom;
	}

	public void setDromRoom(String dromRoom) {
		this.dromRoom = dromRoom;
	}
	
	@Length(min=0, max=200, message="访问事由长度必须介于 0 和 200 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}