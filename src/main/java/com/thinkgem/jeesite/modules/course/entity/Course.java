/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程基本信息Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class Course extends DataEntity<Course> {
	
	private static final long serialVersionUID = 1L;
	private String cursNum;		// 课程编号
	private String cursName;		// 课程名称
	private String cursEngName;		// 英文名称
	private String cursMajor;		// 专业
	private String cursClassHour;		// 学时
	private String cursCredit;		// 学分
	private String cursCurrTerm;		// curs_curr_term
	private String cursIntro;		// 课程简介
	private String cursNote1;		// 与相关课程的分工衔接
	private String cursNote2;		// 其他说明
	private String cursPreCourses;		// 先修课程
	private String cursProperty;		// 课程性质
	private String cursTerm;		// 开设学期
	private String cursType;		// 课程类型
	private User teacher;		// 教师号
	
	public Course() {
		super();
	}

	public Course(String id){
		super(id);
	}

	@Length(min=1, max=255, message="课程编号长度必须介于 1 和 255 之间")
	public String getCursNum() {
		return cursNum;
	}

	public void setCursNum(String cursNum) {
		this.cursNum = cursNum;
	}
	
	@Length(min=1, max=255, message="课程名称长度必须介于 1 和 255 之间")
	public String getCursName() {
		return cursName;
	}

	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	
	@Length(min=1, max=255, message="英文名称长度必须介于 1 和 255 之间")
	public String getCursEngName() {
		return cursEngName;
	}

	public void setCursEngName(String cursEngName) {
		this.cursEngName = cursEngName;
	}
	
	@Length(min=0, max=255, message="专业长度必须介于 0 和 255 之间")
	public String getCursMajor() {
		return cursMajor;
	}

	public void setCursMajor(String cursMajor) {
		this.cursMajor = cursMajor;
	}
	
	@Length(min=0, max=255, message="学时长度必须介于 0 和 255 之间")
	public String getCursClassHour() {
		return cursClassHour;
	}

	public void setCursClassHour(String cursClassHour) {
		this.cursClassHour = cursClassHour;
	}
	
	public String getCursCredit() {
		return cursCredit;
	}

	public void setCursCredit(String cursCredit) {
		this.cursCredit = cursCredit;
	}
	
	@Length(min=0, max=255, message="curs_curr_term长度必须介于 0 和 255 之间")
	public String getCursCurrTerm() {
		return cursCurrTerm;
	}

	public void setCursCurrTerm(String cursCurrTerm) {
		this.cursCurrTerm = cursCurrTerm;
	}
	
	@Length(min=0, max=255, message="课程简介长度必须介于 0 和 255 之间")
	public String getCursIntro() {
		return cursIntro;
	}

	public void setCursIntro(String cursIntro) {
		this.cursIntro = cursIntro;
	}
	
	@Length(min=0, max=255, message="与相关课程的分工衔接长度必须介于 0 和 255 之间")
	public String getCursNote1() {
		return cursNote1;
	}

	public void setCursNote1(String cursNote1) {
		this.cursNote1 = cursNote1;
	}
	
	@Length(min=0, max=255, message="其他说明长度必须介于 0 和 255 之间")
	public String getCursNote2() {
		return cursNote2;
	}

	public void setCursNote2(String cursNote2) {
		this.cursNote2 = cursNote2;
	}
	
	@Length(min=0, max=255, message="先修课程长度必须介于 0 和 255 之间")
	public String getCursPreCourses() {
		return cursPreCourses;
	}

	public void setCursPreCourses(String cursPreCourses) {
		this.cursPreCourses = cursPreCourses;
	}
	
	@Length(min=0, max=255, message="课程性质长度必须介于 0 和 255 之间")
	public String getCursProperty() {
		return cursProperty;
	}

	public void setCursProperty(String cursProperty) {
		this.cursProperty = cursProperty;
	}
	
	@Length(min=0, max=255, message="开设学期长度必须介于 0 和 255 之间")
	public String getCursTerm() {
		return cursTerm;
	}

	public void setCursTerm(String cursTerm) {
		this.cursTerm = cursTerm;
	}
	
	@Length(min=0, max=255, message="课程类型长度必须介于 0 和 255 之间")
	public String getCursType() {
		return cursType;
	}

	public void setCursType(String cursType) {
		this.cursType = cursType;
	}
	
	@NotNull(message="教师号不能为空")
	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	
}