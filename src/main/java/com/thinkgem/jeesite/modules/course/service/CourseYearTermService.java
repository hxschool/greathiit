/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.dao.CourseYearTermDao;
import com.thinkgem.jeesite.modules.course.entity.CourseYearTerm;

/**
 * 学期初始化Service
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class CourseYearTermService extends CrudService<CourseYearTermDao, CourseYearTerm> {
	@Autowired
	private CourseYearTermDao courseYearTermDao;
	public CourseYearTerm get(String id) {
		return super.get(id);
	}

	
	public List<CourseYearTerm> findList(CourseYearTerm courseYearTerm) {
		return super.findList(courseYearTerm);
	}
	
	public Page<CourseYearTerm> findPage(Page<CourseYearTerm> page, CourseYearTerm courseYearTerm) {
		return super.findPage(page, courseYearTerm);
	}
	
	
	public CourseYearTerm systemConfig() {
		return courseYearTermDao.systemConfig();
	}
	
	@Transactional(readOnly = false)
	public void save(CourseYearTerm courseYearTerm) {
		super.save(courseYearTerm);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseYearTerm courseYearTerm) {
		super.delete(courseYearTerm);
	}
	
}