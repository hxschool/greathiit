/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 教师班级信息表Entity
 * @author 赵俊飞
 * @version 2018-04-28
 */
public class TeacherClass extends DataEntity<TeacherClass> {
	
	private static final long serialVersionUID = 1L;
	private Office clazz;		// 班级编号
	private String teacherNumber;		// 教师号
	
	public TeacherClass() {
		super();
	}

	public TeacherClass(String id){
		super(id);
	}

	
	public Office getClazz() {
		return clazz;
	}

	public void setClazz(Office clazz) {
		this.clazz = clazz;
	}

	@Length(min=1, max=255, message="教师号长度必须介于 1 和 255 之间")
	public String getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	
}