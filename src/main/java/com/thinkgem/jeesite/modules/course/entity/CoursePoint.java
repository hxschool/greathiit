/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程班级计数点Entity
 * @author 赵俊飞
 * @version 2018-12-25
 */
public class CoursePoint extends DataEntity<CoursePoint> {
	
	private static final long serialVersionUID = 1L;
	private String percentage;		// 百分比
	private String point;		// 计数点
	private Office office;		// 班级编号,默认公共课
	private Course course;		// 课程编码
	
	public CoursePoint() {
		super();
	}

	public CoursePoint(String id){
		super(id);
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	@NotNull(message="班级编号,默认公共课不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
}