/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.jcd.dao.RsZsjhDao;
import com.thinkgem.jeesite.modules.out.jcd.entity.RsZsjh;

/**
 * 招生计划Service
 * @author 赵俊飞
 * @version 2018-01-05
 */
@Service
@Transactional(readOnly = true)
public class RsZsjhService extends CrudService<RsZsjhDao, RsZsjh> {

	public RsZsjh get(String id) {
		return super.get(id);
	}
	
	public List<RsZsjh> findList(RsZsjh rsZsjh) {
		return super.findList(rsZsjh);
	}
	
	public Page<RsZsjh> findPage(Page<RsZsjh> page, RsZsjh rsZsjh) {
		return super.findPage(page, rsZsjh);
	}
	
	@Transactional(readOnly = false)
	public void save(RsZsjh rsZsjh) {
		super.save(rsZsjh);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsZsjh rsZsjh) {
		super.delete(rsZsjh);
	}
	
}