package com.thinkgem.jeesite.modules.student.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class StudentCourseExt extends DataEntity<StudentCourse>  {
	
	private Office company;	// 学院
	private Office office;	// 专业
	private Office clazz;	// 班级
	private String classEvaValue;		// 平时成绩
	private String evaValue;		// 综合成绩
	private String finEvaValue;		// 期末成绩
	private String credit;			// 学分
	private String point;			//计数点
	private String termYear;		// 年份
	private Course course;		//课程名称
	private Student student;		// 学号
	private String status;		// 状态标记

	@ExcelField(title="学院", type=1,align=2, sort=1)
	public Office getCompany() {
		return company;
	}
	public void setCompany(Office company) {
		this.company = company;
	}
	@ExcelField(title="专业",type=1, align=2, sort=2)
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	@ExcelField(title="班级",type=1, align=2, sort=3)
	public Office getClazz() {
		return clazz;
	}
	public void setClazz(Office clazz) {
		this.clazz = clazz;
	}
	
	@ExcelField(title="学号", type=1, align=2, sort=4)
	public String getStudentNumber() {
		return student.getStudentNumber();
	}

	
	@ExcelField(title="姓名", type=1, align=2, sort=5)
	public String getStudentName() {
		return student.getName();
	}
	@ExcelField(title="综合成绩", type=1, align=2, sort=8)
	public String getEvaValue() {
		return evaValue;
	}
	public void setEvaValue(String evaValue) {
		this.evaValue = evaValue;
	}
	public String getFinEvaValue() {
		return finEvaValue;
	}
	public void setFinEvaValue(String finEvaValue) {
		this.finEvaValue = finEvaValue;
	}
	@ExcelField(title="学分", type=1, align=2, sort=9)
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	@ExcelField(title="学期", type=1, align=2, sort=6)
	public String getTermYear() {
		return termYear;
	}
	
	@ExcelField(title="课程", type=1, align=2, sort=7)
	public String getCursName() {
		return course.getCursName();
	}
	
	@ExcelField(title="课程类型", type=1, align=2, sort=8,dictType="course_curs_type")
	public String getCursType() {
		return course.getCursType();
	}
	
	public void setTermYear(String termYear) {
		this.termYear = termYear;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@ExcelField(title="授课教师", type=1, align=2, sort=12)
	public String getTchrName() {
		return course.getTeacher().getTchrName();
	}
	
	@ExcelField(title="评分教师", type=1, align=2, sort=13)
	public String getRater() {
		return course.getRater();
	}
	
	@ExcelField(title="录入人", type=1, align=2, sort=14)
	public String getSubmitter() {
		return course.getTeacher().getTchrName();
	}
	
	@ExcelField(title="考核状态", type=1, align=2, sort=11,dictType="student_course_result")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ExcelField(title="绩点", type=1, align=2, sort=10)
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getClassEvaValue() {
		return classEvaValue;
	}
	public void setClassEvaValue(String classEvaValue) {
		this.classEvaValue = classEvaValue;
	}
	
	
}
