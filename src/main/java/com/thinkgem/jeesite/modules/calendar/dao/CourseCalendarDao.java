/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
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