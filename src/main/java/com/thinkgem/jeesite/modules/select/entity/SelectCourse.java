/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.select.entity;

import java.math.BigInteger;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;

/**
 * 选课信息表Entity
 * @author 赵俊飞
 * @version 2018-07-31
 */
public class SelectCourse extends DataEntity<SelectCourse>  implements Comparable<SelectCourse>{
	
	private static final long serialVersionUID = 1L;
	private Course course;		// 课程编号
	private Student student;		// 学号
	private List<Course> courses;//选择学期课程

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public SelectCourse() {
		super();
	}

	public SelectCourse(String id){
		super(id);
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

	@Override
	public int compareTo(SelectCourse selectCourse) {
		BigInteger a = new BigInteger(student.getStudentNumber());
		BigInteger b = new BigInteger(selectCourse.getStudent().getStudentNumber());
		return a.compareTo(b);
	}
}