/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.course.entity.CourseGpa;

/**
 * gpaDAO接口
 * @author 赵俊飞
 * @version 2019-02-01
 */
@MyBatisDao
public interface CourseGpaDao extends CrudDao<CourseGpa> {
	public List<CourseGpa> groupList(CourseGpa courseGpa);
}