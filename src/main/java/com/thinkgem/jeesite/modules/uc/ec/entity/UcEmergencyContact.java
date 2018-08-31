/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.ec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 社交通讯录Entity
 * @author 社交通讯录
 * @version 2017-10-11
 */
public class UcEmergencyContact extends DataEntity<UcEmergencyContact> {
	
	private static final long serialVersionUID = 1L;
	
	private String studentNumber;		// 学号
	private String contactType;		// 类型
	private String contact;		// 信息
	private String showFlag;		// 是否显示
	
	private UcStudent ucStudent = new UcStudent();

	
	

	public UcStudent getUcStudent() {
		return ucStudent;
	}

	public void setUcStudent(UcStudent ucStudent) {
		this.ucStudent = ucStudent;
	}

	public UcEmergencyContact() {
		super();
	}

	public UcEmergencyContact(String id){
		super(id);
	}

	
	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	@Length(min=1, max=2, message="类型长度必须介于 1 和 2 之间")
	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	
	@Length(min=1, max=64, message="信息长度必须介于 1 和 64 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=1, message="是否显示长度必须介于 0 和 1 之间")
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	
}