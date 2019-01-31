/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.config.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.out.config.entity.RsStudentConfig;

/**
 * 系统配置DAO接口
 * @author 赵俊飞
 * @version 2019-01-31
 */
@MyBatisDao
public interface RsStudentConfigDao extends CrudDao<RsStudentConfig> {
	
}