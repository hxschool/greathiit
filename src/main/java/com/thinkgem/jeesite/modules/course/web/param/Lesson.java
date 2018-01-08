package com.thinkgem.jeesite.modules.course.web.param;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Lesson {
	private String week;
	private String zhou;
	private String jie;
	private String address;
	private String course;
	
	public Lesson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lesson(String week, String zhou, String jie, String address, String course) {
		super();
		this.week = week;
		this.zhou = zhou;
		this.jie = jie;
		this.address = address;
		this.course = course;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getZhou() {
		return zhou;
	}
	public void setZhou(String zhou) {
		this.zhou = zhou;
	}
	public String getJie() {
		return jie;
	}
	public void setJie(String jie) {
		this.jie = jie;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
