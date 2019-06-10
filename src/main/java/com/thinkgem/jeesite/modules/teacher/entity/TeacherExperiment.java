/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教师履历Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class TeacherExperiment extends DataEntity<TeacherExperiment> {
	
	private static final long serialVersionUID = 1L;
	private String tchrPosition;		// 学位/职称
	private String tchrSchool;		// 学校/单位
	private String tchrTime;		// 时间
	private User user;		// 教师号
	
	public TeacherExperiment() {
		super();
	}

	public TeacherExperiment(String id){
		super(id);
	}

	@Length(min=1, max=255, message="学位/职称长度必须介于 1 和 255 之间")
	public String getTchrPosition() {
		return tchrPosition;
	}

	public void setTchrPosition(String tchrPosition) {
		this.tchrPosition = tchrPosition;
	}
	
	@Length(min=1, max=255, message="学校/单位长度必须介于 1 和 255 之间")
	public String getTchrSchool() {
		return tchrSchool;
	}

	public void setTchrSchool(String tchrSchool) {
		this.tchrSchool = tchrSchool;
	}
	
	@Length(min=1, max=255, message="时间长度必须介于 1 和 255 之间")
	public String getTchrTime() {
		return tchrTime;
	}

	public void setTchrTime(String tchrTime) {
		this.tchrTime = tchrTime;
	}
	
	@NotNull(message="教师号不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}