/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 宿舍管理Entity
 * @author 赵俊飞
 * @version 2017-11-24
 */
public class UcDorm extends DataEntity<UcDorm> implements Comparable<UcDorm>{
	
	private static final long serialVersionUID = 1L;
	
	private UcDormBuild ucDormBuild;
	
	private String dormNumber;		// 宿舍门牌号
	private String dormFloor;		// 楼层
	private String cnt;		// 总人数
	private String total;		// 总人数
	private String master;		// 寝室长
	private String a;
	private String b;
	private String c;
	private String d;
	
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
	
	
	
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	@Override  
    public int compareTo(UcDorm o) {
        return Integer.valueOf(this.total)-Integer.valueOf(this.cnt);
    } 
	
}