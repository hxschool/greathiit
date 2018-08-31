/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.jcd.dao.RsMajorSetupDao;
import com.thinkgem.jeesite.modules.out.jcd.entity.RsMajorSetup;

/**
 * 招生计划Service
 * @author 赵俊飞
 * @version 2018-01-09
 */
@Service
@Transactional(readOnly = true)
public class RsMajorSetupService extends CrudService<RsMajorSetupDao, RsMajorSetup> {
	@Autowired
	private RsMajorSetupDao rsMajorSetupDao;
	
	public RsMajorSetup getMajorId(RsMajorSetup entity) {
		return rsMajorSetupDao.getMajorId(entity);
	}
	
	public RsMajorSetup getRsMajorSetupByMajorName(RsMajorSetup entity) {
		return rsMajorSetupDao.getRsMajorSetupByMajorName(entity);
	}
	
	public RsMajorSetup get(String id) {
		return super.get(id);
	}
	
	public List<RsMajorSetup> findList(RsMajorSetup rsMajorSetup) {
		return super.findList(rsMajorSetup);
	}
	
	public Page<RsMajorSetup> findPage(Page<RsMajorSetup> page, RsMajorSetup rsMajorSetup) {
		return super.findPage(page, rsMajorSetup);
	}
	
	@Transactional(readOnly = false)
	public void save(RsMajorSetup rsMajorSetup) {
		super.save(rsMajorSetup);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsMajorSetup rsMajorSetup) {
		super.delete(rsMajorSetup);
	}
	
}