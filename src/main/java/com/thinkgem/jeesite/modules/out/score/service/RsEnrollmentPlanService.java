/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.score.dao.RsEnrollmentPlanDao;
import com.thinkgem.jeesite.modules.out.score.entity.RsEnrollmentPlan;

/**
 * 招生计划Service
 * @author 赵俊飞
 * @version 2018-01-09
 */
@Service
@Transactional(readOnly = true)
public class RsEnrollmentPlanService extends CrudService<RsEnrollmentPlanDao, RsEnrollmentPlan> {
	@Autowired
	private RsEnrollmentPlanDao rsEnrollmentPlanDao;
	
	public RsEnrollmentPlan getMajorId(RsEnrollmentPlan entity) {
		return rsEnrollmentPlanDao.getMajorId(entity);
	}
	
	public RsEnrollmentPlan getRsEnrollmentPlanByMajorName(RsEnrollmentPlan entity) {
		return rsEnrollmentPlanDao.getRsRsEnrollmentPlanByMajorName(entity);
	}
	
	public RsEnrollmentPlan get(String id) {
		return super.get(id);
	}
	
	public List<RsEnrollmentPlan> findList(RsEnrollmentPlan rsMajorSetup) {
		return super.findList(rsMajorSetup);
	}
	
	public Page<RsEnrollmentPlan> findPage(Page<RsEnrollmentPlan> page, RsEnrollmentPlan rsMajorSetup) {
		return super.findPage(page, rsMajorSetup);
	}
	
	@Transactional(readOnly = false)
	public void save(RsEnrollmentPlan rsMajorSetup) {
		super.save(rsMajorSetup);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsEnrollmentPlan rsMajorSetup) {
		super.delete(rsMajorSetup);
	}
	
}