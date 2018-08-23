/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.select.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;

/**
 * 选课信息表DAO接口
 * @author 赵俊飞
 * @version 2018-07-31
 */
@MyBatisDao
public interface SelectCourseDao extends CrudDao<SelectCourse> {
	public int count(SelectCourse selectCourse);
}