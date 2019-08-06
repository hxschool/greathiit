/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.payment.entity.SysPaymentType;
import com.thinkgem.jeesite.modules.payment.dao.SysPaymentTypeDao;

/**
 * 全局缴费类型配置Service
 * @author 赵俊飞
 * @version 2019-04-27
 */
@Service
@Transactional(readOnly = true)
public class SysPaymentTypeService extends CrudService<SysPaymentTypeDao, SysPaymentType> {
	@Autowired
	private SysPaymentTypeDao sysPaymentTypeDao;
	public SysPaymentType getCode(String code) {
		return sysPaymentTypeDao.getCode(code);
	}
	public SysPaymentType get(String id) {
		return super.get(id);
	}
	
	public List<SysPaymentType> findByParentIdsLike(SysPaymentType sysPaymentType) {
		return super.findByParentIdsLike(sysPaymentType);
	}
	
	public Page<SysPaymentType> findPage(Page<SysPaymentType> page, SysPaymentType sysPaymentType) {
		return super.findPage(page, sysPaymentType);
	}
	
	@Transactional(readOnly = false)
	public void save(SysPaymentType sysPaymentType) {
		super.save(sysPaymentType);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysPaymentType sysPaymentType) {
		super.delete(sysPaymentType);
	}
	
}