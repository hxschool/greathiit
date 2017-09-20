/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学生基本信息Entity
 * @author 赵俊飞
 * @version 2017-09-19
 */
public class UcStudent extends DataEntity<UcStudent> {
	
	private static final long serialVersionUID = 1L;
	private String exaNumber;		// 考试号
	private String number;		// 学号
	private String username;		// 真实姓名
	private String idCard;		// 身份证号
	private String gender;		// 性别
	private String birthday;		// 生日
	private String department;		// 院系
	private String major;		// 专业
	private String classNumber;		// 班号
	private String edu;		// 学历
	private String code;		// 行政区编码
	private String region;		// 行政区
	
	public UcStudent() {
		super();
	}

	public UcStudent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="考试号长度必须介于 1 和 64 之间")
	public String getExaNumber() {
		return exaNumber;
	}

	public void setExaNumber(String exaNumber) {
		this.exaNumber = exaNumber;
	}
	
	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=1, max=64, message="真实姓名长度必须介于 1 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=18, message="身份证号长度必须介于 1 和 18 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=1, max=1, message="性别长度必须介于 1 和 1 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=1, max=11, message="生日长度必须介于 1 和 11 之间")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=1, max=64, message="院系长度必须介于 1 和 64 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=1, max=64, message="专业长度必须介于 1 和 64 之间")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Length(min=1, max=64, message="班号长度必须介于 1 和 64 之间")
	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	
	@Length(min=0, max=64, message="学历长度必须介于 0 和 64 之间")
	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}
	
	@Length(min=0, max=64, message="行政区编码长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=64, message="行政区长度必须介于 0 和 64 之间")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
}