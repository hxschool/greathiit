/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 答辩Entity
 * @author 赵俊飞
 * @version 2018-08-17
 */
public class AsAnsweringStudent extends DataEntity<AsAnsweringStudent> {
	
	private static final long serialVersionUID = 1L;
	private String asAnsweringId;		// 答辩抽签编码
	private String studentNumber;		// 学号
	private String username;		// 姓名
	private String status;		// 姓名
	private String description;		// 描述、摘要

	
	public AsAnsweringStudent() {
		super();
	}

	public AsAnsweringStudent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="答辩抽签编码长度必须介于 1 和 64 之间")
	public String getAsAnsweringId() {
		return asAnsweringId;
	}

	public void setAsAnsweringId(String asAnsweringId) {
		this.asAnsweringId = asAnsweringId;
	}
	@ExcelField(title="学号", align=2, sort=1)
	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	@ExcelField(title="姓名", align=2, sort=2)
	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="描述、摘要长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}