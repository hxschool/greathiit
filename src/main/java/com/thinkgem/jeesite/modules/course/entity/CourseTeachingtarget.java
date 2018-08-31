/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设置课程考试与教学目标支撑分值Entity
 * @author 赵俊飞
 * @version 2017-12-24
 */
public class CourseTeachingtarget extends DataEntity<CourseTeachingtarget> {
	
	private static final long serialVersionUID = 1L;
	private String tchtargetContent;		// 教学目标
	private String tchtargetClassTargetValue;		// 课堂表现
	private String tchtargetHomeworkTargetValue;		// 平时作业
	private String tchtargetExpTargetValue;		// 实验成绩
	private String tchtargetMidTargetValue;		// 期中成绩
	private String tchtargetFinTargetValue;		// 期末成绩
	private String courseId;		// course_id
	
	public CourseTeachingtarget() {
		super();
	}

	public CourseTeachingtarget(String id){
		super(id);
	}

	@Length(min=1, max=200, message="教学目标长度必须介于 1 和 200 之间")
	public String getTchtargetContent() {
		return tchtargetContent;
	}

	public void setTchtargetContent(String tchtargetContent) {
		this.tchtargetContent = tchtargetContent;
	}
	
	public String getTchtargetClassTargetValue() {
		return tchtargetClassTargetValue;
	}

	public void setTchtargetClassTargetValue(String tchtargetClassTargetValue) {
		this.tchtargetClassTargetValue = tchtargetClassTargetValue;
	}
	
	public String getTchtargetHomeworkTargetValue() {
		return tchtargetHomeworkTargetValue;
	}

	public void setTchtargetHomeworkTargetValue(String tchtargetHomeworkTargetValue) {
		this.tchtargetHomeworkTargetValue = tchtargetHomeworkTargetValue;
	}
	
	public String getTchtargetExpTargetValue() {
		return tchtargetExpTargetValue;
	}

	public void setTchtargetExpTargetValue(String tchtargetExpTargetValue) {
		this.tchtargetExpTargetValue = tchtargetExpTargetValue;
	}
	
	public String getTchtargetMidTargetValue() {
		return tchtargetMidTargetValue;
	}

	public void setTchtargetMidTargetValue(String tchtargetMidTargetValue) {
		this.tchtargetMidTargetValue = tchtargetMidTargetValue;
	}
	
	public String getTchtargetFinTargetValue() {
		return tchtargetFinTargetValue;
	}

	public void setTchtargetFinTargetValue(String tchtargetFinTargetValue) {
		this.tchtargetFinTargetValue = tchtargetFinTargetValue;
	}
	
	@Length(min=1, max=64, message="course_id长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
}