package com.thinkgem.jeesite.modules.course.entity;

import java.util.List;

public class CourseScheduleExt extends CourseSchedule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cursCurrTerm;
	private String teacherNumber;
	private String root;
	private List<String> list;
	
	private String cursName;
	private String tchrName;
	public String getCursName() {
		return cursName;
	}
	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	public String getTchrName() {
		return tchrName;
	}
	public void setTchrName(String tchrName) {
		this.tchrName = tchrName;
	}
	public String getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getCursCurrTerm() {
		return cursCurrTerm;
	}
	public void setCursCurrTerm(String cursCurrTerm) {
		this.cursCurrTerm = cursCurrTerm;
	}
	
}
