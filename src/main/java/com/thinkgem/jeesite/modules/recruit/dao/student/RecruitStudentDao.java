/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.recruit.dao.student;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitTotalMajorClass;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitStudent;

/**
 * 统招数据DAO接口
 * @author 赵俊飞
 * @version 2018-06-04
 */
@MyBatisDao
public interface RecruitStudentDao extends CrudDao<RecruitStudent> {
	RecruitStudent getRecruitStudent(RecruitStudent recruitStudent);
	List<RecruitTotalMajorClass> totalMajor(@Param("major_id") String major_id);
}