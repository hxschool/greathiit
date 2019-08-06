/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.WorkConfigDate;
import com.thinkgem.jeesite.modules.sys.dao.WorkConfigDateDao;

/**
 * 考勤信息Service
 * @author 赵俊飞
 * @version 2018-03-22
 */
@Service
@Transactional(readOnly = true)
public class WorkConfigDateService extends CrudService<WorkConfigDateDao, WorkConfigDate> {

	public WorkConfigDate get(String id) {
		return super.get(id);
	}
	
	public List<WorkConfigDate> findByParentIdsLike(WorkConfigDate workConfigDate) {
		return super.findByParentIdsLike(workConfigDate);
	}
	
	public Page<WorkConfigDate> findPage(Page<WorkConfigDate> page, WorkConfigDate workConfigDate) {
		return super.findPage(page, workConfigDate);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkConfigDate workConfigDate) {
		super.save(workConfigDate);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkConfigDate workConfigDate) {
		super.delete(workConfigDate);
	}
	
}