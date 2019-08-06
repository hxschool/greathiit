/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.teacher.dao.TeacherDao;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;

/**
 * 教师信息Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class TeacherService extends CrudService<TeacherDao, Teacher> {
	@Autowired
	private TeacherDao teacherDao;
	
	public List<Teacher> getAllCourseTeacher() {
		return teacherDao.getAllCourseTeacher();
	}
	public List<Teacher> findListByYearTerm(String yearTerm) {
		return teacherDao.findListByYearTerm(yearTerm);
	}

	public Teacher getTeacherByTeacherNumber(String teacherNumber) {
		return teacherDao.getTeacherByTeacherNumber(teacherNumber);
	}
	
	public Teacher getTeacherInfo(Teacher teacher) {
		return teacherDao.getTeacherInfo(teacher);
	}

	
	public Teacher get(Teacher teacher) {
		return super.get(teacher);
	}
	public Teacher get(String id) {
		return super.get(id);
	}
	
	public List<Teacher> findByParentIdsLike(Teacher teacher) {
		return super.findByParentIdsLike(teacher);
	}
	
	public Page<Teacher> findPage(Page<Teacher> page, Teacher teacher) {
		return super.findPage(page, teacher);
	}
	
	@Transactional(readOnly = false)
	public void save(Teacher teacher) {
		super.save(teacher);
	}
	
	@Transactional(readOnly = false)
	public void delete(Teacher teacher) {
		super.delete(teacher);
	}
	
}