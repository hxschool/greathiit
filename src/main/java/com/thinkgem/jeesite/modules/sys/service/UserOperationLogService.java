/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.UserOperationLog;
import com.thinkgem.jeesite.modules.sys.dao.UserOperationLogDao;

/**
 * 用户操作日志Service
 * @author 赵俊飞
 * @version 2018-08-24
 */
@Service
@Transactional(readOnly = true)
public class UserOperationLogService extends CrudService<UserOperationLogDao, UserOperationLog> {

	public UserOperationLog get(String id) {
		return super.get(id);
	}
	
	public List<UserOperationLog> findList(UserOperationLog userOperationLog) {
		return super.findList(userOperationLog);
	}
	
	public Page<UserOperationLog> findPage(Page<UserOperationLog> page, UserOperationLog userOperationLog) {
		return super.findPage(page, userOperationLog);
	}
	
	@Transactional(readOnly = false)
	public void save(UserOperationLog userOperationLog) {
		super.save(userOperationLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(UserOperationLog userOperationLog) {
		super.delete(userOperationLog);
	}
	
}