/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.answering.admin.dao.AsAnsweringStudentDao;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnsweringStudent;

/**
 * 答辩Service
 * @author 赵俊飞
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly = true)
public class AsAnsweringStudentService extends CrudService<AsAnsweringStudentDao, AsAnsweringStudent> {
	
	@Autowired
	private AsAnsweringStudentDao asAnsweringStudentDao;
	public AsAnsweringStudent get(String id) {
		return super.get(id);
	}
	
	public List<AsAnsweringStudent> findList(AsAnsweringStudent asAnsweringStudent) {
		return super.findList(asAnsweringStudent);
	}
	
	public int count(AsAnsweringStudent asAnsweringStudent) {
		return asAnsweringStudentDao.count(asAnsweringStudent);
	}
	
	public Page<AsAnsweringStudent> findPage(Page<AsAnsweringStudent> page, AsAnsweringStudent asAnsweringStudent) {
		return super.findPage(page, asAnsweringStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(AsAnsweringStudent asAnsweringStudent) {
		super.save(asAnsweringStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(AsAnsweringStudent asAnsweringStudent) {
		super.delete(asAnsweringStudent);
	}
	
}