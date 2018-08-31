/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.WorkConfigDate;

/**
 * 考勤信息DAO接口
 * @author 赵俊飞
 * @version 2018-03-22
 */
@MyBatisDao
public interface WorkConfigDateDao extends CrudDao<WorkConfigDate> {
	
}