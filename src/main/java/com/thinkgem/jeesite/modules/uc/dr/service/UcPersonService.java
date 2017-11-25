/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.dr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.uc.dr.dao.UcPersonDao;
import com.thinkgem.jeesite.modules.uc.dr.entity.UcPerson;

/**
 * 学院基本信息Service
 * @author 赵俊飞
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class UcPersonService extends CrudService<UcPersonDao, UcPerson> {

	public UcPerson get(String id) {
		return super.get(id);
	}
	
	public List<UcPerson> findList(UcPerson ucPerson) {
		return super.findList(ucPerson);
	}
	
	public Page<UcPerson> findPage(Page<UcPerson> page, UcPerson ucPerson) {
		return super.findPage(page, ucPerson);
	}
	
	@Transactional(readOnly = false)
	public void save(UcPerson ucPerson) {
		super.save(ucPerson);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcPerson ucPerson) {
		super.delete(ucPerson);
	}
	
}