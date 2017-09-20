/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.uc.dao.UcStudentDao;
import com.thinkgem.jeesite.modules.uc.entity.UcStudent;

/**
 * 学生基本信息Service
 * @author 赵俊飞
 * @version 2017-09-19
 */
@Service
@Transactional(readOnly = true)
public class UcStudentService extends CrudService<UcStudentDao, UcStudent> {
	@Autowired
	private UcStudentDao ucStudentDao;
	public UcStudent get(String id) {
		return super.get(id);
	}
	
	public List<UcStudent> findList(UcStudent ucStudent) {
		return super.findList(ucStudent);
	}
	
	public Page<UcStudent> findPage(Page<UcStudent> page, UcStudent ucStudent) {
		return super.findPage(page, ucStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(UcStudent ucStudent) {
		super.save(ucStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcStudent ucStudent) {
		super.delete(ucStudent);
	}
	
	public List<Map<String,Object>> studentGroup(Date startDate,Date endDate){
		return ucStudentDao.studentGroup(startDate, endDate);
	}
	
	public List<Map<String,Object>> studentSex(Date startDate,Date endDate){
		return ucStudentDao.studentSex(startDate, endDate);
	}
	
	
	public List<Map<String,Object>> studentRegion(Date startDate,Date endDate){
		return ucStudentDao.studentRegion(startDate, endDate);
	}
	
	public List<Map<String,Object>> studentEdu(Date startDate,Date endDate){
		return ucStudentDao.studentEdu(startDate, endDate);
	}
	
	public List<Map<String,Object>> studentDepartment(Date startDate,Date endDate){
		return ucStudentDao.studentDepartment(startDate, endDate);
	}
	
	public List<Map<String,Object>> studentMajor(Date startDate,Date endDate){
		return ucStudentDao.studentMajor(startDate, endDate);
	}
}