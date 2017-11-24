/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 查寝记录Entity
 * @author 赵俊飞
 * @version 2017-11-24
 */
public class UcDormRecord extends DataEntity<UcDormRecord> {
	
	private static final long serialVersionUID = 1L;
	private String studentNumber;		// 学号
	private String username;		// 姓名
	private String dormBuildId;		// 楼号
	private String dormId;		// 寝室号
	private Date date;		// 日期
	private String detail;		// 详情
	
	public UcDormRecord() {
		super();
	}

	public UcDormRecord(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=64, message="楼号长度必须介于 0 和 64 之间")
	public String getDormBuildId() {
		return dormBuildId;
	}

	public void setDormBuildId(String dormBuildId) {
		this.dormBuildId = dormBuildId;
	}
	
	@Length(min=0, max=64, message="寝室号长度必须介于 0 和 64 之间")
	public String getDormId() {
		return dormId;
	}

	public void setDormId(String dormId) {
		this.dormId = dormId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Length(min=0, max=50, message="详情长度必须介于 0 和 50 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}