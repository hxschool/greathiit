package com.thinkgem.jeesite.modules.answering.home.response;

import com.thinkgem.jeesite.modules.sys.entity.User;

public class AnsweringResponse {
	private String title;//标题
	private String school;//教室
	private String teachers;//教师
	private User current;//当前
	private User next;//下一个
	private int completed;//已完成
	private int total;//总数
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	public User getCurrent() {
		return current;
	}
	public void setCurrent(User current) {
		this.current = current;
	}
	public User getNext() {
		return next;
	}
	public void setNext(User next) {
		this.next = next;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
