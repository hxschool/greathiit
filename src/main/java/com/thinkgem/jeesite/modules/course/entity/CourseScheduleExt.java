package com.thinkgem.jeesite.modules.course.entity;

public class CourseScheduleExt extends CourseSchedule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
}
