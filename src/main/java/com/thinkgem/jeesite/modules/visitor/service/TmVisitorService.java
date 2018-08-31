/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.visitor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.visitor.entity.TmVisitor;
import com.thinkgem.jeesite.modules.visitor.dao.TmVisitorDao;

/**
 * 访客信息Service
 * @author 赵俊飞
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class TmVisitorService extends CrudService<TmVisitorDao, TmVisitor> {

	public TmVisitor get(String id) {
		return super.get(id);
	}
	
	public List<TmVisitor> findList(TmVisitor tmVisitor) {
		return super.findList(tmVisitor);
	}
	
	public Page<TmVisitor> findPage(Page<TmVisitor> page, TmVisitor tmVisitor) {
		return super.findPage(page, tmVisitor);
	}
	
	@Transactional(readOnly = false)
	public void save(TmVisitor tmVisitor) {
		super.save(tmVisitor);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmVisitor tmVisitor) {
		super.delete(tmVisitor);
	}
	
}