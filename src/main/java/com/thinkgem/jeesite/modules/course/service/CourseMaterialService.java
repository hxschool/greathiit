/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.entity.CourseMaterial;
import com.thinkgem.jeesite.modules.course.dao.CourseMaterialDao;

/**
 * 课程基本信息Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class CourseMaterialService extends CrudService<CourseMaterialDao, CourseMaterial> {

	public CourseMaterial get(String id) {
		return super.get(id);
	}
	
	public List<CourseMaterial> findList(CourseMaterial courseMaterial) {
		return super.findList(courseMaterial);
	}
	
	public Page<CourseMaterial> findPage(Page<CourseMaterial> page, CourseMaterial courseMaterial) {
		return super.findPage(page, courseMaterial);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseMaterial courseMaterial) {
		super.save(courseMaterial);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseMaterial courseMaterial) {
		super.delete(courseMaterial);
	}
	
}