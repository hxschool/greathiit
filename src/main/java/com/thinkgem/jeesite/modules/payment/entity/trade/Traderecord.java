/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.entity.trade;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.payment.entity.order.Order;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 交易信息Entity
 * @author 赵俊飞
 * @version 2018-06-06
 */
public class Traderecord extends DataEntity<Traderecord> {
	
	private static final long serialVersionUID = 1L;
	private long payAmount;		// 支付金额
	private Date payTime;		// 支付时间
	private String errorCode;		// 错误码
	private String errorMsg;		// 错误描述
	private Date startTime;		// 生效开始时间
	private Date expireTime;		// 结束时间
	private String codeUrl;		// trd_code_url
	private String notifyUrl;		// 回调地址
	private String extra;		// trd_extra
	private String subject;		// 主题
	private String detail;		// 详情
	private String channel;		// 渠道
	private String status;		// 状态
	private User user;		// 用户
	private String userIp;		// 客户端IP
	private String idCard;		// 身份证
    private List<Order> orders = Lists.newArrayList();
    
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Traderecord() {
		super();
	}

	public Traderecord(String id){
		super(id);
	}

	public long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	@Length(min=0, max=32, message="错误码长度必须介于 0 和 32 之间")
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	@Length(min=0, max=128, message="错误描述长度必须介于 0 和 128 之间")
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	@Length(min=0, max=255, message="trd_code_url长度必须介于 0 和 255 之间")
	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	
	@Length(min=0, max=255, message="回调地址长度必须介于 0 和 255 之间")
	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	@Length(min=0, max=100, message="trd_extra长度必须介于 0 和 100 之间")
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	@Length(min=0, max=100, message="主题长度必须介于 0 和 100 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Length(min=0, max=500, message="详情长度必须介于 0 和 500 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=64, message="渠道长度必须介于 0 和 64 之间")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@Length(min=1, max=2, message="状态长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@NotNull(message="用户不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="客户端IP长度必须介于 0 和 64 之间")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	@Length(min=0, max=64, message="身份证长度必须介于 0 和 64 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}