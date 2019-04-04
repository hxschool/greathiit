/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.sc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.sc.dao.RsStudentResultDao;
import com.thinkgem.jeesite.modules.out.sc.entity.RsStudentResult;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;

/**
 * 省成绩Service
 * @author 赵俊飞
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class RsStudentResultService extends CrudService<RsStudentResultDao, RsStudentResult> {
	@Resource
	private SysConfigService sysConfigService;
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
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_EXAM);
		rsStudentResult.setTermYear(config.getTermYear());
		super.save(rsStudentResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsStudentResult rsStudentResult) {
		super.delete(rsStudentResult);
	}
	
}