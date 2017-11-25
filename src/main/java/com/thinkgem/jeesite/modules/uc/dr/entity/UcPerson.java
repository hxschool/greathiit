/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.dr.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学院基本信息Entity
 * @author 赵俊飞
 * @version 2017-11-25
 */
public class UcPerson extends DataEntity<UcPerson> {
	
	private static final long serialVersionUID = 1L;
	private String studentNumber;		// 学号
	private String height;		// 身高
	private String weight;		// 体重
	private String dormbuildId;		// 公寓编号
	private String dormbuildName;		// 公寓名称
	private String dormNumber;		// 寝室号
	private String location;		// 家庭地址
	private String mobile;		// 手机号
	private String wechat;		// 微信号
	private String qq;		// qq号
	private String health;		// 健康状态
	private String fatherName;		// 父亲姓名
	private String fatherMobile;		// 父亲联系方式
	private String motherName;		// 母亲姓名
	private String motherMobile;		// 母亲联系方式
	private String master;		// 班主任
	private String instructor;		// 导员
	
	public UcPerson() {
		super();
	}

	public UcPerson(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	@Length(min=0, max=18, message="身高长度必须介于 0 和 18 之间")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	@Length(min=0, max=18, message="体重长度必须介于 0 和 18 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=64, message="公寓编号长度必须介于 0 和 64 之间")
	public String getDormbuildId() {
		return dormbuildId;
	}

	public void setDormbuildId(String dormbuildId) {
		this.dormbuildId = dormbuildId;
	}
	
	@Length(min=0, max=64, message="公寓名称长度必须介于 0 和 64 之间")
	public String getDormbuildName() {
		return dormbuildName;
	}

	public void setDormbuildName(String dormbuildName) {
		this.dormbuildName = dormbuildName;
	}
	
	@Length(min=0, max=64, message="寝室号长度必须介于 0 和 64 之间")
	public String getDormNumber() {
		return dormNumber;
	}

	public void setDormNumber(String dormNumber) {
		this.dormNumber = dormNumber;
	}
	
	@Length(min=0, max=64, message="家庭地址长度必须介于 0 和 64 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=64, message="手机号长度必须介于 0 和 64 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=64, message="微信号长度必须介于 0 和 64 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	@Length(min=0, max=64, message="qq号长度必须介于 0 和 64 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=64, message="健康状态长度必须介于 0 和 64 之间")
	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}
	
	@Length(min=0, max=64, message="父亲姓名长度必须介于 0 和 64 之间")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	@Length(min=0, max=64, message="父亲联系方式长度必须介于 0 和 64 之间")
	public String getFatherMobile() {
		return fatherMobile;
	}

	public void setFatherMobile(String fatherMobile) {
		this.fatherMobile = fatherMobile;
	}
	
	@Length(min=0, max=64, message="母亲姓名长度必须介于 0 和 64 之间")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	@Length(min=0, max=64, message="母亲联系方式长度必须介于 0 和 64 之间")
	public String getMotherMobile() {
		return motherMobile;
	}

	public void setMotherMobile(String motherMobile) {
		this.motherMobile = motherMobile;
	}
	
	@Length(min=0, max=64, message="班主任长度必须介于 0 和 64 之间")
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	@Length(min=0, max=64, message="导员长度必须介于 0 和 64 之间")
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
}