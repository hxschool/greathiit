/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.school.dao.SchoolRootDao;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;

/**
 * 楼宇Service
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class SchoolRootService extends CrudService<SchoolRootDao, SchoolRoot> {
	@Autowired
	private SchoolRootDao schoolRootDao;

	public SchoolRoot get(String id) {
		return super.get(id);
	}
	
	public List<SchoolRoot> findList(SchoolRoot schoolRoot) {
		return super.findList(schoolRoot);
	}
	

	public List<SchoolRoot> findByParentId(String parentId) {
		return schoolRootDao.findByParentId(parentId);
	}
	
	
	public Page<SchoolRoot> findPage(Page<SchoolRoot> page, SchoolRoot schoolRoot) {
		return super.findPage(page, schoolRoot);
	}
	
	@Transactional(readOnly = false)
	public void save(SchoolRoot schoolRoot) {
		super.save(schoolRoot);
	}
	
	@Transactional(readOnly = false)
	public void delete(SchoolRoot schoolRoot) {
		super.delete(schoolRoot);
	}
	
}