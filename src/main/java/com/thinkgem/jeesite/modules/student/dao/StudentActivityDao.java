/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.student.entity.StudentActivity;

/**
 * 参与活动DAO接口
 * @author 赵俊飞
 * @version 2017-12-13
 */
@MyBatisDao
public interface StudentActivityDao extends CrudDao<StudentActivity> {
	List<StudentActivity> getStudentActivitysByType(@Param("studentNumber")String studentNumber,@Param("actType")String actType);
}