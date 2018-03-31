/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.sc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.sc.dao.RsStudentResultDao;
import com.thinkgem.jeesite.modules.out.sc.entity.RsStudentResult;

/**
 * 省成绩Service
 * @author 赵俊飞
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class RsStudentResultService extends CrudService<RsStudentResultDao, RsStudentResult> {

	public RsStudentResult get(String id) {
		return super.get(id);
	}
	
	public List<RsStudentResult> findList(RsStudentResult rsStudentResult) {
		return super.findList(rsStudentResult);
	}
	
	public Page<RsStudentResult> findPage(Page<RsStudentResult> page, RsStudentResult rsStudentResult) {
		return super.findPage(page, rsStudentResult);
	}
	
	@Transactional(readOnly = false)
	public void save(RsStudentResult rsStudentResult) {
		super.save(rsStudentResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsStudentResult rsStudentResult) {
		super.delete(rsStudentResult);
	}
	
}