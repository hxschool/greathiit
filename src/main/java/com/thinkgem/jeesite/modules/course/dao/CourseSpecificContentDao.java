/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.course.entity.CourseSpecificContent;

/**
 * 课程具体内容DAO接口
 * @author 赵俊飞
 * @version 2017-12-24
 */
@MyBatisDao
public interface CourseSpecificContentDao extends CrudDao<CourseSpecificContent> {
	
}