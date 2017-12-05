/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 宿舍管理Entity
 * @author 赵俊飞
 * @version 2017-11-24
 */
public class UcDorm extends DataEntity<UcDorm> {
	
	private static final long serialVersionUID = 1L;
	
	private UcDormBuild ucDormBuild;
	
	private String dormNumber;		// 宿舍门牌号
	private String dormFloor;		// 楼层
	private String cnt;		// 总人数
	private String total;		// 总人数
	private String master;		// 寝室长
	
	public UcDorm() {
		super();
	}

	public UcDorm(String id){
		super(id);
	}

	public UcDormBuild getUcDormBuild() {
		return ucDormBuild;
	}

	public void setUcDormBuild(UcDormBuild ucDormBuild) {
		this.ucDormBuild = ucDormBuild;
	}

	@Length(min=0, max=64, message="宿舍门牌号长度必须介于 0 和 64 之间")
	public String getDormNumber() {
		return dormNumber;
	}

	public void setDormNumber(String dormNumber) {
		this.dormNumber = dormNumber;
	}
	
	@Length(min=0, max=64, message="楼层长度必须介于 0 和 64 之间")
	public String getDormFloor() {
		return dormFloor;
	}

	public void setDormFloor(String dormFloor) {
		this.dormFloor = dormFloor;
	}
	
	@Length(min=0, max=64, message="已入住人数长度必须介于 0 和 64 之间")
	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
	@Length(min=0, max=64, message="总人数长度必须介于 0 和 64 之间")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	@Length(min=0, max=64, message="寝室长长度必须介于 0 和 64 之间")
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
}