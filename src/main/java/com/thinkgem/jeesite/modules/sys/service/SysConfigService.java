/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.SysConfigDao;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;

/**
 * 全局系统配置Service
 * @author 赵俊飞
 * @version 2019-04-03
 */
@Service
@Transactional(readOnly = true)
public class SysConfigService extends CrudService<SysConfigDao, SysConfig> {
	@Autowired
	private SysConfigDao sysConfigDao;
	public SysConfig get(String id) {
		return super.get(id);
	}
	
	public SysConfig getModule(String module) {
		SysConfig sysConfig = new SysConfig();
		sysConfig.setModule(module);
		SysConfig entity = sysConfigDao.getModule(sysConfig);
		if(StringUtils.isEmpty(entity)) {
			entity = sysConfigDao.get("1");
		}
		return entity;
	}
	
	public SysConfig getModule(SysConfig sysConfig) {
		return sysConfigDao.getModule(sysConfig);
	}
	
	public List<SysConfig> findList(SysConfig sysConfig) {
		return super.findList(sysConfig);
	}
	
	public Page<SysConfig> findPage(Page<SysConfig> page, SysConfig sysConfig) {
		return super.findPage(page, sysConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SysConfig sysConfig) {
		super.save(sysConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysConfig sysConfig) {
		super.delete(sysConfig);
	}
	
}