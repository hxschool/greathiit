/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;

/**
 * 课程考试与教学目标支撑分值设置DAO接口
 * @author 赵俊飞
 * @version 2017-12-24
 */
@MyBatisDao
public interface CourseCompositionRulesDao extends CrudDao<CourseCompositionRules> {
	public CourseCompositionRules getCourseCompositionRulesByCourseId(@Param("courseId")String courseId) ;
}