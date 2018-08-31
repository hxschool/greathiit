/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 参与活动Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class StudentActivity extends DataEntity<StudentActivity> {
	
	private static final long serialVersionUID = 1L;
	private String actName;		// 活动名称
	private Date actTime;		// 活动日期
	private String actDuty;		// 职责描述
	private String actState;		// 状态
	private String actType;		// 活动类型
	private String actUnit;		// 主办单位
	private User student;		// 学号
	
	public StudentActivity() {
		super();
	}

	public StudentActivity(String id){
		super(id);
	}

	@Length(min=0, max=255, message="活动名称长度必须介于 0 和 255 之间")
	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
	
	@Length(min=0, max=255, message="职责描述长度必须介于 0 和 255 之间")
	public String getActDuty() {
		return actDuty;
	}

	public void setActDuty(String actDuty) {
		this.actDuty = actDuty;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getActState() {
		return actState;
	}

	public void setActState(String actState) {
		this.actState = actState;
	}
	
	@Length(min=0, max=64, message="活动类型长度必须介于 0 和 64 之间")
	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}
	
	@Length(min=0, max=255, message="主办单位长度必须介于 0 和 255 之间")
	public String getActUnit() {
		return actUnit;
	}

	public void setActUnit(String actUnit) {
		this.actUnit = actUnit;
	}
	
	@NotNull(message="学号不能为空")
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}
	
}