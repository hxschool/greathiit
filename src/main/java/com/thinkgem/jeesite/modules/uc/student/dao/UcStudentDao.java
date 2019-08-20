/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.student.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 学生基本信息DAO接口
 * @author 赵俊飞
 * @version 2017-09-19
 */
@MyBatisDao
public interface UcStudentDao extends CrudDao<UcStudent> {
	
	public UcStudent findByIdCard(@Param("idCard")String idCard);
	public UcStudent findBystudentNumber(@Param("studentNumber")String studentNumber);
	/**
	 * 通过姓名和身份证号获取学生信息
	 * @param username
	 * @param idCard
	 * @return
	 */
	public String findNumberByUsernameAndIdCard(@Param("username")String username,@Param("idCard")String idCard);
	/**
	 * 通过姓名,身份证号,学号获取学生信息
	 * @param username
	 * @param idCard
	 * @param number
	 * @return
	 */
	public UcStudent findNumberByUsernameAndIdCardAndNumber(@Param("username")String username,@Param("idCard")String idCard,@Param("number")String number);
	/**
	 * 统计未报到与已经报到
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> studentGroup(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	/**
	 * 男女占比
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> studentSex(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	/**
	 * 全国招生情况
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> studentRegion(@Param("year")String year);
	
	public List<Map<String,Object>> studentGengerRegion(@Param("year")String year);
	
	/**
	 * 学院
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> studentDepartment(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	/**
	 * 专业
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> studentMajor(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	/**
	 * 学历
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> studentEdu(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	/**
	 * 学生信息表用户信息不全,缺少2017和2018数据.直接读取用户数据表数据
	 * @return
	 */
	public List<Map<String,Object>> studentUser();
	
/**
 * 根据导出做查询
 * @param ucStudent
 * @return
 */
	public List<UcStudent> exportList(UcStudent ucStudent);
	/**
	 * 毕业生
	 * @param ucStudent
	 * @return
	 */
	public List<UcStudent> completeList(UcStudent ucStudent);
	public List<UcStudent> fenban(UcStudent ucStudent);
}