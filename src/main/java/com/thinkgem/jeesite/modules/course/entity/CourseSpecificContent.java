/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程具体内容Entity
 * @author 赵俊飞
 * @version 2017-12-24
 */
public class CourseSpecificContent extends DataEntity<CourseSpecificContent> {
	
	private static final long serialVersionUID = 1L;
	private String cscBasRequ;		// 基本要求
	private String cscChapter;		// 章节名称
	private String cscClassHour;		// 学时
	private String cscGoal;		// 学习目的
	private String cscHomework;		// 课外作业及要求
	private String cscStudyDiffi;		// 学习难点
	private String cscStudyEmpha;		// 学习重点
	private String cscTarget;		// 教学目标
	private String courseId;		// 成绩编号
	
	public CourseSpecificContent() {
		super();
	}

	public CourseSpecificContent(String id){
		super(id);
	}

	@Length(min=0, max=255, message="基本要求长度必须介于 0 和 255 之间")
	public String getCscBasRequ() {
		return cscBasRequ;
	}

	public void setCscBasRequ(String cscBasRequ) {
		this.cscBasRequ = cscBasRequ;
	}
	
	@Length(min=0, max=255, message="章节名称长度必须介于 0 和 255 之间")
	public String getCscChapter() {
		return cscChapter;
	}

	public void setCscChapter(String cscChapter) {
		this.cscChapter = cscChapter;
	}
	
	@Length(min=0, max=255, message="学时长度必须介于 0 和 255 之间")
	public String getCscClassHour() {
		return cscClassHour;
	}

	public void setCscClassHour(String cscClassHour) {
		this.cscClassHour = cscClassHour;
	}
	
	@Length(min=0, max=255, message="学习目的长度必须介于 0 和 255 之间")
	public String getCscGoal() {
		return cscGoal;
	}

	public void setCscGoal(String cscGoal) {
		this.cscGoal = cscGoal;
	}
	
	@Length(min=0, max=255, message="课外作业及要求长度必须介于 0 和 255 之间")
	public String getCscHomework() {
		return cscHomework;
	}

	public void setCscHomework(String cscHomework) {
		this.cscHomework = cscHomework;
	}
	
	@Length(min=0, max=255, message="学习难点长度必须介于 0 和 255 之间")
	public String getCscStudyDiffi() {
		return cscStudyDiffi;
	}

	public void setCscStudyDiffi(String cscStudyDiffi) {
		this.cscStudyDiffi = cscStudyDiffi;
	}
	
	@Length(min=0, max=255, message="学习重点长度必须介于 0 和 255 之间")
	public String getCscStudyEmpha() {
		return cscStudyEmpha;
	}

	public void setCscStudyEmpha(String cscStudyEmpha) {
		this.cscStudyEmpha = cscStudyEmpha;
	}
	
	@Length(min=0, max=255, message="教学目标长度必须介于 0 和 255 之间")
	public String getCscTarget() {
		return cscTarget;
	}

	public void setCscTarget(String cscTarget) {
		this.cscTarget = cscTarget;
	}
	
	@Length(min=1, max=64, message="成绩编号长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
}