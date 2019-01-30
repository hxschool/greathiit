/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学期初始化Entity
 * @author 赵俊飞
 * @version 2017-12-15
 */
public class CourseYearTerm extends DataEntity<CourseYearTerm> {
	
	private static final long serialVersionUID = 1L;
	private String yearTerm;		// 前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几
	private String status;
	public CourseYearTerm() {
		super();
	}

	public CourseYearTerm(String id){
		super(id);
	}

	@Length(min=1, max=10, message="前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几长度必须介于 1 和 10 之间")
	public String getYearTerm() {
		return yearTerm;
	}

	public void setYearTerm(String yearTerm) {
		this.yearTerm = yearTerm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}