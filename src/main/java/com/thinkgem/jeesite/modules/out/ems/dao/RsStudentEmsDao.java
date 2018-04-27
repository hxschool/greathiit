/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.ems.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.out.ems.entity.RsStudentEms;

/**
 * 单招录取通知书DAO接口
 * @author 赵俊飞
 * @version 2018-04-27
 */
@MyBatisDao
public interface RsStudentEmsDao extends CrudDao<RsStudentEms> {
	public RsStudentEms getByUsernameAndIdCard(@Param("username")String username,@Param("idCard")String idCard) ;
}