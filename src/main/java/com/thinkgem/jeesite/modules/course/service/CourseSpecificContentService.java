/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
 * 课程具体内容Service
 * @author 赵俊飞
 * @version 2017-12-24
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