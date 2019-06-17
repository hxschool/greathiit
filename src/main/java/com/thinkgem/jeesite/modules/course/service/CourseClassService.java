/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.dao.CourseClassDao;
import com.thinkgem.jeesite.modules.course.entity.CourseClass;

/**
 * 课程班级Service
 * @author 赵俊飞
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly = true)
public class CourseClassService extends CrudService<CourseClassDao, CourseClass> {
	public CourseClass get(String id) {
		return super.get(id);
	}
	
	public List<CourseClass> findList(CourseClass courseClass) {
		return super.findList(courseClass);
	}
	
	public Page<CourseClass> findPage(Page<CourseClass> page, CourseClass courseClass) {
		return super.findPage(page, courseClass);
	}
	
	
	@Transactional(readOnly = false)
	public void save(CourseClass courseClass) {
		super.save(courseClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseClass courseClass) {
		super.delete(courseClass);
	}
	
}