/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;
import com.thinkgem.jeesite.modules.course.dao.CourseTeachingModeDao;

/**
 * 教学方式Service
 * @author 赵俊飞
 * @version 2017-12-24
 */
@Service
@Transactional(readOnly = true)
public class CourseTeachingModeService extends CrudService<CourseTeachingModeDao, CourseTeachingMode> {

	public CourseTeachingMode get(String id) {
		return super.get(id);
	}
	
	public List<CourseTeachingMode> findList(CourseTeachingMode courseTeachingMode) {
		return super.findList(courseTeachingMode);
	}
	
	public Page<CourseTeachingMode> findPage(Page<CourseTeachingMode> page, CourseTeachingMode courseTeachingMode) {
		return super.findPage(page, courseTeachingMode);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseTeachingMode courseTeachingMode) {
		super.save(courseTeachingMode);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseTeachingMode courseTeachingMode) {
		super.delete(courseTeachingMode);
	}
	
}