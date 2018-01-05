/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.jcd.dao.RsJcdDao;
import com.thinkgem.jeesite.modules.out.jcd.entity.RsJcd;

/**
 * 考试成绩单Service
 * @author 赵俊飞
 * @version 2017-12-09
 */
@Service
@Transactional(readOnly = true)
public class RsJcdService extends CrudService<RsJcdDao, RsJcd> {
	@Autowired
	private RsJcdDao rsJcdDao;
	public RsJcd getByKsh(String ksh) {
		return rsJcdDao.getByKsh(ksh);
	}
	
	public RsJcd get(String id) {
		return super.get(id);
	}
	
	public List<RsJcd> findList(RsJcd rsJcd) {
		return super.findList(rsJcd);
	}
	
	public Page<RsJcd> findPage(Page<RsJcd> page, RsJcd rsJcd) {
		return super.findPage(page, rsJcd);
	}
	
	@Transactional(readOnly = false)
	public void save(RsJcd rsJcd) {
		super.save(rsJcd);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsJcd rsJcd) {
		super.delete(rsJcd);
	}
	
}