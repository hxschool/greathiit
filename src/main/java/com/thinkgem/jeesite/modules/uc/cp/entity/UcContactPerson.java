/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.cp.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 紧急联系人Entity
 * 
 * @author 赵俊飞
 * @version 2017-10-11
 */
public class UcContactPerson extends DataEntity<UcContactPerson> {

	private static final long serialVersionUID = 1L;
	private String studentNumber; // 学号
	private String contactType; // 联系类型
	private String contact; // 联系人
	private String mobile; // 联系电话

	private UcStudent ucStudent = new UcStudent();

	public UcStudent getUcStudent() {
		return ucStudent;
	}

	public void setUcStudent(UcStudent ucStudent) {
		this.ucStudent = ucStudent;
	}

	public UcContactPerson() {
		super();
	}

	public UcContactPerson(String id) {
		super(id);
	}


	@Length(min = 1, max = 64, message = "学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Length(min = 0, max = 2, message = "联系类型长度必须介于 0 和 2 之间")
	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	@Length(min = 1, max = 64, message = "联系人长度必须介于 1 和 64 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Length(min = 0, max = 64, message = "联系电话长度必须介于 0 和 64 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}