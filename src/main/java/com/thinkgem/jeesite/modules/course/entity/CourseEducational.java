/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教务课程信息Entity
 * @author 赵俊飞
 * @version 2018-11-22
 */
public class CourseEducational extends DataEntity<CourseEducational> {
	
	private static final long serialVersionUID = 1L;
	private String cursNum;		// 课程编码
	private String cursName;		// 课程名称
	
	public CourseEducational() {
		super();
	}

	public CourseEducational(String id){
		super(id);
	}

	@Length(min=1, max=255, message="课程编码长度必须介于 1 和 255 之间")
	public String getCursNum() {
		return cursNum;
	}

	public void setCursNum(String cursNum) {
		this.cursNum = cursNum;
	}
	
	@Length(min=1, max=255, message="课程名称长度必须介于 1 和 255 之间")
	public String getCursName() {
		return cursName;
	}

	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	
}