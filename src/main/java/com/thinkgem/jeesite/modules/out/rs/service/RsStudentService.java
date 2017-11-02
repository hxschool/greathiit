/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.rs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.rs.dao.RsStudentDao;
import com.thinkgem.jeesite.modules.out.rs.entity.RsStudent;

/**
 * 单招报名申请表Service
 * @author qq773152
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class RsStudentService extends CrudService<RsStudentDao, RsStudent> {

	public RsStudent get(String id) {
		return super.get(id);
	}
	
	public List<RsStudent> findList(RsStudent rsStudent) {
		return super.findList(rsStudent);
	}
	
	public Page<RsStudent> findPage(Page<RsStudent> page, RsStudent rsStudent) {
		return super.findPage(page, rsStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(RsStudent rsStudent) {
		super.save(rsStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsStudent rsStudent) {
		super.delete(rsStudent);
	}
	
}