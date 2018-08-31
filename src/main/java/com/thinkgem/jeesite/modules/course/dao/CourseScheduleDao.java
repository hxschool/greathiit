/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
	//自定排课
	public List<CourseSchedule> auto(CourseSchedule courseSchedule);
	
	public List<CourseSchedule> findListByTimeAdd(@Param("timeAdd")String timeAdd);
	
	public CourseSchedule getByAddTime(@Param("timeAdd")String timeAdd);
	
	public List<CourseSchedule> getCourseScheduleByYearTerm(@Param("yearTerm")String yearTerm); 
	
	public List<CourseSchedule> getCourseScheduleByYearTermAndTeacherNumber(@Param("yearTerm")String yearTerm,@Param("teacherNumber")String teacherNumber);
	
	public List<CourseScheduleExt> getCourseScheduleExt(@Param("cursCurrTerm") String cursCurrTerm,@Param("courseClass") String courseClass, @Param("teacherNumber") String teacherNumber);
	/**
	 * 楼号+教室号
	 * @param root
	 * @return
	 */
	public List<CourseScheduleExt> getCourseScheduleByRoot(@Param("cursCurrTerm") String cursCurrTerm,@Param("root")String root);
	
	
	public List<CourseScheduleExt> findCoursesByParam(@Param("list") List<String> list,@Param("courseClass") String courseClass,@Param("teacherNumber") String teacherNumber);
}