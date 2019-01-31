/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.config.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 系统配置Entity
 * @author 赵俊飞
 * @version 2019-01-31
 */
public class RsStudentConfig extends DataEntity<RsStudentConfig> {
	
	private static final long serialVersionUID = 1L;
	private String yearTerm;		// 学期
	private String sw;		// sw
	private String scoreSw;		// sw
	private String tip;		// 关闭提示
	private String description;		// 特别提示
	
	public RsStudentConfig() {
		super();
	}

	public RsStudentConfig(String id){
		super(id);
	}

	@Length(min=0, max=20, message="学期长度必须介于 0 和 20 之间")
	public String getYearTerm() {
		return yearTerm;
	}

	public void setYearTerm(String yearTerm) {
		this.yearTerm = yearTerm;
	}
	
	@Length(min=0, max=10, message="sw长度必须介于 0 和 10 之间")
	public String getSw() {
		return sw;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScoreSw() {
		return scoreSw;
	}

	public void setScoreSw(String scoreSw) {
		this.scoreSw = scoreSw;
	}
	
}