/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.config.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.config.dao.RsStudentConfigDao;
import com.thinkgem.jeesite.modules.out.config.entity.RsStudentConfig;

/**
 * 系统配置Service
 * @author 赵俊飞
 * @version 2019-01-31
 */
@Service
@Transactional(readOnly = true)
public class RsStudentConfigService extends CrudService<RsStudentConfigDao, RsStudentConfig> {

	public RsStudentConfig get(String id) {
		return super.get(id);
	}
	
	public List<RsStudentConfig> findList(RsStudentConfig rsStudentConfig) {
		return super.findList(rsStudentConfig);
	}
	
	public Page<RsStudentConfig> findPage(Page<RsStudentConfig> page, RsStudentConfig rsStudentConfig) {
		return super.findPage(page, rsStudentConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(RsStudentConfig rsStudentConfig) {
		rsStudentConfig.setTip(StringEscapeUtils.unescapeHtml4(rsStudentConfig.getTip()));
		rsStudentConfig.setDescription(StringEscapeUtils.unescapeHtml4(rsStudentConfig.getDescription()));
		rsStudentConfig.setRemarks(StringEscapeUtils.unescapeHtml4(rsStudentConfig.getRemarks()));
		super.save(rsStudentConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsStudentConfig rsStudentConfig) {
		super.delete(rsStudentConfig);
	}
	
}