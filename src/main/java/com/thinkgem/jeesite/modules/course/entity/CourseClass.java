/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程班级Entity
 * @author 赵俊飞
 * @version 2019-06-17
 */
public class CourseClass extends DataEntity<CourseClass> {
	
	private static final long serialVersionUID = 1L;
	private Course course;		// course_id
	private String classId;
	private String className;	// course_class
	private String tips;		// tips
	
	public CourseClass() {
		super();
	}

	public CourseClass(String id){
		super(id);
	}

	@JsonBackReference
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
	

	public synchronized String getClassId() {
		return classId;
	}

	public synchronized void setClassId(String classId) {
		this.classId = classId;
	}

	public synchronized String getClassName() {
		return className;
	}

	public synchronized void setClassName(String className) {
		this.className = className;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
}