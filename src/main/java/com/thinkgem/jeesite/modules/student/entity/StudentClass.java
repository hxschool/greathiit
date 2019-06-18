/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学生班级Entity
 * @author 赵俊飞
 * @version 2019-06-18
 */
public class StudentClass extends DataEntity<StudentClass> {
	
	private static final long serialVersionUID = 1L;
	private String studentNumber;		// 学号
	private String classId;		// 班级号
	private String orderNumber;		// 0:初始化,最大值
	private String tips;		// 提示信息
	
	public StudentClass() {
		super();
	}

	public StudentClass(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	@Length(min=1, max=20, message="班级号长度必须介于 1 和 20 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	@Length(min=0, max=10, message="0:初始化,最大值长度必须介于 0 和 10 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
}