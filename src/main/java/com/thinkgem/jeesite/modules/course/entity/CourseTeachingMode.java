/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教学方式Entity
 * @author 赵俊飞
 * @version 2017-12-24
 */
public class CourseTeachingMode extends DataEntity<CourseTeachingMode> {
	
	private static final long serialVersionUID = 1L;
	private String cursContent;		// 课程内容
	private String period;		// 学时
	private String teacMethod;		// 教学方式
	private String courseId;		// course_id
	
	public CourseTeachingMode() {
		super();
	}

	public CourseTeachingMode(String id){
		super(id);
	}

	@Length(min=0, max=255, message="课程内容长度必须介于 0 和 255 之间")
	public String getCursContent() {
		return cursContent;
	}

	public void setCursContent(String cursContent) {
		this.cursContent = cursContent;
	}
	
	@Length(min=0, max=255, message="学时长度必须介于 0 和 255 之间")
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	@Length(min=0, max=255, message="教学方式长度必须介于 0 和 255 之间")
	public String getTeacMethod() {
		return teacMethod;
	}

	public void setTeacMethod(String teacMethod) {
		this.teacMethod = teacMethod;
	}
	
	@Length(min=1, max=64, message="course_id长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return teacMethod;
	}
	
}