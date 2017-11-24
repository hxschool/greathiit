/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;

/**
 * 宿舍管理DAO接口
 * @author 赵俊飞
 * @version 2017-11-24
 */
@MyBatisDao
public interface UcDormDao extends CrudDao<UcDorm> {
	
}