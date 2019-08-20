package com.thinkgem.jeesite.modules.pay.strategy;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.modules.pay.GlobalConstants;
import com.thinkgem.jeesite.modules.payment.entity.order.Order;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.order.OrderService;
import com.thinkgem.jeesite.modules.payment.service.trade.TraderecordService;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitStudent;
import com.thinkgem.jeesite.modules.recruit.service.student.RecruitStudentService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;
import com.thinkgem.jeesite.modules.uc.student.service.UcStudentService;

public class TradeStrategy {
    private static Logger logger = LoggerFactory.getLogger(TradeStrategy.class);
	protected void update(String out_trade_no) {
		try {
      	  
        	TraderecordService traderecordService = SpringContextHolder.getBean(TraderecordService.class);
        	 
        	 Traderecord traderecord = traderecordService.get(out_trade_no);
        	 if(traderecord.getStatus().equals(GlobalConstants.TRADESTATUS_PAY)) {
        		 traderecord.setStatus(GlobalConstants.TRADESTATUS_SUC);
        		 traderecord.setPayTime(new Date());
        		 traderecordService.save(traderecord);
        		 
        		 OrderService orderService = SpringContextHolder.getBean(OrderService.class);
        		 Order tradeOrder = new Order();
        		 tradeOrder.setTraderecord(traderecord);
        		 List<Order> orders =  orderService.findByParentIdsLike(tradeOrder);
        		 List<String> list1 = Lists.newArrayList();
        		 for(Order order:orders) {
        			 logger.info("准备更新订单:{}",order);
        			 order.setStatus(GlobalConstants.TRADESTATUS_SUC);
        			 orderService.save(order);
        		 }
        		
        		
					User user = UserUtils.get(traderecord.getUser().getId());
					Dict dict = new Dict();
					dict.setType("payment_type");
					DictService dictService = SpringContextHolder.getBean(DictService.class);
					List<Dict> dicts = dictService.findByParentIdsLike(dict);

					for (Dict d : dicts) {
						if (!StringUtils.isEmpty(user.getNo())) {
							String year = StudentUtil.getCircles(user.getNo());
							if (year.equals(d.getRemarks())) {
								list1.add(d.getId());
							}
						} else {
							list1.add(d.getId());
						}
					}
					 List<String> list0 = Lists.newArrayList();
            		 Order order = new Order();
            		 order.setUser(user);
            		 order.setStatus(GlobalConstants.TRADESTATUS_SUC);
            		 List<Order> orderList = orderService.findByParentIdsLike(order);
            		 for(Order o:orderList) {
            			 list0.add(o.getPayId());
            		 }
					boolean ret = list0.containsAll(list1) && list1.containsAll(list0);

					if (ret) {
						logger.info("准备更新学生缴费信息状态...全部费用已经完成缴费");
						RecruitStudentService recruitStudentService = SpringContextHolder.getBean(RecruitStudentService.class);
						RecruitStudent recruitStudent = new RecruitStudent();
						recruitStudent.setIdCard(user.getLoginName());
						RecruitStudent entity = recruitStudentService.get(recruitStudent);
						if(!StringUtils.isEmpty(entity)) {
							
							UcStudentService ucStudentService = SpringContextHolder.getBean(UcStudentService.class);
							UcStudent e = new UcStudent();
							e.setIdCard(user.getLoginName());
							UcStudent us = ucStudentService.get(e);
							
							if(!StringUtils.isEmpty(us)) {
								us = new UcStudent();
								us.setExaNumber(entity.getExaNumber());
								us.setLocation(entity.getLocation());
								//us.setStudentNumber();
								us.setName(entity.getUsername());
								us.setGender(entity.getGender());
								us.setBirthday(entity.getBirthday());
								us.setIdCard(entity.getIdCard());
								us.setPolitical(entity.getPolitical());
								us.setNation(entity.getNation());
								us.setDepartmentCode(entity.getDepartment().getId());
								us.setDepartmentId(entity.getDepartment().getId());
								us.setDepartmentName(entity.getDepartment().getName());
								us.setMajorCode(entity.getMajor().getId());
								us.setMajorId(entity.getMajor().getId());
								us.setMajorName(entity.getMajor().getName());
		
								us.setLearning("注册学籍");
								us.setStartDate("");
								us.setStatus("00");
								us.setRegionName(entity.getProvince());
							}
							
							
							entity.setStatus(RecruitStudentService.RECRUIT_STUDENT_STATUS_PAY_SUCC);
							recruitStudentService.save(entity);
						}
						SystemService systemService = SpringContextHolder.getBean(SystemService.class);
						user.setPayStatus(TraderecordService.PAYMENT_TRADE_RECORD_STATUS_SUCC);
						systemService.saveUser(user);
					}
        	 }

        } catch (Exception e) {
            logger.error("支付宝回调业务处理报错,params:" +  e);
        }
	}
}
