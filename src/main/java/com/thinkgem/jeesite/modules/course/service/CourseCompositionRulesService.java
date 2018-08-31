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
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.course.dao.CourseCompositionRulesDao;

/**
 * 课程考试与教学目标支撑分值设置Service
 * @author 赵俊飞
 * @version 2017-12-24
 */
@Service
@Transactional(readOnly = true)
public class CourseCompositionRulesService extends CrudService<CourseCompositionRulesDao, CourseCompositionRules> {
	@Autowired
	private CourseCompositionRulesDao courseCompositionRulesDao;
	public CourseCompositionRules get(String id) {
		return super.get(id);
	}
	
	public CourseCompositionRules getCourseCompositionRulesByCourseId(String courseId) {
		return courseCompositionRulesDao.getCourseCompositionRulesByCourseId(courseId);
	}
	
	
	public List<CourseCompositionRules> findList(CourseCompositionRules courseCompositionRules) {
		return super.findList(courseCompositionRules);
	}
	
	public Page<CourseCompositionRules> findPage(Page<CourseCompositionRules> page, CourseCompositionRules courseCompositionRules) {
		return super.findPage(page, courseCompositionRules);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseCompositionRules courseCompositionRules) {
		super.save(courseCompositionRules);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseCompositionRules courseCompositionRules) {
		super.delete(courseCompositionRules);
	}
	
}