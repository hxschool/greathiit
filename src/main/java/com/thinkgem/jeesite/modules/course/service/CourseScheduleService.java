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
import com.thinkgem.jeesite.modules.course.dao.CourseScheduleDao;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt;

/**
 * 计划教室Service
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class CourseScheduleService extends CrudService<CourseScheduleDao, CourseSchedule> {
	@Autowired
	private CourseScheduleDao courseScheduleDao;
	public List<CourseScheduleExt> findCoursesByParam(CourseScheduleExt courseScheduleExt){
		return courseScheduleDao.findCoursesByParam(courseScheduleExt.getList(), courseScheduleExt.getCourseClass(), courseScheduleExt.getTeacherNumber());
	}
	public List<CourseScheduleExt> getCourseScheduleExt(CourseScheduleExt courseScheduleExt){
		return courseScheduleDao.getCourseScheduleExt(courseScheduleExt);
		
	}
	
	public CourseSchedule get(String id) {
		return super.get(id);
	}
	
	public List<CourseSchedule> auto(CourseSchedule courseSchedule) {
		return courseScheduleDao.auto(courseSchedule);
	}
	
	public List<CourseSchedule> getCourseScheduleByYearTerm(String yearTerm) {
		return courseScheduleDao.getCourseScheduleByYearTerm(yearTerm);
	}
	public List<CourseSchedule> getCourseScheduleByYearTermAndTeacherNumber(String yearTerm,String teacherNumber) {
		return courseScheduleDao.getCourseScheduleByYearTermAndTeacherNumber(yearTerm,teacherNumber);
	}
	
	
	public CourseSchedule getByAddTime(String timeAdd) {
		return courseScheduleDao.getByAddTime(timeAdd);
	}
	
	
	public List<CourseSchedule> findListByTimeAdd(String timeAdd) {
		return courseScheduleDao.findListByTimeAdd(timeAdd);
	}
	
	public List<CourseSchedule> findList(CourseSchedule courseSchedule) {
		return super.findList(courseSchedule);
	}
	
	public Page<CourseSchedule> findPage(Page<CourseSchedule> page, CourseSchedule courseSchedule) {
		return super.findPage(page, courseSchedule);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseSchedule courseSchedule) {
		super.save(courseSchedule);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseSchedule courseSchedule) {
		super.delete(courseSchedule);
	}
	
}