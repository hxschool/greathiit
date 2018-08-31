/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.select.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 选课信息表Entity
 * @author 赵俊飞
 * @version 2018-07-31
 */
public class SelectCourse extends DataEntity<SelectCourse> {
	
	private static final long serialVersionUID = 1L;
	private Course course;		// 课程编号
	private User student;		// 学号

	
	public SelectCourse() {
		super();
	}

	public SelectCourse(String id){
		super(id);
	}

	
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	
}