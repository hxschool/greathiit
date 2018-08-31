/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;

/**
 * 教学方式DAO接口
 * @author 赵俊飞
 * @version 2017-12-24
 */
@MyBatisDao
public interface CourseTeachingModeDao extends CrudDao<CourseTeachingMode> {
	
}