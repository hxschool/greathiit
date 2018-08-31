/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.dao.CourseDao;

/**
 * 课程基本信息Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class CourseService extends CrudService<CourseDao, Course> {
	@Autowired
	private CourseDao courseDao;
	public Course getCourseByCourseId(String courseId) {
		Course course = new Course();
		course.setId(courseId);
		return courseDao.getCourse(course);
	}
	public Course getCourse(Course course) {
		return courseDao.getCourse(course);
	}
	
	public Course get(String id) {
		return super.get(id);
	}
	
	public List<Course> findCoursesByPaike(Course course) {
		return courseDao.findCoursesByPaike(course);
	}
	
	public List<Course> findList(Course course) {
		return super.findList(course);
	}
	
	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}

	
	@Transactional(readOnly = false)
	public void delete(Course course) {
		super.delete(course);
	}
	
}