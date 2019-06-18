/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.course.entity.CourseClass;

/**
 * 课程班级DAO接口
 * @author 赵俊飞
 * @version 2019-06-17
 */
@MyBatisDao
public interface CourseClassDao extends CrudDao<CourseClass> {
	public void deleteByCourse(CourseClass courseClass);
}