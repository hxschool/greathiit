/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.calendar.dao.CourseCalendarDao;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;

/**
 * 校历校准Service
 * @author 赵俊飞
 * @version 2017-12-14
 */
@Service
@Transactional(readOnly = true)
public class CourseCalendarService extends CrudService<CourseCalendarDao, CourseCalendar> {
	@Autowired
	private CourseCalendarDao courseCalendarDao;
	public CourseCalendar systemConfig() {
		return courseCalendarDao.systemConfig();
	}
	
	public CourseCalendar get(String id) {
		return super.get(id);
	}
	
	public List<CourseCalendar> findList(CourseCalendar courseCalendar) {
		return super.findList(courseCalendar);
	}
	
	public Page<CourseCalendar> findPage(Page<CourseCalendar> page, CourseCalendar courseCalendar) {
		return super.findPage(page, courseCalendar);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseCalendar courseCalendar) {
		super.save(courseCalendar);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseCalendar courseCalendar) {
		super.delete(courseCalendar);
	}
	
}