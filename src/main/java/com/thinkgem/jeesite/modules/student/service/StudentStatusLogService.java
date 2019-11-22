/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.student.dao.StudentStatusLogDao;
import com.thinkgem.jeesite.modules.student.entity.StudentStatusLog;

/**
 * 变动进度表Service
 * @author 变动进度表
 * @version 2019-08-19
 */
@Service
@Transactional(readOnly = true)
public class StudentStatusLogService extends CrudService<StudentStatusLogDao, StudentStatusLog> {

	public StudentStatusLog get(String id) {
		return super.get(id);
	}
	
	public Page<StudentStatusLog> findPage(Page<StudentStatusLog> page, StudentStatusLog studentStatusLog) {
		return super.findPage(page, studentStatusLog);
	}
	
	
	
	@Transactional(readOnly = false)
	public void delete(StudentStatusLog studentStatusLog) {
		super.delete(studentStatusLog);
	}
	
}