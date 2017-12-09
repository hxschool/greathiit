/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.out.jcd.entity.RsJcd;

/**
 * 考试成绩单DAO接口
 * @author 赵俊飞
 * @version 2017-12-09
 */
@MyBatisDao
public interface RsJcdDao extends CrudDao<RsJcd> {
	
}