/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;
import com.thinkgem.jeesite.modules.dorm.dao.UcDormDao;

/**
 * 宿舍管理Service
 * @author 赵俊飞
 * @version 2017-11-24
 */
@Service
@Transactional(readOnly = true)
public class UcDormService extends CrudService<UcDormDao, UcDorm> {

	public UcDorm get(String id) {
		return super.get(id);
	}
	
	public List<UcDorm> findList(UcDorm ucDorm) {
		return super.findList(ucDorm);
	}
	
	public Page<UcDorm> findPage(Page<UcDorm> page, UcDorm ucDorm) {
		return super.findPage(page, ucDorm);
	}
	
	@Transactional(readOnly = false)
	public void save(UcDorm ucDorm) {
		super.save(ucDorm);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcDorm ucDorm) {
		super.delete(ucDorm);
	}
	
}