/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.dao.CourseGpaDao;
import com.thinkgem.jeesite.modules.course.entity.CourseGpa;

/**
 * gpaService
 * @author 赵俊飞
 * @version 2019-02-01
 */
@Service
@Transactional(readOnly = true)
public class CourseGpaService extends CrudService<CourseGpaDao, CourseGpa> {
	@Resource
	private CourseGpaDao courseGpaDao;
	
	public List<CourseGpa> groupList(CourseGpa courseGpa){
		return courseGpaDao.groupList(courseGpa);
	}
	
	public CourseGpa get(String id) {
		return super.get(id);
	}
	
	public List<CourseGpa> findByParentIdsLike(CourseGpa courseGpa) {
		return super.findByParentIdsLike(courseGpa);
	}
	
	public Page<CourseGpa> findPage(Page<CourseGpa> page, CourseGpa courseGpa) {
		return super.findPage(page, courseGpa);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseGpa courseGpa) {
		super.save(courseGpa);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseGpa courseGpa) {
		super.delete(courseGpa);
	}
	
}