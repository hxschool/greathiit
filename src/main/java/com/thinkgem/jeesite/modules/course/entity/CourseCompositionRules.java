/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程考试与教学目标支撑分值设置Entity
 * @author 赵俊飞
 * @version 2017-12-24
 */
public class CourseCompositionRules extends DataEntity<CourseCompositionRules> {
	
	private static final long serialVersionUID = 1L;
	private String clazzPer;		// 课堂表现
	private String expResultPer;		// 实验成绩
	private String finalExamper;		// 期末成绩
	private String homeworkResultPer;		// 平时作业
	private String midTermPer;		// 期中成绩
	private String courseId;		// course_id
	
	public CourseCompositionRules() {
		super();
	}

	public CourseCompositionRules(String id){
		super(id);
	}

	public String getClazzPer() {
		return clazzPer;
	}

	public void setClazzPer(String clazzPer) {
		this.clazzPer = clazzPer;
	}
	
	public String getExpResultPer() {
		return expResultPer;
	}

	public void setExpResultPer(String expResultPer) {
		this.expResultPer = expResultPer;
	}
	
	public String getFinalExamper() {
		return finalExamper;
	}

	public void setFinalExamper(String finalExamper) {
		this.finalExamper = finalExamper;
	}
	
	public String getHomeworkResultPer() {
		return homeworkResultPer;
	}

	public void setHomeworkResultPer(String homeworkResultPer) {
		this.homeworkResultPer = homeworkResultPer;
	}
	
	public String getMidTermPer() {
		return midTermPer;
	}

	public void setMidTermPer(String midTermPer) {
		this.midTermPer = midTermPer;
	}
	
	@Length(min=1, max=64, message="course_id长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
}