/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 招生计划Entity
 * @author 赵俊飞
 * @version 2018-01-05
 */
public class RsZsjh extends DataEntity<RsZsjh> {
	
	private static final long serialVersionUID = 1L;
	private String majorType;		// 专业类型
	private String majorCount;		// 专业人数
	private String zy1;		// 扩展1
	private String zy2;		// 扩展2
	private String zy3;		// 扩展3
	private String zy4;		// 扩展4
	private String zy5;		// 扩展5
	private String zy6;		// 扩展6
	private String status;		// 状态
	
	public RsZsjh() {
		super();
	}

	public RsZsjh(String id){
		super(id);
	}

	@Length(min=0, max=64, message="专业类型长度必须介于 0 和 64 之间")
	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}
	
	@Length(min=0, max=64, message="专业人数长度必须介于 0 和 64 之间")
	public String getMajorCount() {
		return majorCount;
	}

	public void setMajorCount(String majorCount) {
		this.majorCount = majorCount;
	}
	
	@Length(min=0, max=64, message="扩展1长度必须介于 0 和 64 之间")
	public String getZy1() {
		return zy1;
	}

	public void setZy1(String zy1) {
		this.zy1 = zy1;
	}
	
	@Length(min=0, max=64, message="扩展2长度必须介于 0 和 64 之间")
	public String getZy2() {
		return zy2;
	}

	public void setZy2(String zy2) {
		this.zy2 = zy2;
	}
	
	@Length(min=0, max=64, message="扩展3长度必须介于 0 和 64 之间")
	public String getZy3() {
		return zy3;
	}

	public void setZy3(String zy3) {
		this.zy3 = zy3;
	}
	
	@Length(min=0, max=64, message="扩展4长度必须介于 0 和 64 之间")
	public String getZy4() {
		return zy4;
	}

	public void setZy4(String zy4) {
		this.zy4 = zy4;
	}
	
	@Length(min=0, max=64, message="扩展5长度必须介于 0 和 64 之间")
	public String getZy5() {
		return zy5;
	}

	public void setZy5(String zy5) {
		this.zy5 = zy5;
	}
	
	@Length(min=0, max=64, message="扩展6长度必须介于 0 和 64 之间")
	public String getZy6() {
		return zy6;
	}

	public void setZy6(String zy6) {
		this.zy6 = zy6;
	}
	
	@Length(min=0, max=4, message="状态长度必须介于 0 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}