/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingtarget;
import com.thinkgem.jeesite.modules.course.dao.CourseTeachingtargetDao;

/**
 * 设置课程考试与教学目标支撑分值Service
 * @author 赵俊飞
 * @version 2017-12-24
 */
@Service
@Transactional(readOnly = true)
public class CourseTeachingtargetService extends CrudService<CourseTeachingtargetDao, CourseTeachingtarget> {

	public CourseTeachingtarget get(String id) {
		return super.get(id);
	}
	
	public List<CourseTeachingtarget> findList(CourseTeachingtarget courseTeachingtarget) {
		return super.findList(courseTeachingtarget);
	}
	
	public Page<CourseTeachingtarget> findPage(Page<CourseTeachingtarget> page, CourseTeachingtarget courseTeachingtarget) {
		return super.findPage(page, courseTeachingtarget);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseTeachingtarget courseTeachingtarget) {
		super.save(courseTeachingtarget);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseTeachingtarget courseTeachingtarget) {
		super.delete(courseTeachingtarget);
	}
	
}