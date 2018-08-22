/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnswering;

/**
 * 答辩抽签DAO接口
 * @author 赵俊飞
 * @version 2018-08-17
 */
@MyBatisDao
public interface AsAnsweringDao extends CrudDao<AsAnswering> {
	
}