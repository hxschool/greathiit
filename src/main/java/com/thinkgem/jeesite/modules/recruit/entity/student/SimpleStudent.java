/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.recruit.entity.student;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 统招数据Entity
 * @author 赵俊飞
 * @version 2018-06-04
 */
public class SimpleStudent{
	
	private static final long serialVersionUID = 1L;
	
	private String leven;		// 层次(本科/高职单招)
	private String province;		// 省份
	private String exaNumber;		// 考试号
	private String username;		// 真实姓名
	private String idCard;		// 身份证号码
	private String majorname;		// 专业编码
	private String zongfen;		// 特长
	private String techang;		// 特长
	private String status;		// 状态 00:初始化数据导入 01:完成院校报名 11:交易处理中 20:成功 30:失败
	@ExcelField(title="招生层次", align=2, sort=1)
	public String getLeven() {
		return leven;
	}
	public void setLeven(String leven) {
		this.leven = leven;
	}
	@ExcelField(title="省份", align=2, sort=2)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@ExcelField(title="考生号", align=2, sort=3)
	public String getExaNumber() {
		return exaNumber;
	}
	public void setExaNumber(String exaNumber) {
		this.exaNumber = exaNumber;
	}
	@ExcelField(title="姓名", align=2, sort=4)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@ExcelField(title="身份证号", align=2, sort=5)
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	@ExcelField(title="录取专业", align=2, sort=6)
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}
	@ExcelField(title="投档成绩", align=2, sort=7)
	public String getZongfen() {
		return zongfen;
	}
	
	public void setZongfen(String zongfen) {
		this.zongfen = zongfen;
	}
	@ExcelField(title="美术", align=2, sort=8)
	public String getTechang() {
		return techang;
	}
	public void setTechang(String techang) {
		this.techang = techang;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}