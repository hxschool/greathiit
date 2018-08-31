/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 宿舍管理Entity
 * @author 赵俊飞
 * @version 2017-11-24
 */
public class UcDormManager extends DataEntity<UcDormManager>{
	
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String dormbuildId;
	private String dormFloor;
	private String dormNumber;
	private String userId;
	private String studentNumber;
	private String name;
	private String phone;
	private String master;
	
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDormbuildId() {
		return dormbuildId;
	}
	public void setDormbuildId(String dormbuildId) {
		this.dormbuildId = dormbuildId;
	}
	public String getDormFloor() {
		return dormFloor;
	}
	public void setDormFloor(String dormFloor) {
		this.dormFloor = dormFloor;
	}
	public String getDormNumber() {
		return dormNumber;
	}
	public void setDormNumber(String dormNumber) {
		this.dormNumber = dormNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}