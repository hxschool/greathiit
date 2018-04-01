/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.sc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 省成绩Entity
 * @author 赵俊飞
 * @version 2018-03-31
 */
public class RsStudentResult extends DataEntity<RsStudentResult> {
	
	private static final long serialVersionUID = 1L;
	private String hcFormBkh;		// 报考号
	private String hcFormXm;		// 姓名
	private String hcFormSfzh;		// 身份证号
	private String hcFormYuwen;		// 语文
	private String hcFormShuxue;		// 数学
	private String hcFormZonghe;		// 职业技能
	private String hcFormCj;		// 成绩
	private String hcFormStatus;		// 状态
	
	public RsStudentResult() {
		super();
	}

	public RsStudentResult(String id){
		super(id);
	}

	@Length(min=0, max=64, message="报考号长度必须介于 0 和 64 之间")
	@ExcelField(title="报考号", align=2, sort=0)
	public String getHcFormBkh() {
		return hcFormBkh;
	}

	public void setHcFormBkh(String hcFormBkh) {
		this.hcFormBkh = hcFormBkh;
	}
	
	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	@ExcelField(title="姓名", align=2, sort=1)
	public String getHcFormXm() {
		return hcFormXm;
	}

	public void setHcFormXm(String hcFormXm) {
		this.hcFormXm = hcFormXm;
	}
	
	@Length(min=1, max=64, message="身份证号长度必须介于 1 和 64 之间")
	@ExcelField(title="身份证号", align=2, sort=2)
	public String getHcFormSfzh() {
		return hcFormSfzh;
	}

	public void setHcFormSfzh(String hcFormSfzh) {
		this.hcFormSfzh = hcFormSfzh;
	}
	
	@Length(min=0, max=64, message="语文长度必须介于 0 和 64 之间")
	@ExcelField(title="语文", align=2, sort=3)
	public String getHcFormYuwen() {
		return hcFormYuwen;
	}

	public void setHcFormYuwen(String hcFormYuwen) {
		this.hcFormYuwen = hcFormYuwen;
	}
	
	@Length(min=0, max=64, message="数学长度必须介于 0 和 64 之间")
	@ExcelField(title="数学", align=2, sort=4)
	public String getHcFormShuxue() {
		return hcFormShuxue;
	}

	public void setHcFormShuxue(String hcFormShuxue) {
		this.hcFormShuxue = hcFormShuxue;
	}
	
	@Length(min=0, max=64, message="职业技能长度必须介于 0 和 64 之间")
	@ExcelField(title="职业技能", align=2, sort=5)
	public String getHcFormZonghe() {
		return hcFormZonghe;
	}

	public void setHcFormZonghe(String hcFormZonghe) {
		this.hcFormZonghe = hcFormZonghe;
	}
	
	@Length(min=0, max=64, message="总分长度必须介于 0 和 64 之间")
	@ExcelField(title="总分", align=2, sort=6)
	public String getHcFormCj() {
		return hcFormCj;
	}

	public void setHcFormCj(String hcFormCj) {
		this.hcFormCj = hcFormCj;
	}
	
	@Length(min=0, max=11, message="状态长度必须介于 0 和 11 之间")
	public String getHcFormStatus() {
		return hcFormStatus;
	}

	public void setHcFormStatus(String hcFormStatus) {
		this.hcFormStatus = hcFormStatus;
	}
	
}