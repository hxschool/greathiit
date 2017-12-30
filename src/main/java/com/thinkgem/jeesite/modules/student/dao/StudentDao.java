/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.student.entity.Student;

/**
 * 学生信息DAO接口
 * @author 赵俊飞
 * @version 2017-12-13
 */
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {
	public Student getStudentByStudentNumber(@Param("studentNumber")String studentNumber);
}