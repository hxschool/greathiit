/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.score.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 考试成绩单Entity
 * @author 赵俊飞
 * @version 2017-12-09
 */
public class RsScoreBill extends DataEntity<RsScoreBill> implements Comparable<RsScoreBill>{
	
	private static final long serialVersionUID = 1L;
	private String ksh;		// 考生号
	private String xm;		// 姓名
	private String sfzh;		// 身份证号
	private String zf;		// 总分
	private String cj;		// 投档成绩
	private String km1;		// 科目1
	private String km2;		// 科目2
	private String km3;		// 科目3
	private String km4;		// 科目4
	private String zy1;		// 意向专业1
	private String zy2;		// 意向专业2
	private String zy3;		// 意向专业3
	private String zy4;		// 意向专业4
	private String zy5;		// 意向专业5
	private String zy6;		// 意向专业6
	private String status;		// 意向专业6
	private String zytj;		// 是否服从专业调剂
	
	private String termYear;
	
	public RsScoreBill() {
		super();
	}

	public RsScoreBill(String id){
		super(id);
	}

	@Length(min=0, max=64, message="考生号长度必须介于 0 和 64 之间")
	@ExcelField(title="考生号", align=2, sort=1)
	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	@ExcelField(title="姓名", align=2, sort=2)
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Length(min=0, max=64, message="身份证号长度必须介于 0 和 64 之间")
	@ExcelField(title="身份证号", align=2, sort=7)
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	@Length(min=0, max=64, message="总分长度必须介于 0 和 64 之间")
	@ExcelField(title="总分", align=2, sort=6)
	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}
	
	@Length(min=0, max=64, message="投档成绩长度必须介于 0 和 64 之间")
	public String getCj() {
		return cj;
	}

	public void setCj(String cj) {
		this.cj = cj;
	}
	
	@Length(min=0, max=64, message="科目1长度必须介于 0 和 64 之间")
	@ExcelField(title="语文", align=2, sort=3)
	public String getKm1() {
		return km1;
	}

	public void setKm1(String km1) {
		this.km1 = km1;
	}
	
	@Length(min=0, max=64, message="科目2长度必须介于 0 和 64 之间")
	@ExcelField(title="数学", align=2, sort=4)
	public String getKm2() {
		return km2;
	}

	public void setKm2(String km2) {
		this.km2 = km2;
	}
	
	@Length(min=0, max=64, message="科目3长度必须介于 0 和 64 之间")
	@ExcelField(title="职业技能", align=2, sort=5)
	public String getKm3() {
		return km3;
	}

	public void setKm3(String km3) {
		this.km3 = km3;
	}
	
	@Length(min=0, max=64, message="科目4长度必须介于 0 和 64 之间")
	public String getKm4() {
		return km4;
	}

	public void setKm4(String km4) {
		this.km4 = km4;
	}
	
	@Length(min=0, max=64, message="意向专业1长度必须介于 0 和 64 之间")
	@ExcelField(title="报考专业1", align=2, sort=8)
	public String getZy1() {
		return zy1;
	}

	public void setZy1(String zy1) {
		this.zy1 = zy1;
	}
	
	@Length(min=0, max=64, message="意向专业2长度必须介于 0 和 64 之间")
	@ExcelField(title="报考专业2", align=2, sort=9)
	public String getZy2() {
		return zy2;
	}

	public void setZy2(String zy2) {
		this.zy2 = zy2;
	}
	
	@Length(min=0, max=64, message="意向专业3长度必须介于 0 和 64 之间")
	@ExcelField(title="报考专业3", align=2, sort=10)
	public String getZy3() {
		return zy3;
	}

	public void setZy3(String zy3) {
		this.zy3 = zy3;
	}
	
	@Length(min=0, max=64, message="意向专业4长度必须介于 0 和 64 之间")
	@ExcelField(title="报考专业4", align=2, sort=11)
	public String getZy4() {
		return zy4;
	}

	public void setZy4(String zy4) {
		this.zy4 = zy4;
	}
	
	@Length(min=0, max=64, message="意向专业5长度必须介于 0 和 64 之间")
	@ExcelField(title="报考专业5", align=2, sort=12)
	public String getZy5() {
		return zy5;
	}

	public void setZy5(String zy5) {
		this.zy5 = zy5;
	}
	
	@Length(min=0, max=64, message="意向专业6长度必须介于 0 和 64 之间")
	public String getZy6() {
		return zy6;
	}

	public void setZy6(String zy6) {
		this.zy6 = zy6;
	}
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min=0, max=64, message="是否服从专业调剂长度必须介于 0 和 64 之间")
	@ExcelField(title="是否服从调剂", align=2, sort=13)
	public String getZytj() {
		return zytj;
	}

	public void setZytj(String zytj) {
		this.zytj = zytj;
	}
	
	
	@Override  
	public int compareTo(RsScoreBill o) {
		if(StringUtils.isEmpty(this.cj)) {
			this.cj = "0";
		}
		if(StringUtils.isEmpty(o.cj)) {
			o.cj = "0";
		}
		if(Double.valueOf(this.cj)>Double.valueOf(o.cj)) {
			return -1;
		}
		else if(Double.valueOf(this.cj)<Double.valueOf(o.cj)) {
			return 1;
		}else {
			return 0;
		}
	}

	public String getTermYear() {
		return termYear;
	}

	public void setTermYear(String termYear) {
		this.termYear = termYear;
	}


	
}