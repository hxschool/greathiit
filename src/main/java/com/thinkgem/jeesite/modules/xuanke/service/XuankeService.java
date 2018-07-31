package com.thinkgem.jeesite.modules.xuanke.service;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.dao.CourseDao;
import com.thinkgem.jeesite.modules.course.entity.Course;

@Service
public class XuankeService  extends CrudService<CourseDao, Course>{
	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}
}
