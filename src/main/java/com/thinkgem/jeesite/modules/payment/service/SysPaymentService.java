/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.payment.entity.SysPayment;
import com.thinkgem.jeesite.modules.payment.dao.SysPaymentDao;

/**
 * 全局缴费配置Service
 * @author 赵俊飞
 * @version 2019-04-27
 */
@Service
@Transactional(readOnly = true)
public class SysPaymentService extends CrudService<SysPaymentDao, SysPayment> {

	public SysPayment get(String id) {
		return super.get(id);
	}
	
	public List<SysPayment> findByParentIdsLike(SysPayment sysPayment) {
		return super.findByParentIdsLike(sysPayment);
	}
	
	public Page<SysPayment> findPage(Page<SysPayment> page, SysPayment sysPayment) {
		return super.findPage(page, sysPayment);
	}
	
	@Transactional(readOnly = false)
	public void save(SysPayment sysPayment) {
		super.save(sysPayment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysPayment sysPayment) {
		super.delete(sysPayment);
	}
	
}