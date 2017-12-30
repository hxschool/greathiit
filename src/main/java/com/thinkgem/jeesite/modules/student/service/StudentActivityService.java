/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.student.entity.StudentActivity;
import com.thinkgem.jeesite.modules.student.dao.StudentActivityDao;

/**
 * 参与活动Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class StudentActivityService extends CrudService<StudentActivityDao, StudentActivity> {
	private final static String ORG="组织活动";
	private final static String ATTEND="参与活动";
	private final static String SOCIAL="社会实践";
	@Autowired
	private StudentActivityDao studentActivityDao;
	public List<StudentActivity> getOrgActivities(String studentNumber){
		return studentActivityDao.getStudentActivitysByType(studentNumber,ORG);
	}
	public List<StudentActivity> getAttendActivities(String studentNumber){
		return studentActivityDao.getStudentActivitysByType(studentNumber,ATTEND);
	}
	public List<StudentActivity> getSocialActivities(String studentNumber){
		return studentActivityDao.getStudentActivitysByType(studentNumber,SOCIAL);
	}
	public StudentActivity get(String id) {
		return super.get(id);
	}
	
	public List<StudentActivity> findList(StudentActivity studentActivity) {
		return super.findList(studentActivity);
	}
	
	public Page<StudentActivity> findPage(Page<StudentActivity> page, StudentActivity studentActivity) {
		return super.findPage(page, studentActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(StudentActivity studentActivity) {
		super.save(studentActivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(StudentActivity studentActivity) {
		super.delete(studentActivity);
	}
	
}