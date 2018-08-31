/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;

/**
 * 教师信息DAO接口
 * @author 赵俊飞
 * @version 2017-12-13
 */
@MyBatisDao
public interface TeacherDao extends CrudDao<Teacher> {
	
	public Teacher getTeacherByTeacherNumber(@Param("teacherNumber") String teacherNumber);
	public List<Teacher> findListByYearTerm(@Param("yearTerm")String yearTerm);
	public List<Teacher> getAllCourseTeacher();
	
}