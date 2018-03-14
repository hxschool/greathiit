/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.school.entity.SchoolCourse;
import com.thinkgem.jeesite.modules.school.dao.SchoolCourseDao;

/**
 * 学院教室管理Service
 * @author 赵俊飞
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class SchoolCourseService extends CrudService<SchoolCourseDao, SchoolCourse> {

	public SchoolCourse get(String id) {
		return super.get(id);
	}
	
	public List<SchoolCourse> findList(SchoolCourse schoolCourse) {
		return super.findList(schoolCourse);
	}
	
	public Page<SchoolCourse> findPage(Page<SchoolCourse> page, SchoolCourse schoolCourse) {
		return super.findPage(page, schoolCourse);
	}
	
	@Transactional(readOnly = false)
	public void save(SchoolCourse schoolCourse) {
		super.save(schoolCourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(SchoolCourse schoolCourse) {
		super.delete(schoolCourse);
	}
	
}