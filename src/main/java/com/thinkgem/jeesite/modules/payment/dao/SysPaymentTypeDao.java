/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.payment.entity.SysPaymentType;

/**
 * 全局缴费类型配置DAO接口
 * @author 赵俊飞
 * @version 2019-04-27
 */
@MyBatisDao
public interface SysPaymentTypeDao extends CrudDao<SysPaymentType> {
	public SysPaymentType getCode(String code);
}