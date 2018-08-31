/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考勤信息Entity
 * @author 赵俊飞
 * @version 2018-03-22
 */
public class WorkConfigDate extends DataEntity<WorkConfigDate> {
	
	private static final long serialVersionUID = 1L;
	private String configDate;		// 考勤日期
	
	public WorkConfigDate() {
		super();
	}

	public WorkConfigDate(String id){
		super(id);
	}

	@Length(min=1, max=255, message="考勤日期长度必须介于 1 和 255 之间")
	public String getConfigDate() {
		return configDate;
	}

	public void setConfigDate(String configDate) {
		this.configDate = configDate;
	}
	
}