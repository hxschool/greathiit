/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt;

/**
 * 计划教室DAO接口
 * @author 赵俊飞
 * @version 2017-12-15
 */
@MyBatisDao
public interface CourseScheduleDao extends CrudDao<CourseSchedule> {
	public List<CourseSchedule> findListByTimeAdd(@Param("timeAdd")String timeAdd);
	
	public CourseSchedule getByAddTime(@Param("timeAdd")String timeAdd);
	
	public List<CourseSchedule> getCourseScheduleByYearTerm(@Param("yearTerm")String yearTerm); 
	
	public List<CourseSchedule> getCourseScheduleByYearTermAndTeacherNumber(@Param("yearTerm")String yearTerm,@Param("teacherNumber")String teacherNumber);
	
	public List<CourseScheduleExt> getCourseScheduleExt(@Param("cursTerm") String cursTerm,@Param("courseClass") Integer courseClass, @Param("teacherNumber") String teacherNumber);
}