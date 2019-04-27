/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.payment.entity.SysPayment;

/**
 * 全局缴费配置DAO接口
 * @author 赵俊飞
 * @version 2019-04-27
 */
@MyBatisDao
public interface SysPaymentDao extends CrudDao<SysPayment> {
	
}