/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.entity.order;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 交易明细Entity
 * @author 赵俊飞
 * @version 2018-06-06
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private Traderecord traderecord;		// 交易流水编码
	private String payId;		// 商品编码
	private String payTitle;		// 商品标题
	private String payRemark;		// 商品备注
	private String payAmount;		// 支付金额
	private Date payTime;		// 支付时间
	private String errorCode;		// 错误码
	private String errorMsg;		// 错误描述
	private User user;		// 用户
	private String status;		// 状态
	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}
	
	public Traderecord getTraderecord() {
		return traderecord;
	}

	public void setTraderecord(Traderecord traderecord) {
		this.traderecord = traderecord;
	}

	@Length(min=1, max=64, message="商品编码长度必须介于 1 和 64 之间")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	@Length(min=1, max=64, message="商品标题长度必须介于 1 和 64 之间")
	public String getPayTitle() {
		return payTitle;
	}

	public void setPayTitle(String payTitle) {
		this.payTitle = payTitle;
	}
	
	@Length(min=1, max=64, message="商品备注长度必须介于 1 和 64 之间")
	public String getPayRemark() {
		return payRemark;
	}

	public void setPayRemark(String payRemark) {
		this.payRemark = payRemark;
	}
	
	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
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
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min=1, max=2, message="状态长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}