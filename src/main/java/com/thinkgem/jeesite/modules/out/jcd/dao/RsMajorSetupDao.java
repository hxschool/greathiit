/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.out.jcd.entity.RsMajorSetup;

/**
 * 招生计划DAO接口
 * @author 赵俊飞
 * @version 2018-01-09
 */
@MyBatisDao
public interface RsMajorSetupDao extends CrudDao<RsMajorSetup> {
	public RsMajorSetup getMajorId(RsMajorSetup entity);
}