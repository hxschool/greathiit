/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.SysAppconfig;

/**
 * 系统秘钥DAO接口
 * @author 赵俊飞
 * @version 2017-12-28
 */
@MyBatisDao
public interface SysAppconfigDao extends CrudDao<SysAppconfig> {
	SysAppconfig getByAppId(@Param("appid")String appid);
}