/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormBuild;
import com.thinkgem.jeesite.modules.dorm.dao.UcDormBuildDao;

/**
 * 公寓管理Service
 * @author 赵俊飞
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class UcDormBuildService extends CrudService<UcDormBuildDao, UcDormBuild> {

	public UcDormBuild get(String id) {
		return super.get(id);
	}
	
	public List<UcDormBuild> findList(UcDormBuild ucDormBuild) {
		return super.findList(ucDormBuild);
	}
	
	public Page<UcDormBuild> findPage(Page<UcDormBuild> page, UcDormBuild ucDormBuild) {
		return super.findPage(page, ucDormBuild);
	}
	
	@Transactional(readOnly = false)
	public void save(UcDormBuild ucDormBuild) {
		super.save(ucDormBuild);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcDormBuild ucDormBuild) {
		super.delete(ucDormBuild);
	}
	
}