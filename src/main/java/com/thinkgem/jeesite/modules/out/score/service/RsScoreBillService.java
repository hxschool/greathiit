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
import com.thinkgem.jeesite.modules.out.score.dao.RsScoreBillDao;
import com.thinkgem.jeesite.modules.out.score.entity.RsScoreBill;

/**
 * 考试成绩单Service
 * @author 赵俊飞
 * @version 2017-12-09
 */
@Service
@Transactional(readOnly = true)
public class RsScoreBillService extends CrudService<RsScoreBillDao, RsScoreBill> {
	@Autowired
	private RsScoreBillDao rsScoreBillDao;
	public RsScoreBill getByKsh(String ksh) {
		return rsScoreBillDao.getByKsh(ksh);
	}
	
	public RsScoreBill get(String id) {
		return super.get(id);
	}
	
	public List<RsScoreBill> findList(RsScoreBill rsJcd) {
		return super.findList(rsJcd);
	}
	
	public Page<RsScoreBill> findPage(Page<RsScoreBill> page, RsScoreBill rsJcd) {
		return super.findPage(page, rsJcd);
	}
	
	@Transactional(readOnly = false)
	public void save(RsScoreBill rsJcd) {
		super.save(rsJcd);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsScoreBill rsJcd) {
		super.delete(rsJcd);
	}
	
}