/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.rs.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.out.rs.entity.RsStudent;

/**
 * 单招报名申请表DAO接口
 * @author qq773152
 * @version 2017-11-02
 */
@MyBatisDao
public interface RsStudentDao extends CrudDao<RsStudent> {
	
}