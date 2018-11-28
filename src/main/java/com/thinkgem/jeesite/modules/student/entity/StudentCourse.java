/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;

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
	private String termYear;		// termYear
	private String workEvaValue;		// 作业成绩
	
	private Course course;		//课程名称
	private Student student;		// 学号
	private Teacher teacher;		// 学号
	private String status;		// 状态标记
	private String clazzId;
	private String cursType;
	public StudentCourse() {
		super();
	}

	public StudentCourse(String id){
		super(id);
	}

	public String getCursType() {
		return cursType;
	}

	public void setCursType(String cursType) {
		this.cursType = cursType;
	}
	@ExcelField(title="课堂表现成绩", type=0, align=2, sort=1)
	public String getClassEvaValue() {
		return classEvaValue;
	}

	public void setClassEvaValue(String classEvaValue) {
		this.classEvaValue = classEvaValue;
	}
	
	@ExcelField(title="学号", type=0, align=2, sort=7)
	public String getStudentNumber() {
		return student.getStudentNumber();
	}
	
	public void setStudentNumber(String studentNumber) {
		student = new Student();
		student.setStudentNumber(studentNumber);
	}
	
	@ExcelField(title="姓名", type=1, align=2, sort=8)
	public String getStudentName() {
		return student.getName();
	}

	
	@ExcelField(title="综合成绩", type=0, align=2, sort=6)
	public String getEvaValue() {
		return evaValue;
	}

	public void setEvaValue(String evaValue) {
		this.evaValue = evaValue;
	}
	@ExcelField(title="实验成绩", type=0, align=2, sort=5)
	public String getExpEvaValue() {
		return expEvaValue;
	}

	public void setExpEvaValue(String expEvaValue) {
		this.expEvaValue = expEvaValue;
	}
	@ExcelField(title="期末成绩", type=0, align=2, sort=4)
	public String getFinEvaValue() {
		return finEvaValue;
	}

	public void setFinEvaValue(String finEvaValue) {
		this.finEvaValue = finEvaValue;
	}
	
	@ExcelField(title="期中成绩", type=0, align=2, sort=3)
	public String getMidEvaValue() {
		return midEvaValue;
	}

	public void setMidEvaValue(String midEvaValue) {
		this.midEvaValue = midEvaValue;
	}
	
	@Length(min=0, max=255, message="school_year长度必须介于 0 和 255 之间")
	public String getTermYear() {
		return termYear;
	}

	public void setTermYear(String termYear) {
		this.termYear = termYear;
	}
	@ExcelField(title="作业成绩", type=0, align=2, sort=2)
	public String getWorkEvaValue() {
		return workEvaValue;
	}

	public void setWorkEvaValue(String workEvaValue) {
		this.workEvaValue = workEvaValue;
	}
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Length(min=0, max=1, message="状态标记长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getClazzId() {
		return clazzId;
	}

	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}
	
}