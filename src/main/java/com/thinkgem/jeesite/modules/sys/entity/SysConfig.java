/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 全局系统配置Entity
 * @author 赵俊飞
 * @version 2019-04-03
 */
public class SysConfig extends DataEntity<SysConfig> {
	
	private static final long serialVersionUID = 1L;
	private String module;		// 模块
	private String title;		// 标题
	private String termYear;		// 设置年份
	private Date startDate;		// 开始时间
	private Date endDate;		// 结束时间
	private String message;		// 提示信息
	private String description;		// 特别提示
	private String tip;		// 关闭提示
	private String status;		// 状态,默认为0,当状态为1的时候读取关闭提示
	
	public SysConfig() {
		super();
	}

	public SysConfig(String id){
		super(id);
	}

	@Length(min=1, max=64, message="模块长度必须介于 1 和 64 之间")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	@Length(min=1, max=64, message="标题长度必须介于 1 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=12, message="设置年份长度必须介于 1 和 12 之间")
	public String getTermYear() {
		return termYear;
	}

	public void setTermYear(String termYear) {
		this.termYear = termYear;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@Length(min=1, max=11, message="状态,默认为0,当状态为1的时候读取关闭提示长度必须介于 1 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}