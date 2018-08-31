/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.ems.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 单招录取通知书Entity
 * @author 赵俊飞
 * @version 2018-04-27
 */
public class RsStudentEms extends DataEntity<RsStudentEms> {
	
	private static final long serialVersionUID = 1L;
	private String hcFormEms;		// EMS邮件编号
	private String hcFormBkh;		// 报考号
	private String hcFormXm;		// 姓名
	private String hcFormSfzh;		// 身份证号
	private String hcFormDz;		// 地址
	private String hcFormSjf;		// 收件人
	private String hcFormRemarks;		// 备注
	private String hcFormStatus;		// 状态
	
	public RsStudentEms() {
		super();
	}

	public RsStudentEms(String id){
		super(id);
	}

	@Length(min=0, max=64, message="EMS邮件编号长度必须介于 0 和 64 之间")
	@ExcelField(title="EMS邮件编号", align=2, sort=0)
	public String getHcFormEms() {
		return hcFormEms;
	}

	public void setHcFormEms(String hcFormEms) {
		this.hcFormEms = hcFormEms;
	}
	
	@Length(min=0, max=64, message="报考号长度必须介于 0 和 64 之间")
	@ExcelField(title="报考号", align=2, sort=1)
	public String getHcFormBkh() {
		return hcFormBkh;
	}

	public void setHcFormBkh(String hcFormBkh) {
		this.hcFormBkh = hcFormBkh;
	}
	
	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	@ExcelField(title="姓名", align=2, sort=2)
	public String getHcFormXm() {
		return hcFormXm;
	}

	public void setHcFormXm(String hcFormXm) {
		this.hcFormXm = hcFormXm;
	}
	
	@Length(min=1, max=64, message="身份证号长度必须介于 1 和 64 之间")
	@ExcelField(title="身份证号", align=2, sort=3)
	public String getHcFormSfzh() {
		return hcFormSfzh;
	}

	public void setHcFormSfzh(String hcFormSfzh) {
		this.hcFormSfzh = hcFormSfzh;
	}
	
	@Length(min=0, max=500, message="地址长度必须介于 0 和 500 之间")
	@ExcelField(title="地址", align=2, sort=4)
	public String getHcFormDz() {
		return hcFormDz;
	}

	public void setHcFormDz(String hcFormDz) {
		this.hcFormDz = hcFormDz;
	}
	
	@Length(min=0, max=64, message="收件人长度必须介于 0 和 64 之间")
	@ExcelField(title="收件人", align=2, sort=5)
	public String getHcFormSjf() {
		return hcFormSjf;
	}

	public void setHcFormSjf(String hcFormSjf) {
		this.hcFormSjf = hcFormSjf;
	}
	
	@Length(min=0, max=64, message="备注长度必须介于 0 和 64 之间")
	public String getHcFormRemarks() {
		return hcFormRemarks;
	}

	public void setHcFormRemarks(String hcFormRemarks) {
		this.hcFormRemarks = hcFormRemarks;
	}
	
	@Length(min=0, max=11, message="状态长度必须介于 0 和 11 之间")
	public String getHcFormStatus() {
		return hcFormStatus;
	}

	public void setHcFormStatus(String hcFormStatus) {
		this.hcFormStatus = hcFormStatus;
	}
	
}