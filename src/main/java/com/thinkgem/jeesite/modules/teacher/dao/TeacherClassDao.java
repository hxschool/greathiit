/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherClass;

/**
 * 教师班级信息表DAO接口
 * @author 赵俊飞
 * @version 2018-04-28
 */
@MyBatisDao
public interface TeacherClassDao extends CrudDao<TeacherClass> {
	
}