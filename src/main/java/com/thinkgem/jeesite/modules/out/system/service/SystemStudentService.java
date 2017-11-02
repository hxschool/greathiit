/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.system.dao.SystemStudentDao;
import com.thinkgem.jeesite.modules.out.system.entity.SystemStudent;

/**
 * 单招报名申请表Service
 * @author 赵俊飞
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class SystemStudentService extends CrudService<SystemStudentDao, SystemStudent> {

	public SystemStudent get(String id) {
		return super.get(id);
	}
	
	public List<SystemStudent> findList(SystemStudent systemStudent) {
		return super.findList(systemStudent);
	}
	
	public Page<SystemStudent> findPage(Page<SystemStudent> page, SystemStudent systemStudent) {
		return super.findPage(page, systemStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(SystemStudent systemStudent) {
		super.save(systemStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemStudent systemStudent) {
		super.delete(systemStudent);
	}
	
}