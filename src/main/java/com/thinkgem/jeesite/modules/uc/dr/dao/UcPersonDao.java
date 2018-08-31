/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.dr.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.uc.dr.entity.UcPerson;

/**
 * 学院基本信息DAO接口
 * @author 赵俊飞
 * @version 2017-11-25
 */
@MyBatisDao
public interface UcPersonDao extends CrudDao<UcPerson> {
	
}