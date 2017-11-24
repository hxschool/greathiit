/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公寓管理Entity
 * @author 赵俊飞
 * @version 2017-11-24
 */
public class UcDormBuild extends DataEntity<UcDormBuild> {
	
	private static final long serialVersionUID = 1L;
	private String dormBuildNo;		// 楼号
	private String dormBuildName;		// 名称
	private String dormBuildType;		// 类型
	private String dormBuildAddress;		// 地址
	private String dormBuildCnt;		// 总人数
	
	public UcDormBuild() {
		super();
	}

	public UcDormBuild(String id){
		super(id);
	}

	@Length(min=1, max=64, message="楼号长度必须介于 1 和 64 之间")
	public String getDormBuildNo() {
		return dormBuildNo;
	}

	public void setDormBuildNo(String dormBuildNo) {
		this.dormBuildNo = dormBuildNo;
	}
	
	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getDormBuildName() {
		return dormBuildName;
	}

	public void setDormBuildName(String dormBuildName) {
		this.dormBuildName = dormBuildName;
	}
	
	@Length(min=0, max=64, message="类型长度必须介于 0 和 64 之间")
	public String getDormBuildType() {
		return dormBuildType;
	}

	public void setDormBuildType(String dormBuildType) {
		this.dormBuildType = dormBuildType;
	}
	
	@Length(min=0, max=64, message="地址长度必须介于 0 和 64 之间")
	public String getDormBuildAddress() {
		return dormBuildAddress;
	}

	public void setDormBuildAddress(String dormBuildAddress) {
		this.dormBuildAddress = dormBuildAddress;
	}
	
	@Length(min=0, max=11, message="总人数长度必须介于 0 和 11 之间")
	public String getDormBuildCnt() {
		return dormBuildCnt;
	}

	public void setDormBuildCnt(String dormBuildCnt) {
		this.dormBuildCnt = dormBuildCnt;
	}
	
}