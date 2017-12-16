/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.entity.CourseSpecificContent;
import com.thinkgem.jeesite.modules.course.dao.CourseSpecificContentDao;

/**
 * 课程内容Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class CourseSpecificContentService extends CrudService<CourseSpecificContentDao, CourseSpecificContent> {

	public CourseSpecificContent get(String id) {
		return super.get(id);
	}
	
	public List<CourseSpecificContent> findList(CourseSpecificContent courseSpecificContent) {
		return super.findList(courseSpecificContent);
	}
	
	public Page<CourseSpecificContent> findPage(Page<CourseSpecificContent> page, CourseSpecificContent courseSpecificContent) {
		return super.findPage(page, courseSpecificContent);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseSpecificContent courseSpecificContent) {
		super.save(courseSpecificContent);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseSpecificContent courseSpecificContent) {
		super.delete(courseSpecificContent);
	}
	
}