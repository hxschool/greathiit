/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;

/**
 * 学生成绩DAO接口
 * 
 * @author 赵俊飞
 * @version 2018-01-30
 */
@MyBatisDao
public interface StudentCourseDao extends CrudDao<StudentCourse> {
	StudentCourse findStudentCourseByStudentNumberAndCourseId(@Param("studentNumber") String studentNumber,
			@Param("courseId") String courseId);

	/**
	 * 教师查询成绩
	 * 
	 * @param studentNumber
	 * @param clazzId
	 * @return
	 */
	List<StudentCourse> findListByTeacherIdAndClassIdAndCursType(@Param("teacherNumber") String teacherNumber,@Param("clazzId") String clazzId, @Param("cursType") String cursType);

	List<StudentCourse> findListByStudentNumber(@Param("studentNumber") String studentNumber);

	StudentCourse getStudentCourseByStudentCourse(StudentCourse studentCourse);
}