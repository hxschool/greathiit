/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.system.dao;

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
	
}