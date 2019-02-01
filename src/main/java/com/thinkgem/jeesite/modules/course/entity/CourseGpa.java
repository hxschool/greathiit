/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * gpaEntity
 * @author 赵俊飞
 * @version 2019-02-01
 */
public class CourseGpa extends DataEntity<CourseGpa> {
	
	private static final long serialVersionUID = 1L;
	private String groupid;		// 分组标识
	private String groupname;		// 分组
	private String score;		// 成绩
	private String credit;		// 学分
	
	public CourseGpa() {
		super();
	}

	public CourseGpa(String id){
		super(id);
	}

	@Length(min=0, max=20, message="分组标识长度必须介于 0 和 20 之间")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	@Length(min=1, max=64, message="分组长度必须介于 1 和 64 之间")
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	@Length(min=1, max=64, message="成绩长度必须介于 1 和 64 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=1, max=64, message="学分长度必须介于 1 和 64 之间")
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
}