/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.service.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pay.GlobalConstants;
import com.thinkgem.jeesite.modules.payment.dao.order.OrderDao;
import com.thinkgem.jeesite.modules.payment.dao.trade.TraderecordDao;
import com.thinkgem.jeesite.modules.payment.entity.order.Order;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;

/**
 * 交易信息
 * @author 赵俊飞
 * @version 2018-06-06
 */
@Service
@Transactional(readOnly = true)
public class TraderecordService extends CrudService<TraderecordDao, Traderecord> {
	public static final String PAYMENT_TRADE_RECORD_STATUS_FEE="00";//初始化

	public static final String PAYMENT_TRADE_RECORD_STATUS_ING="10";//
	public static final String PAYMENT_TRADE_RECORD_STATUS_SUCC="20";//缴费成功
	@Autowired
	private OrderDao orderDao;
	public Traderecord get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	public void insertTraderecord(Traderecord traderecord) {
		
		Traderecord entity = get(traderecord);
		if(StringUtils.isEmpty(entity)) {
			traderecord.setIsNewRecord(true);
		}
		traderecord.setStatus(GlobalConstants.TRADESTATUS_PAY);
		super.save(traderecord);
		List<Order> orders = traderecord.getOrders();
		for(Order order:orders) {
			order.setTraderecord(traderecord);
			orderDao.insert(order);
		}
	}
	
	public List<Traderecord> findByParentIdsLike(Traderecord traderecord) {
		return super.findByParentIdsLike(traderecord);
	}
	
	public Page<Traderecord> findPage(Page<Traderecord> page, Traderecord traderecord) {
		return super.findPage(page, traderecord);
	}
	
	@Transactional(readOnly = false)
	public void save(Traderecord traderecord) {
		super.save(traderecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(Traderecord traderecord) {
		super.delete(traderecord);
	}
	
}