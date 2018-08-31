/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学生成绩Entity
 * @author 赵俊飞
 * @version 2018-01-30
 */
public class StudentCourse extends DataEntity<StudentCourse> {
	
	private static final long serialVersionUID = 1L;
	private String classEvaValue;		// 课堂表现成绩
	private String evaValue;		// 综合成绩
	private String expEvaValue;		// 实验成绩
	private String finEvaValue;		// 期末成绩
	private String midEvaValue;		// 期中成绩
	private String schoolYear;		// school_year
	private String workEvaValue;		// 作业成绩
	private String courseId;		// 课程号
	private String studentNumber;		// 学号
	private String status;		// 状态标记
	private String studentName;
	private String cursType;
	public StudentCourse() {
		super();
	}

	public StudentCourse(String id){
		super(id);
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCursType() {
		return cursType;
	}

	public void setCursType(String cursType) {
		this.cursType = cursType;
	}

	public String getClassEvaValue() {
		return classEvaValue;
	}

	public void setClassEvaValue(String classEvaValue) {
		this.classEvaValue = classEvaValue;
	}
	
	public String getEvaValue() {
		return evaValue;
	}

	public void setEvaValue(String evaValue) {
		this.evaValue = evaValue;
	}
	
	public String getExpEvaValue() {
		return expEvaValue;
	}

	public void setExpEvaValue(String expEvaValue) {
		this.expEvaValue = expEvaValue;
	}
	
	public String getFinEvaValue() {
		return finEvaValue;
	}

	public void setFinEvaValue(String finEvaValue) {
		this.finEvaValue = finEvaValue;
	}
	
	public String getMidEvaValue() {
		return midEvaValue;
	}

	public void setMidEvaValue(String midEvaValue) {
		this.midEvaValue = midEvaValue;
	}
	
	@Length(min=0, max=255, message="school_year长度必须介于 0 和 255 之间")
	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	public String getWorkEvaValue() {
		return workEvaValue;
	}

	public void setWorkEvaValue(String workEvaValue) {
		this.workEvaValue = workEvaValue;
	}
	
	@Length(min=1, max=64, message="课程号长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	@Length(min=0, max=1, message="状态标记长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}