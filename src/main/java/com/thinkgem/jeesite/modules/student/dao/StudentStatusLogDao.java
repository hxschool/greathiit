/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.student.entity.StudentStatusLog;

/**
 * 变动进度表DAO接口
 * @author 变动进度表
 * @version 2019-08-19
 */
@MyBatisDao
public interface StudentStatusLogDao extends CrudDao<StudentStatusLog> {
	
}