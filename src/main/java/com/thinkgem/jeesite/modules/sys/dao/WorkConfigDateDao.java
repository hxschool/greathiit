/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
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