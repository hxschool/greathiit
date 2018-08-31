/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 学生基本信息DAO接口
 * @author 赵俊飞
 * @version 2017-09-19
 */
@MyBatisDao
public interface ApiDao{
	/**
	 * 获取专业
	 * @return
	 */
	public List<Map<String,Object>> getMajor();
	/**
	 * 获取学院
	 * @return
	 */
	public List<Map<String,Object>> getDepartment();
	/**
	 * 通过名称获取主键
	 * @param name
	 * @return
	 */
	public String getOfficeId(@Param("name")String name);
	/**
	 * 获取班级信息
	 * @param officeId
	 * @param clazzId
	 * @return
	 */
	public String getClazzName(@Param("parentId")String parentId,@Param("name")String name);
}