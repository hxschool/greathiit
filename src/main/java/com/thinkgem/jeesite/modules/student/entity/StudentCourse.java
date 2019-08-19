/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.course.entity.Course;

/**
 * 学生成绩Entity
 * @author 赵俊飞
 * @version 2018-01-30
 */
public class StudentCourse extends DataEntity<StudentCourse> implements Comparable<StudentCourse> {
	
	private static final long serialVersionUID = 1L;
	private String classEvaValue;		// 平时成绩
	private String evaValue;		// 综合成绩
	private String expEvaValue;		// 实验成绩
	private String finEvaValue;		// 期末成绩
	private String midEvaValue;		// 期中成绩
	private String credit;			// 学分
	private String point;			//计数点
	private String termYear;		// 年份
	private String workEvaValue;		// 作业成绩
	private Course course;		//课程名称

	private Student student;		// 学号
	private String status;		// 状态标记
	
	private List<String> item;
	
	private String clazzId;
	private String cursType;

	public List<String> getItem() {
		return item;
	}

	public void setItem(List<String> item) {
		this.item = item;
	}

	//@ExcelField(title="学分", type=0, align=2, sort=6)
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

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
	@ExcelField(title="平时成绩", type=0, align=2, sort=2)
	public String getClassEvaValue() {
		return classEvaValue;
	}

	public void setClassEvaValue(String classEvaValue) {
		this.classEvaValue = classEvaValue;
	}
	
	@ExcelField(title="学号", type=0, align=2, sort=0)
	public String getStudentNumber() {
		if(student!=null) {
			return student.getStudentNumber();
		}
		return null;
	}
	
	public void setStudentNumber(String studentNumber) {
		student = new Student();
		student.setStudentNumber(studentNumber);
	}
	
	@ExcelField(title="姓名", type=0, align=2, sort=1)
	public String getStudentName() {
		return student.getName();
	}

	
	public void setStudentName(String name) {
			student.setName(name);
	}
	
	//@ExcelField(title="综合成绩", type=0, align=2, sort=5)
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
	@ExcelField(title="期末成绩", type=0, align=2, sort=3)
	public String getFinEvaValue() {
		return finEvaValue;
	}

	public void setFinEvaValue(String finEvaValue) {
		this.finEvaValue = finEvaValue;
	}
	
	//@ExcelField(title="期中成绩", type=0, align=2, sort=3)
	public String getMidEvaValue() {
		return midEvaValue;
	}

	public void setMidEvaValue(String midEvaValue) {
		this.midEvaValue = midEvaValue;
	}
	
	@Length(min=0, max=255, message="学期不允许为空,且长度大于0小于255")
	public String getTermYear() {
		return termYear;
	}

	public void setTermYear(String termYear) {
		this.termYear = termYear;
	}
	
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

	@Length(min=0, max=1, message="考核状态")
	@ExcelField(title="考核状态", type=0, align=2, sort=4,dictType="student_course_result")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClazzId() {
		return clazzId;
	}

	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}

	@ExcelField(title="备注", type=0, align=2, sort=5)
	public String getRemarks() {
		return super.getRemarks();
	}
	//@ExcelField(title="绩点", type=0, align=2, sort=7)
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	@Override
	public int compareTo(StudentCourse studentCourse) {
		BigInteger a = new BigInteger(student.getStudentNumber());
		BigInteger b = new BigInteger(studentCourse.getStudent().getStudentNumber());
		return a.compareTo(b);
	}
}