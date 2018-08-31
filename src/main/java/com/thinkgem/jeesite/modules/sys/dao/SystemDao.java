/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
	void insertSequence(@Param("type") String serialType,@Param("no") String serialNo,@Param("length") int length);
}
