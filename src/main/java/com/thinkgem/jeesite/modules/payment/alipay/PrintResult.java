package com.thinkgem.jeesite.modules.payment.alipay;

import java.util.List;

public class PrintResult {
	private String studentNumber;
	private String name;
	private List<String> img;
	public synchronized String getStudentNumber() {
		return studentNumber;
	}
	public synchronized void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized List<String> getImg() {
		return img;
	}
	public synchronized void setImg(List<String> img) {
		this.img = img;
	}
	
}
