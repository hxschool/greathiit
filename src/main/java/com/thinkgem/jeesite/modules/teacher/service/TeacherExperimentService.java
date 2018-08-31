/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherExperiment;
import com.thinkgem.jeesite.modules.teacher.dao.TeacherExperimentDao;

/**
 * 教师履历Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class TeacherExperimentService extends CrudService<TeacherExperimentDao, TeacherExperiment> {

	public TeacherExperiment get(String id) {
		return super.get(id);
	}
	
	public List<TeacherExperiment> findList(TeacherExperiment teacherExperiment) {
		return super.findList(teacherExperiment);
	}
	
	public Page<TeacherExperiment> findPage(Page<TeacherExperiment> page, TeacherExperiment teacherExperiment) {
		return super.findPage(page, teacherExperiment);
	}
	
	@Transactional(readOnly = false)
	public void save(TeacherExperiment teacherExperiment) {
		super.save(teacherExperiment);
	}
	
	@Transactional(readOnly = false)
	public void delete(TeacherExperiment teacherExperiment) {
		super.delete(teacherExperiment);
	}
	
}