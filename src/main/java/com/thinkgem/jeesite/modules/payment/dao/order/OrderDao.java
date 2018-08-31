/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.dao.order;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.payment.entity.order.Order;

/**
 * 交易明细DAO接口
 * @author 赵俊飞
 * @version 2018-06-06
 */
@MyBatisDao
public interface OrderDao extends CrudDao<Order> {
	
}