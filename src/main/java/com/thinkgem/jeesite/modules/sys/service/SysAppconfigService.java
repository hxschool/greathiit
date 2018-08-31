/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.SysAppconfigDao;
import com.thinkgem.jeesite.modules.sys.entity.SysAppconfig;

/**
 * 系统秘钥Service
 * @author 赵俊飞
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class SysAppconfigService extends CrudService<SysAppconfigDao, SysAppconfig> {
	@Autowired
	private SysAppconfigDao sysAppconfigDao;

	public SysAppconfig getByAppId(String appid) {
		return sysAppconfigDao.getByAppId(appid);
	} 
	public SysAppconfig get(String id) {
		return super.get(id);
	}
	
	public List<SysAppconfig> findList(SysAppconfig sysAppconfig) {
		return super.findList(sysAppconfig);
	}
	
	public Page<SysAppconfig> findPage(Page<SysAppconfig> page, SysAppconfig sysAppconfig) {
		return super.findPage(page, sysAppconfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SysAppconfig sysAppconfig) {
		super.save(sysAppconfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysAppconfig sysAppconfig) {
		super.delete(sysAppconfig);
	}
	
}