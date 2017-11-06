/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface SystemDao  {
	String getRsStudentId();
	String getSequence(@Param("serialNo") String serialNo);
}
