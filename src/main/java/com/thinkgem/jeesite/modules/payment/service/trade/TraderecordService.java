/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.service.trade;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.dao.trade.TraderecordDao;

/**
 * 交易信息Service
 * @author 赵俊飞
 * @version 2018-06-06
 */
@Service
@Transactional(readOnly = true)
public class TraderecordService extends CrudService<TraderecordDao, Traderecord> {

	public Traderecord get(String id) {
		return super.get(id);
	}
	
	public List<Traderecord> findList(Traderecord traderecord) {
		return super.findList(traderecord);
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