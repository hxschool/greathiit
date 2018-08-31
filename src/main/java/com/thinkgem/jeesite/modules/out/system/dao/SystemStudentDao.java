/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.out.system.entity.SystemStudent;

/**
 * 单招报名申请表DAO接口
 * @author 赵俊飞
 * @version 2017-11-02
 */
@MyBatisDao
public interface SystemStudentDao extends CrudDao<SystemStudent> {
	public SystemStudent getByIdCard(@Param("idCard")String idCard) ;
	public SystemStudent getByUsernameAndIdCard(@Param("username")String username,@Param("idCard")String idCard) ;
	public List<Map<String,Object>> tj();
	public List<Map<String,Object>> kl();
	public List<Map<String,Object>> lb();
	public List<Map<String,Object>> xb();
	public List<Map<String,Object>> zy();
}