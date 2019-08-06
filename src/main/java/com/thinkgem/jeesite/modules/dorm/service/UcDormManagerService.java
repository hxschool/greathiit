/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dorm.dao.UcDormManagerDao;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormManager;

/**
 * 宿舍管理Service
 * @author 赵俊飞
 * @version 2017-11-24
 */
@Service
@Transactional(readOnly = true)
public class UcDormManagerService extends CrudService<UcDormManagerDao, UcDormManager> {
	

	public List<UcDormManager> findByParentIdsLike(UcDormManager ucDormManager) {
		return super.findByParentIdsLike(ucDormManager);
	}
	
	public Page<UcDormManager> findPage(Page<UcDormManager> page, UcDormManager ucDorm) {
		return super.findPage(page, ucDorm);
	}
	
	
	
}