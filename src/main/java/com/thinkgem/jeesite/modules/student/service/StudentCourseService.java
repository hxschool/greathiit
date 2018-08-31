/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.student.dao.StudentCourseDao;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;

/**
 * 学生成绩Service
 * @author 赵俊飞
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class StudentCourseService extends CrudService<StudentCourseDao, StudentCourse> {
	@Autowired
	private StudentCourseDao studentCourseDao;
	public List<StudentCourse> findListByTeacherIdAndClassIdAndCursType(String studentNumber,String clazzId,String cursType){
		return studentCourseDao.findListByTeacherIdAndClassIdAndCursType(studentNumber, clazzId, cursType);
	}
	public StudentCourse findStudentCourseByStudentNumberAndCourseId(String studentNumber,String courseId) {
		return studentCourseDao.findStudentCourseByStudentNumberAndCourseId(studentNumber, courseId);
	}
	public StudentCourse get(String id) {
		return super.get(id);
	}
	
	public List<StudentCourse> findList(StudentCourse studentCourse) {
		return super.findList(studentCourse);
	}
	
	public Page<StudentCourse> findPage(Page<StudentCourse> page, StudentCourse studentCourse) {
		return super.findPage(page, studentCourse);
	}
	
	@Transactional(readOnly = false)
	public void save(StudentCourse studentCourse) {
		super.save(studentCourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(StudentCourse studentCourse) {
		super.delete(studentCourse);
	}
	
}