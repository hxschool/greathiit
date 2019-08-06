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
import com.thinkgem.jeesite.modules.course.dao.CoursePointDao;
import com.thinkgem.jeesite.modules.course.entity.CoursePoint;

/**
 * 课程班级计数点Service
 * @author 赵俊飞
 * @version 2018-12-25
 */
@Service
@Transactional(readOnly = true)
public class CoursePointService extends CrudService<CoursePointDao, CoursePoint> {
	@Autowired
	private CoursePointDao coursePointDao;
	
	public CoursePoint getCoursePointByCourseId(CoursePoint coursePoint) {
		return coursePointDao.getCoursePointByCourseId(coursePoint);
	}
	public CoursePoint get(String id) {
		return super.get(id);
	}
	
	public List<CoursePoint> findByParentIdsLike(CoursePoint coursePoint) {
		return super.findByParentIdsLike(coursePoint);
	}
	
	public Page<CoursePoint> findPage(Page<CoursePoint> page, CoursePoint coursePoint) {
		return super.findPage(page, coursePoint);
	}
	
	@Transactional(readOnly = false)
	public void save(CoursePoint coursePoint) {
		super.save(coursePoint);
	}
	
	@Transactional(readOnly = false)
	public void delete(CoursePoint coursePoint) {
		super.delete(coursePoint);
	}
	
}