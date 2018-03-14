/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 学院教室管理Entity
 * @author 赵俊飞
 * @version 2018-03-13
 */
public class SchoolCourse extends DataEntity<SchoolCourse> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 学院编码
	private SchoolRoot schoolRoot;		// 教室编码
	
	
	public SchoolCourse() {
		super();
	}

	public SchoolCourse(String id){
		super(id);
	}

	@NotNull(message="学院编码不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	
	public SchoolRoot getSchoolRoot() {
		return schoolRoot;
	}

	public void setSchoolRoot(SchoolRoot schoolRoot) {
		this.schoolRoot = schoolRoot;
	}
	
}