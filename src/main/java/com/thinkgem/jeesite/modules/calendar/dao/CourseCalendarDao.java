/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;

/**
 * 校历校准DAO接口
 * @author 赵俊飞
 * @version 2017-12-14
 */
@MyBatisDao
public interface CourseCalendarDao extends CrudDao<CourseCalendar> {
	public CourseCalendar systemConfig();
}