/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.ems.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.ems.dao.RsStudentEmsDao;
import com.thinkgem.jeesite.modules.out.ems.entity.RsStudentEms;

/**
 * 单招录取通知书Service
 * @author 赵俊飞
 * @version 2018-04-27
 */
@Service
@Transactional(readOnly = true)
public class RsStudentEmsService extends CrudService<RsStudentEmsDao, RsStudentEms> {
	@Resource
	private RsStudentEmsDao rsStudentEmsDao;

	public RsStudentEms getByUsernameAndIdCard(String username,String idCard) {
		return rsStudentEmsDao.getByUsernameAndIdCard(username,idCard);
	}
	public RsStudentEms get(String id) {
		return super.get(id);
	}
	
	public List<RsStudentEms> findList(RsStudentEms rsStudentEms) {
		return super.findList(rsStudentEms);
	}
	
	public Page<RsStudentEms> findPage(Page<RsStudentEms> page, RsStudentEms rsStudentEms) {
		return super.findPage(page, rsStudentEms);
	}
	
	@Transactional(readOnly = false)
	public void save(RsStudentEms rsStudentEms) {
		super.save(rsStudentEms);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsStudentEms rsStudentEms) {
		super.delete(rsStudentEms);
	}
	
}