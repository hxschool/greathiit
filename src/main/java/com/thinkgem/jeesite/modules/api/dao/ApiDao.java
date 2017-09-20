/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.dao;

import java.util.Date;
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

}