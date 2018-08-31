/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
	public RsMajorSetup getRsMajorSetupByMajorName(RsMajorSetup entity);
	
}