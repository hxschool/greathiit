/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.entity.CourseEducational;
import com.thinkgem.jeesite.modules.course.dao.CourseEducationalDao;

/**
 * 教务课程信息Service
 * @author 赵俊飞
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class CourseEducationalService extends CrudService<CourseEducationalDao, CourseEducational> {

	public CourseEducational get(String id) {
		return super.get(id);
	}
	
	public List<CourseEducational> findList(CourseEducational courseEducational) {
		return super.findList(courseEducational);
	}
	
	public Page<CourseEducational> findPage(Page<CourseEducational> page, CourseEducational courseEducational) {
		return super.findPage(page, courseEducational);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseEducational courseEducational) {
		super.save(courseEducational);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseEducational courseEducational) {
		super.delete(courseEducational);
	}
	
}