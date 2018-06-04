/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.recruit.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.recruit.dao.student.RecruitStudentDao;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitStudent;

/**
 * 统招数据Service
 * @author 赵俊飞
 * @version 2018-06-04
 */
@Service
@Transactional(readOnly = true)
public class RecruitStudentService extends CrudService<RecruitStudentDao, RecruitStudent> {
	@Autowired
	private RecruitStudentDao recruitStudentDao;
	
	public RecruitStudent getRecruitStudent(RecruitStudent entity) {
		return recruitStudentDao.get(entity);
	}
	
	public RecruitStudent get(String id) {
		return super.get(id);
	}
	
	public List<RecruitStudent> findList(RecruitStudent recruitStudent) {
		return super.findList(recruitStudent);
	}
	
	public Page<RecruitStudent> findPage(Page<RecruitStudent> page, RecruitStudent recruitStudent) {
		return super.findPage(page, recruitStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(RecruitStudent recruitStudent) {
		super.save(recruitStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(RecruitStudent recruitStudent) {
		super.delete(recruitStudent);
	}
	
}