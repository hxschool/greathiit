/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程基本信息Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class CourseMaterial extends DataEntity<CourseMaterial> {
	
	private static final long serialVersionUID = 1L;
	private String cmType;		// 类型
	private String cmAuthor;		// 作者
	private String cmName;		// 书名
	private String cmPubYear;		// 出版年份
	private String cmPublisher;		// 出版社
	private String courseId;		// course_id
	
	public CourseMaterial() {
		super();
	}

	public CourseMaterial(String id){
		super(id);
	}

	@Length(min=0, max=4, message="类型长度必须介于 0 和 4 之间")
	public String getCmType() {
		return cmType;
	}

	public void setCmType(String cmType) {
		this.cmType = cmType;
	}
	
	@Length(min=0, max=255, message="作者长度必须介于 0 和 255 之间")
	public String getCmAuthor() {
		return cmAuthor;
	}

	public void setCmAuthor(String cmAuthor) {
		this.cmAuthor = cmAuthor;
	}
	
	@Length(min=0, max=255, message="书名长度必须介于 0 和 255 之间")
	public String getCmName() {
		return cmName;
	}

	public void setCmName(String cmName) {
		this.cmName = cmName;
	}
	
	@Length(min=0, max=255, message="出版年份长度必须介于 0 和 255 之间")
	public String getCmPubYear() {
		return cmPubYear;
	}

	public void setCmPubYear(String cmPubYear) {
		this.cmPubYear = cmPubYear;
	}
	
	@Length(min=0, max=255, message="出版社长度必须介于 0 和 255 之间")
	public String getCmPublisher() {
		return cmPublisher;
	}

	public void setCmPublisher(String cmPublisher) {
		this.cmPublisher = cmPublisher;
	}
	
	@Length(min=1, max=64, message="course_id长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
}