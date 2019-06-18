/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.student.entity.StudentClass;
import com.thinkgem.jeesite.modules.student.dao.StudentClassDao;

/**
 * 学生班级Service
 * @author 赵俊飞
 * @version 2019-06-18
 */
@Service
@Transactional(readOnly = true)
public class StudentClassService extends CrudService<StudentClassDao, StudentClass> {

	public StudentClass get(String id) {
		return super.get(id);
	}
	
	public List<StudentClass> findList(StudentClass studentClass) {
		return super.findList(studentClass);
	}
	
	public Page<StudentClass> findPage(Page<StudentClass> page, StudentClass studentClass) {
		return super.findPage(page, studentClass);
	}
	
	@Transactional(readOnly = false)
	public void save(StudentClass studentClass) {
		super.save(studentClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(StudentClass studentClass) {
		super.delete(studentClass);
	}
	
}