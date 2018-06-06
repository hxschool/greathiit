/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.dao.trade;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;

/**
 * 交易信息DAO接口
 * @author 赵俊飞
 * @version 2018-06-06
 */
@MyBatisDao
public interface TraderecordDao extends CrudDao<Traderecord> {
	
}