package com.thinkgem.jeesite.modules.student.adapter;

import java.util.List;

import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

public class StudentScoreCourse {
	private UcStudent ucStudent;
	private List<StudentCourse> studentCourses;
	public UcStudent getUcStudent() {
		return ucStudent;
	}
	public void setUcStudent(UcStudent ucStudent) {
		this.ucStudent = ucStudent;
	}
	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}
	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}
	
}
