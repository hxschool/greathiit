/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.select.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.course.web.excel.CourseSelectExcel;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;

/**
 * 选课信息表DAO接口
 * @author 赵俊飞
 * @version 2018-07-31
 */
@MyBatisDao
public interface SelectCourseDao extends CrudDao<SelectCourse> {
	public int count(SelectCourse selectCourse);
	public List<CourseSelectExcel> exportSelectCourse(CourseSelectExcel courseSelectExcel);
	public List<Map<String,Object>> totalSelectCourse();
}