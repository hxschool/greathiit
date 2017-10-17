/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.rs.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单招报名申请表Entity
 * @author 赵俊飞
 * @version 2017-10-16
 */
public class RsStudent extends DataEntity<RsStudent> {
	
	private static final long serialVersionUID = 1L;
	private String hc_form_xm;		// 姓名
	private String hc_form_xb;		// 性别
	private String hc_form_sfzh;		// 身份证号
	private String hc_form_zy1;		// 意向专业1
	private String hc_form_zy2;		// 意向专业2
	private String hc_form_zy3;		// 意向专业3
	private String hc_form_szzx;		// 所在中学
	private String hc_form_zxdz;		// 中学地址
	private String hc_form_fzrxm;		// 班主任或学校负责人姓名
	private String hc_form_fzrdh;		// 负责人电话
	private String hc_form_add1;		// 希望就读地区
	private String hc_form_add2;		// 就读地市
	private String hc_form_kuasheng;		// 外地就读
	private String hc_form_baojiuye;		// 包就业分配
	private String hc_form_yuexin;		// 期待月薪
	private String hc_form_xslx;		// 学生类型
	private String hc_form_cj;		// 成绩
	private String hc_form_province;		// 所在身份
	private String hc_form_city;		// 所在城市
	private String hc_form_area;		// 所在地区
	private String hc_form_dz;		// 通讯地址
	private String hc_form_sj;		// 联系方式
	private String hc_form_bysj;		// 备用联系方式
	private String hc_form_qq;		// qq
	private String hc_form_bz;		// 备注
	private String hc_form_zhuangtai;//报考状态
	
	public RsStudent() {
		super();
	}

	public RsStudent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getHc_form_xm() {
		return hc_form_xm;
	}

	public void setHc_form_xm(String hc_form_xm) {
		this.hc_form_xm = hc_form_xm;
	}
	
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public String getHc_form_xb() {
		return hc_form_xb;
	}

	public void setHc_form_xb(String hc_form_xb) {
		this.hc_form_xb = hc_form_xb;
	}
	
	@Length(min=1, max=64, message="身份证号长度必须介于 1 和 64 之间")
	public String getHc_form_sfzh() {
		return hc_form_sfzh;
	}

	public void setHc_form_sfzh(String hc_form_sfzh) {
		this.hc_form_sfzh = hc_form_sfzh;
	}
	
	@Length(min=0, max=64, message="意向专业1长度必须介于 0 和 64 之间")
	public String getHc_form_zy1() {
		return hc_form_zy1;
	}

	public void setHc_form_zy1(String hc_form_zy1) {
		this.hc_form_zy1 = hc_form_zy1;
	}
	
	@Length(min=0, max=64, message="意向专业2长度必须介于 0 和 64 之间")
	public String getHc_form_zy2() {
		return hc_form_zy2;
	}

	public void setHc_form_zy2(String hc_form_zy2) {
		this.hc_form_zy2 = hc_form_zy2;
	}
	
	@Length(min=0, max=64, message="意向专业3长度必须介于 0 和 64 之间")
	public String getHc_form_zy3() {
		return hc_form_zy3;
	}

	public void setHc_form_zy3(String hc_form_zy3) {
		this.hc_form_zy3 = hc_form_zy3;
	}
	
	@Length(min=0, max=64, message="所在中学长度必须介于 0 和 64 之间")
	public String getHc_form_szzx() {
		return hc_form_szzx;
	}

	public void setHc_form_szzx(String hc_form_szzx) {
		this.hc_form_szzx = hc_form_szzx;
	}
	
	@Length(min=0, max=64, message="中学地址长度必须介于 0 和 64 之间")
	public String getHc_form_zxdz() {
		return hc_form_zxdz;
	}

	public void setHc_form_zxdz(String hc_form_zxdz) {
		this.hc_form_zxdz = hc_form_zxdz;
	}
	
	@Length(min=0, max=64, message="班主任或学校负责人姓名长度必须介于 0 和 64 之间")
	public String getHc_form_fzrxm() {
		return hc_form_fzrxm;
	}

	public void setHc_form_fzrxm(String hc_form_fzrxm) {
		this.hc_form_fzrxm = hc_form_fzrxm;
	}
	
	@Length(min=1, max=64, message="负责人电话长度必须介于 1 和 64 之间")
	public String getHc_form_fzrdh() {
		return hc_form_fzrdh;
	}

	public void setHc_form_fzrdh(String hc_form_fzrdh) {
		this.hc_form_fzrdh = hc_form_fzrdh;
	}
	
	@Length(min=0, max=64, message="希望就读地区长度必须介于 0 和 64 之间")
	public String getHc_form_add1() {
		return hc_form_add1;
	}

	public void setHc_form_add1(String hc_form_add1) {
		this.hc_form_add1 = hc_form_add1;
	}
	
	@Length(min=0, max=64, message="就读地市长度必须介于 0 和 64 之间")
	public String getHc_form_add2() {
		return hc_form_add2;
	}

	public void setHc_form_add2(String hc_form_add2) {
		this.hc_form_add2 = hc_form_add2;
	}
	
	@Length(min=0, max=64, message="外地就读长度必须介于 0 和 64 之间")
	public String getHc_form_kuasheng() {
		return hc_form_kuasheng;
	}

	public void setHc_form_kuasheng(String hc_form_kuasheng) {
		this.hc_form_kuasheng = hc_form_kuasheng;
	}
	
	@Length(min=0, max=64, message="包就业分配长度必须介于 0 和 64 之间")
	public String getHc_form_baojiuye() {
		return hc_form_baojiuye;
	}

	public void setHc_form_baojiuye(String hc_form_baojiuye) {
		this.hc_form_baojiuye = hc_form_baojiuye;
	}
	
	@Length(min=0, max=64, message="期待月薪长度必须介于 0 和 64 之间")
	public String getHc_form_yuexin() {
		return hc_form_yuexin;
	}

	public void setHc_form_yuexin(String hc_form_yuexin) {
		this.hc_form_yuexin = hc_form_yuexin;
	}
	
	@Length(min=0, max=64, message="学生类型长度必须介于 0 和 64 之间")
	public String getHc_form_xslx() {
		return hc_form_xslx;
	}

	public void setHc_form_xslx(String hc_form_xslx) {
		this.hc_form_xslx = hc_form_xslx;
	}
	
	@Length(min=0, max=64, message="成绩长度必须介于 0 和 64 之间")
	public String getHc_form_cj() {
		return hc_form_cj;
	}

	public void setHc_form_cj(String hc_form_cj) {
		this.hc_form_cj = hc_form_cj;
	}
	
	@Length(min=0, max=64, message="所在身份长度必须介于 0 和 64 之间")
	public String getHc_form_province() {
		return hc_form_province;
	}

	public void setHc_form_province(String hc_form_province) {
		this.hc_form_province = hc_form_province;
	}
	
	@Length(min=0, max=64, message="所在城市长度必须介于 0 和 64 之间")
	public String getHc_form_city() {
		return hc_form_city;
	}

	public void setHc_form_city(String hc_form_city) {
		this.hc_form_city = hc_form_city;
	}
	
	@Length(min=0, max=64, message="所在地区长度必须介于 0 和 64 之间")
	public String getHc_form_area() {
		return hc_form_area;
	}

	public void setHc_form_area(String hc_form_area) {
		this.hc_form_area = hc_form_area;
	}
	
	@Length(min=0, max=64, message="通讯地址长度必须介于 0 和 64 之间")
	public String getHc_form_dz() {
		return hc_form_dz;
	}

	public void setHc_form_dz(String hc_form_dz) {
		this.hc_form_dz = hc_form_dz;
	}
	
	@Length(min=0, max=64, message="联系方式长度必须介于 0 和 64 之间")
	public String getHc_form_sj() {
		return hc_form_sj;
	}

	public void setHc_form_sj(String hc_form_sj) {
		this.hc_form_sj = hc_form_sj;
	}
	
	@Length(min=0, max=64, message="备用联系方式长度必须介于 0 和 64 之间")
	public String getHc_form_bysj() {
		return hc_form_bysj;
	}

	public void setHc_form_bysj(String hc_form_bysj) {
		this.hc_form_bysj = hc_form_bysj;
	}
	
	@Length(min=0, max=64, message="qq长度必须介于 0 和 64 之间")
	public String getHc_form_qq() {
		return hc_form_qq;
	}

	public void setHc_form_qq(String hc_form_qq) {
		this.hc_form_qq = hc_form_qq;
	}
	
	@Length(min=0, max=64, message="备注长度必须介于 0 和 64 之间")
	public String getHc_form_bz() {
		return hc_form_bz;
	}

	public void setHc_form_bz(String hc_form_bz) {
		this.hc_form_bz = hc_form_bz;
	}

	public String getHc_form_zhuangtai() {
		return hc_form_zhuangtai;
	}
	
	public void setHc_form_zhuangtai(String hc_form_zhuangtai) {
		this.hc_form_zhuangtai = hc_form_zhuangtai;
	}
	
	
	
}