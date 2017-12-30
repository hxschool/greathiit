/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.student.entity.StudentItem;

/**
 * 获奖信息DAO接口
 * @author 赵俊飞
 * @version 2017-12-30
 */
@MyBatisDao
public interface StudentItemDao extends CrudDao<StudentItem> {
	
}