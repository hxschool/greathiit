/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程班级Entity
 * @author 赵俊飞
 * @version 2019-06-17
 */
public class CourseClass extends DataEntity<CourseClass> {
	
	private static final long serialVersionUID = 1L;
	private String courseId;		// course_id
	private String courseClass;		// course_class
	private String tips;		// tips
	
	public CourseClass() {
		super();
	}

	public CourseClass(String id){
		super(id);
	}

	@Length(min=1, max=64, message="course_id长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@Length(min=1, max=20, message="course_class长度必须介于 1 和 20 之间")
	public String getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}
	
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
}