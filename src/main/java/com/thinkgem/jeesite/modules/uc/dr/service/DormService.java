/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.dr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.uc.dr.dao.DormDao;
import com.thinkgem.jeesite.modules.uc.dr.entity.Dorm;

/**
 * 寝室信息Service
 * @author 赵俊飞
 * @version 2017-10-11
 */
@Service
@Transactional(readOnly = true)
public class DormService extends CrudService<DormDao, Dorm> {

	public Dorm get(String id) {
		return super.get(id);
	}
	
	public List<Dorm> findList(Dorm dorm) {
		return super.findList(dorm);
	}
	
	public Page<Dorm> findPage(Page<Dorm> page, Dorm dorm) {
		return super.findPage(page, dorm);
	}
	
	@Transactional(readOnly = false)
	public void save(Dorm dorm) {
		super.save(dorm);
	}
	
	@Transactional(readOnly = false)
	public void delete(Dorm dorm) {
		super.delete(dorm);
	}
	
}