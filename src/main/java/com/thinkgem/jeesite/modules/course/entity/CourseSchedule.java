/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计划教室Entity
 * @author 赵俊飞
 * @version 2017-12-15
 */
public class CourseSchedule extends DataEntity<CourseSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String timeAdd;		// 前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几
	private String courseId;		// course_id
	private String courseClass;		// 7个解析一个班级
	private String scLock;		// 0表示管理员加的课,1表示可排课,2表示已排课
	private String tips;		// tips
	private List<String> schoolRoots;

	public List<String> getSchoolRoots() {
		return schoolRoots;
	}

	public void setSchoolRoots(List<String> schoolRoots) {
		this.schoolRoots = schoolRoots;
	}

	public CourseSchedule() {
		super();
	}

	public CourseSchedule(String id){
		super(id);
	}

	@Length(min=1, max=14, message="前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几长度必须介于 1 和 14 之间")
	public String getTimeAdd() {
		return timeAdd;
	}

	public void setTimeAdd(String timeAdd) {
		this.timeAdd = timeAdd;
	}
	
	@Length(min=1, max=64, message="course_id长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@Length(min=1, max=2000, message="7个解析一个班级长度必须介于 1 和 2000之间")
	public String getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}
	
	@Length(min=1, max=1, message="0表示管理员加的课,1表示可排课,2表示已排课长度必须介于 1 和 1 之间")
	public String getScLock() {
		return scLock;
	}

	public void setScLock(String scLock) {
		this.scLock = scLock;
	}
	
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
}