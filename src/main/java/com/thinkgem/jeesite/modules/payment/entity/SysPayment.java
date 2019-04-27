/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 全局缴费配置Entity
 * @author 赵俊飞
 * @version 2019-04-27
 */
public class SysPayment extends DataEntity<SysPayment> {
	
	private static final long serialVersionUID = 1L;
	private SysPaymentType sysPaymentType;
	private String title;		// 标题
	private String amount;		// 缴费金额
	private String message;		// 提示信息
	private String description;		// 特别提示
	private String tip;		// 关闭提示
	private String status;		// 状态,默认为0,当状态为1的时候读取关闭提示
	
	public SysPayment() {
		super();
	}

	public SysPayment(String id){
		super(id);
	}

	
	
	public SysPaymentType getSysPaymentType() {
		return sysPaymentType;
	}

	public void setSysPaymentType(SysPaymentType sysPaymentType) {
		this.sysPaymentType = sysPaymentType;
	}

	@Length(min=1, max=64, message="标题长度必须介于 1 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@Length(min=1, max=11, message="状态,默认为0,当状态为1的时候读取关闭提示长度必须介于 1 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}