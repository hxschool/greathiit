/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.ems.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.ems.dao.RsStudentEmsDao;
import com.thinkgem.jeesite.modules.out.ems.entity.RsStudentEms;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;

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
	
	@Resource
	private SysConfigService sysConfigService;

	public RsStudentEms getByUsernameAndIdCard(String username,String idCard) {
		return rsStudentEmsDao.getByUsernameAndIdCard(username,idCard);
	}
	public RsStudentEms get(String id) {
		return super.get(id);
	}
	
	public List<RsStudentEms> findByParentIdsLike(RsStudentEms rsStudentEms) {
		return super.findByParentIdsLike(rsStudentEms);
	}
	
	public Page<RsStudentEms> findPage(Page<RsStudentEms> page, RsStudentEms rsStudentEms) {
		return super.findPage(page, rsStudentEms);
	}
	
	@Transactional(readOnly = false)
	public void save(RsStudentEms rsStudentEms) {
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_EXAM);
		rsStudentEms.setTermYear(config.getTermYear());
		rsStudentEms.setIsNewRecord(true);
		super.save(rsStudentEms);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsStudentEms rsStudentEms) {
		super.delete(rsStudentEms);
	}
	
}