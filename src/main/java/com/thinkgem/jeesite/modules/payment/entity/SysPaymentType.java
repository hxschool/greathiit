/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 全局缴费类型配置Entity
 * @author 赵俊飞
 * @version 2019-04-27
 */
public class SysPaymentType extends DataEntity<SysPaymentType> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String code;		// 编码
	private String description;		// 描述
	private String status;		// 状态,默认为0,当状态为1的时候读取关闭提示
	private List<SysPayment> sysPayments;
	public SysPaymentType() {
		super();
	}

	public SysPaymentType(String id){
		super(id);
	}

	@Length(min=1, max=64, message="标题长度必须介于 1 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=64, message="编码长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=11, message="状态,默认为0,当状态为1的时候读取关闭提示长度必须介于 1 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SysPayment> getSysPayments() {
		return sysPayments;
	}

	public void setSysPayments(List<SysPayment> sysPayments) {
		this.sysPayments = sysPayments;
	}
	
}