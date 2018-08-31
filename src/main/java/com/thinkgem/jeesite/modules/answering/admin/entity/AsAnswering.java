/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 答辩抽签Entity
 * @author 赵俊飞
 * @version 2018-08-17
 */
public class AsAnswering extends DataEntity<AsAnswering> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private Office office;		// 分类
	private String courseScheduleId;		// 计划编码
	private String timeAdd;		// 前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几
	private String teacherIds;		// 教师编码
	private String description;		// 描述、摘要
	
	public AsAnswering() {
		super();
	}

	public AsAnswering(String id){
		super(id);
	}

	@Length(min=1, max=64, message="标题长度必须介于 1 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull(message="分类不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=1, max=64, message="计划编码长度必须介于 1 和 64 之间")
	public String getCourseScheduleId() {
		return courseScheduleId;
	}

	public void setCourseScheduleId(String courseScheduleId) {
		this.courseScheduleId = courseScheduleId;
	}
	
	@Length(min=1, max=64, message="前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几长度必须介于 1 和 64 之间")
	public String getTimeAdd() {
		return timeAdd;
	}

	public void setTimeAdd(String timeAdd) {
		this.timeAdd = timeAdd;
	}
	
	@Length(min=1, max=1280, message="教师编码长度必须介于 1 和 1280 之间")
	public String getTeacherIds() {
		return teacherIds;
	}

	public void setTeacherIds(String teacherIds) {
		this.teacherIds = teacherIds;
	}
	
	@Length(min=0, max=255, message="描述、摘要长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}