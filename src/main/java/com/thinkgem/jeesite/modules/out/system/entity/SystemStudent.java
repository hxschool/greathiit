/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.system.entity;

import org.hibernate.validator.constraints.Length;


import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 单招报名申请表Entity
 * @author 赵俊飞
 * @version 2017-11-02
 */
public class SystemStudent extends DataEntity<SystemStudent> {
	
	private static final long serialVersionUID = 1L;
	private String hcFormAdd1;		// 希望就读地区
	private String hcFormAdd2;		// 就读地市
	private String hcFormKuasheng;		// 外地就读
	private String hcFormBaojiuye;		// 包就业分配
	private String hcFormYuexin;		// 期待月薪
	private String hcFormCj;		// 成绩
	private String hcFormArea;		// 地区
	private String hcFormProvince;		// 所在省份
	private String hcFormCity;		// 所在城市
	private String hcFormKl;		// 报考科类
	private String hcFormBkh;		// 报考号
	private String hcFormXm;		// 姓名
	private String hcFormAge;		// 年龄
	private String hcFormBirth;		// 出生日期
	private String hcFormXb;		// 性别
	private String hcFormSfzh;		// 身份证号
	private String hcFormBylb;		// 毕业类别
	private String hcFormXslx;		// 考试类别(学生类型)
	private String hcFormMingzu;		// 民族
	private String hcFormZhengzhimianmao;		// 政治面貌
	private String hcFormByxx;		// 毕业学校及班级
	private String hcFormHkszd;		// 户口所在地
	private String hcFormSj;		// 联系方式
	private String hcFormDz;		// 通讯地址
	private String hcFormZy1;		// 意向专业1
	private String hcFormZy2;		// 意向专业2
	private String hcFormZy3;		// 意向专业3
	private String hcFormZy4;		// 意向专业4
	private String hcFormZy5;		// 意向专业5
	private String hcFormZytj;		// 是否服从专业调剂
	private String hcFormSzzx;		// 所在中学
	private String hcFormZxdz;		// 中学地址
	private String hcFormFzrxm;		// 班主任或学校负责人姓名
	private String hcFormFzrdh;		// 负责人电话
	private String hcFormJdstarttime;		// 就读开始时间
	private String hcFormJdendtime;		// 就读结束时间
	private String hcFormYhtc;		// 有何特长
	private String hcFormJjlxrFaGx;		// 与本人关系
	private String hcFormJjlxrFaName;		// 联系人姓名
	private String hcFormJjlxrFaTel;		// 联系电话
	private String hcFormJjlxrFaWork;		// 工作单位
	private String hcFormJjlxrFaZw;		// 任何职务
	private String hcFormJjlxrMaGx;		// 与本人关系
	private String hcFormJjlxrMaName;		// 联系人姓名
	private String hcFormJjlxrMaTel;		// 联系电话
	private String hcFormJjlxrMaWork;		// 工作单位
	private String hcFormJjlxrMaZw;		// 任何职务
	private String hcFormBysj;		// 备用联系方式
	private String hcFormQq;		// qq
	private String hcFormBz;		// 备注
	private String hcFormZhuangtai;		// 状态
	
	public SystemStudent() {
		super();
	}

	public SystemStudent(String id){
		super(id);
	}

	@Length(min=0, max=64, message="希望就读地区长度必须介于 0 和 64 之间")
	public String getHcFormAdd1() {
		return hcFormAdd1;
	}

	public void setHcFormAdd1(String hcFormAdd1) {
		this.hcFormAdd1 = hcFormAdd1;
	}
	
	@Length(min=0, max=64, message="就读地市长度必须介于 0 和 64 之间")
	public String getHcFormAdd2() {
		return hcFormAdd2;
	}

	public void setHcFormAdd2(String hcFormAdd2) {
		this.hcFormAdd2 = hcFormAdd2;
	}
	
	@Length(min=0, max=64, message="外地就读长度必须介于 0 和 64 之间")
	public String getHcFormKuasheng() {
		return hcFormKuasheng;
	}

	public void setHcFormKuasheng(String hcFormKuasheng) {
		this.hcFormKuasheng = hcFormKuasheng;
	}
	
	@Length(min=0, max=64, message="包就业分配长度必须介于 0 和 64 之间")
	public String getHcFormBaojiuye() {
		return hcFormBaojiuye;
	}

	public void setHcFormBaojiuye(String hcFormBaojiuye) {
		this.hcFormBaojiuye = hcFormBaojiuye;
	}
	
	@Length(min=0, max=64, message="期待月薪长度必须介于 0 和 64 之间")
	public String getHcFormYuexin() {
		return hcFormYuexin;
	}

	public void setHcFormYuexin(String hcFormYuexin) {
		this.hcFormYuexin = hcFormYuexin;
	}
	
	@Length(min=0, max=64, message="成绩长度必须介于 0 和 64 之间")
	public String getHcFormCj() {
		return hcFormCj;
	}

	public void setHcFormCj(String hcFormCj) {
		this.hcFormCj = hcFormCj;
	}
	
	@Length(min=0, max=64, message="地区长度必须介于 0 和 64 之间")
	public String getHcFormArea() {
		return hcFormArea;
	}

	public void setHcFormArea(String hcFormArea) {
		this.hcFormArea = hcFormArea;
	}
	
	@Length(min=0, max=64, message="所在省份长度必须介于 0 和 64 之间")
	@ExcelField(title="省份", align=2, sort=6)
	public String getHcFormProvince() {
		return hcFormProvince;
	}

	public void setHcFormProvince(String hcFormProvince) {
		this.hcFormProvince = hcFormProvince;
	}
	@ExcelField(title="城市", align=2, sort=7)
	@Length(min=0, max=64, message="所在城市长度必须介于 0 和 64 之间")
	public String getHcFormCity() {
		return hcFormCity;
	}

	public void setHcFormCity(String hcFormCity) {
		this.hcFormCity = hcFormCity;
	}
	
	@Length(min=0, max=64, message="报考科类长度必须介于 0 和 64 之间")
	public String getHcFormKl() {
		return hcFormKl;
	}

	public void setHcFormKl(String hcFormKl) {
		this.hcFormKl = hcFormKl;
	}
	
	@Length(min=0, max=64, message="报考号长度必须介于 0 和 64 之间")
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
	@ExcelField(title="年龄", align=2, sort=3)
	@Length(min=0, max=64, message="年龄长度必须介于 0 和 64 之间")
	public String getHcFormAge() {
		return hcFormAge;
	}

	public void setHcFormAge(String hcFormAge) {
		this.hcFormAge = hcFormAge;
	}
	@ExcelField(title="出生日期", align=2, sort=4)
	@Length(min=0, max=64, message="出生日期长度必须介于 0 和 64 之间")
	public String getHcFormBirth() {
		return hcFormBirth;
	}

	public void setHcFormBirth(String hcFormBirth) {
		this.hcFormBirth = hcFormBirth;
	}
	@ExcelField(title="性别", align=2, sort=2)
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public String getHcFormXb() {
		return hcFormXb;
	}

	public void setHcFormXb(String hcFormXb) {
		this.hcFormXb = hcFormXb;
	}
	@ExcelField(title="身份证号", align=2, sort=5)
	@Length(min=1, max=64, message="身份证号长度必须介于 1 和 64 之间")
	public String getHcFormSfzh() {
		return hcFormSfzh;
	}

	public void setHcFormSfzh(String hcFormSfzh) {
		this.hcFormSfzh = hcFormSfzh;
	}
	@ExcelField(title="毕业类别", align=2, sort=9)
	@Length(min=1, max=64, message="毕业类别长度必须介于 1 和 64 之间")
	public String getHcFormBylb() {
		return hcFormBylb;
	}

	public void setHcFormBylb(String hcFormBylb) {
		this.hcFormBylb = hcFormBylb;
	}
	@ExcelField(title="考试类别", align=2, sort=10)
	@Length(min=0, max=64, message="考试类别(学生类型)长度必须介于 0 和 64 之间")
	public String getHcFormXslx() {
		return hcFormXslx;
	}

	public void setHcFormXslx(String hcFormXslx) {
		this.hcFormXslx = hcFormXslx;
	}
	@ExcelField(title="民族", align=2, sort=11)
	@Length(min=0, max=64, message="民族长度必须介于 0 和 64 之间")
	public String getHcFormMingzu() {
		return hcFormMingzu;
	}

	public void setHcFormMingzu(String hcFormMingzu) {
		this.hcFormMingzu = hcFormMingzu;
	}
	@ExcelField(title="政治面貌", align=2, sort=12)
	@Length(min=0, max=64, message="政治面貌长度必须介于 0 和 64 之间")
	public String getHcFormZhengzhimianmao() {
		return hcFormZhengzhimianmao;
	}

	public void setHcFormZhengzhimianmao(String hcFormZhengzhimianmao) {
		this.hcFormZhengzhimianmao = hcFormZhengzhimianmao;
	}
	@ExcelField(title="毕业学校及班级", align=2, sort=13)
	@Length(min=0, max=64, message="毕业学校及班级长度必须介于 0 和 64 之间")
	public String getHcFormByxx() {
		return hcFormByxx;
	}

	public void setHcFormByxx(String hcFormByxx) {
		this.hcFormByxx = hcFormByxx;
	}
	@ExcelField(title="户口所在地", align=2, sort=13)
	@Length(min=0, max=64, message="户口所在地长度必须介于 0 和 64 之间")
	public String getHcFormHkszd() {
		return hcFormHkszd;
	}

	public void setHcFormHkszd(String hcFormHkszd) {
		this.hcFormHkszd = hcFormHkszd;
	}
	@ExcelField(title="联系方式", align=2, sort=8)
	@Length(min=0, max=64, message="联系方式长度必须介于 0 和 64 之间")
	public String getHcFormSj() {
		return hcFormSj;
	}

	public void setHcFormSj(String hcFormSj) {
		this.hcFormSj = hcFormSj;
	}
	@ExcelField(title="通讯地址", align=2, sort=15)
	@Length(min=0, max=64, message="通讯地址长度必须介于 0 和 64 之间")
	public String getHcFormDz() {
		return hcFormDz;
	}

	public void setHcFormDz(String hcFormDz) {
		this.hcFormDz = hcFormDz;
	}
	@ExcelField(title="意向专业1", align=2, sort=16)
	@Length(min=0, max=64, message="意向专业1长度必须介于 0 和 64 之间")
	public String getHcFormZy1() {
		return hcFormZy1;
	}

	public void setHcFormZy1(String hcFormZy1) {
		this.hcFormZy1 = hcFormZy1;
	}
	@ExcelField(title="意向专业2", align=2, sort=16)
	@Length(min=0, max=64, message="意向专业2长度必须介于 0 和 64 之间")
	public String getHcFormZy2() {
		return hcFormZy2;
	}

	public void setHcFormZy2(String hcFormZy2) {
		this.hcFormZy2 = hcFormZy2;
	}
	@ExcelField(title="意向专业3", align=2, sort=16)
	@Length(min=0, max=64, message="意向专业3长度必须介于 0 和 64 之间")
	public String getHcFormZy3() {
		return hcFormZy3;
	}

	public void setHcFormZy3(String hcFormZy3) {
		this.hcFormZy3 = hcFormZy3;
	}
	@ExcelField(title="意向专业4", align=2, sort=16)
	@Length(min=0, max=64, message="意向专业4长度必须介于 0 和 64 之间")
	public String getHcFormZy4() {
		return hcFormZy4;
	}

	public void setHcFormZy4(String hcFormZy4) {
		this.hcFormZy4 = hcFormZy4;
	}
	@ExcelField(title="意向专业5", align=2, sort=16)
	@Length(min=0, max=64, message="意向专业5长度必须介于 0 和 64 之间")
	public String getHcFormZy5() {
		return hcFormZy5;
	}

	public void setHcFormZy5(String hcFormZy5) {
		this.hcFormZy5 = hcFormZy5;
	}
	@ExcelField(title="是否服从专业调剂", align=2, sort=17)
	@Length(min=0, max=64, message="是否服从专业调剂长度必须介于 0 和 64 之间")
	public String getHcFormZytj() {
		return hcFormZytj;
	}

	public void setHcFormZytj(String hcFormZytj) {
		this.hcFormZytj = hcFormZytj;
	}
	@ExcelField(title="所在高中", align=2, sort=30)
	@Length(min=0, max=64, message="所在中学长度必须介于 0 和 64 之间")
	public String getHcFormSzzx() {
		return hcFormSzzx;
	}

	public void setHcFormSzzx(String hcFormSzzx) {
		this.hcFormSzzx = hcFormSzzx;
	}
	@ExcelField(title="高中学校地址", align=2, sort=31)
	@Length(min=0, max=64, message="中学地址长度必须介于 0 和 64 之间")
	public String getHcFormZxdz() {
		return hcFormZxdz;
	}

	public void setHcFormZxdz(String hcFormZxdz) {
		this.hcFormZxdz = hcFormZxdz;
	}
	@ExcelField(title="班主任或学校负责人姓名", align=2, sort=32)
	@Length(min=0, max=64, message="班主任或学校负责人姓名长度必须介于 0 和 64 之间")
	public String getHcFormFzrxm() {
		return hcFormFzrxm;
	}

	public void setHcFormFzrxm(String hcFormFzrxm) {
		this.hcFormFzrxm = hcFormFzrxm;
	}
	@ExcelField(title="负责人电话", align=2, sort=33)
	@Length(min=1, max=64, message="负责人电话长度必须介于 1 和 64 之间")
	public String getHcFormFzrdh() {
		return hcFormFzrdh;
	}

	public void setHcFormFzrdh(String hcFormFzrdh) {
		this.hcFormFzrdh = hcFormFzrdh;
	}
	@ExcelField(title="就读开始时间", align=2, sort=34)
	@Length(min=0, max=64, message="就读开始时间长度必须介于 0 和 64 之间")
	public String getHcFormJdstarttime() {
		return hcFormJdstarttime;
	}

	public void setHcFormJdstarttime(String hcFormJdstarttime) {
		this.hcFormJdstarttime = hcFormJdstarttime;
	}
	@ExcelField(title="就读结束时间", align=2, sort=35)
	@Length(min=0, max=64, message="就读结束时间长度必须介于 0 和 64 之间")
	public String getHcFormJdendtime() {
		return hcFormJdendtime;
	}

	public void setHcFormJdendtime(String hcFormJdendtime) {
		this.hcFormJdendtime = hcFormJdendtime;
	}
	@ExcelField(title="有何特长", align=2, sort=36)
	@Length(min=0, max=64, message="有何特长长度必须介于 0 和 64 之间")
	public String getHcFormYhtc() {
		return hcFormYhtc;
	}

	public void setHcFormYhtc(String hcFormYhtc) {
		this.hcFormYhtc = hcFormYhtc;
	}
	
	@Length(min=0, max=64, message="与本人关系长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrFaGx() {
		return hcFormJjlxrFaGx;
	}

	public void setHcFormJjlxrFaGx(String hcFormJjlxrFaGx) {
		this.hcFormJjlxrFaGx = hcFormJjlxrFaGx;
	}
	@ExcelField(title="紧急联系人姓名", align=2, sort=40)
	@Length(min=0, max=64, message="联系人姓名长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrFaName() {
		return hcFormJjlxrFaName;
	}

	public void setHcFormJjlxrFaName(String hcFormJjlxrFaName) {
		this.hcFormJjlxrFaName = hcFormJjlxrFaName;
	}
	@ExcelField(title="紧急联系人电话", align=2, sort=41)
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrFaTel() {
		return hcFormJjlxrFaTel;
	}

	public void setHcFormJjlxrFaTel(String hcFormJjlxrFaTel) {
		this.hcFormJjlxrFaTel = hcFormJjlxrFaTel;
	}
	
	@Length(min=0, max=64, message="工作单位长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrFaWork() {
		return hcFormJjlxrFaWork;
	}

	public void setHcFormJjlxrFaWork(String hcFormJjlxrFaWork) {
		this.hcFormJjlxrFaWork = hcFormJjlxrFaWork;
	}
	
	@Length(min=0, max=64, message="任何职务长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrFaZw() {
		return hcFormJjlxrFaZw;
	}

	public void setHcFormJjlxrFaZw(String hcFormJjlxrFaZw) {
		this.hcFormJjlxrFaZw = hcFormJjlxrFaZw;
	}
	
	@Length(min=0, max=64, message="与本人关系长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrMaGx() {
		return hcFormJjlxrMaGx;
	}

	public void setHcFormJjlxrMaGx(String hcFormJjlxrMaGx) {
		this.hcFormJjlxrMaGx = hcFormJjlxrMaGx;
	}
	@ExcelField(title="紧急联系人姓名", align=2, sort=42)
	@Length(min=0, max=64, message="联系人姓名长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrMaName() {
		return hcFormJjlxrMaName;
	}

	public void setHcFormJjlxrMaName(String hcFormJjlxrMaName) {
		this.hcFormJjlxrMaName = hcFormJjlxrMaName;
	}
	@ExcelField(title="紧急联系人电话", align=2, sort=43)
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrMaTel() {
		return hcFormJjlxrMaTel;
	}

	public void setHcFormJjlxrMaTel(String hcFormJjlxrMaTel) {
		this.hcFormJjlxrMaTel = hcFormJjlxrMaTel;
	}
	
	@Length(min=0, max=64, message="工作单位长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrMaWork() {
		return hcFormJjlxrMaWork;
	}

	public void setHcFormJjlxrMaWork(String hcFormJjlxrMaWork) {
		this.hcFormJjlxrMaWork = hcFormJjlxrMaWork;
	}
	
	@Length(min=0, max=64, message="任何职务长度必须介于 0 和 64 之间")
	public String getHcFormJjlxrMaZw() {
		return hcFormJjlxrMaZw;
	}

	public void setHcFormJjlxrMaZw(String hcFormJjlxrMaZw) {
		this.hcFormJjlxrMaZw = hcFormJjlxrMaZw;
	}
	@ExcelField(title="备用联系方式", align=2, sort=50)
	@Length(min=0, max=64, message="备用联系方式长度必须介于 0 和 64 之间")
	public String getHcFormBysj() {
		return hcFormBysj;
	}

	public void setHcFormBysj(String hcFormBysj) {
		this.hcFormBysj = hcFormBysj;
	}
	@ExcelField(title="qq", align=2, sort=50)
	@Length(min=0, max=64, message="qq长度必须介于 0 和 64 之间")
	public String getHcFormQq() {
		return hcFormQq;
	}

	public void setHcFormQq(String hcFormQq) {
		this.hcFormQq = hcFormQq;
	}
	
	@Length(min=0, max=64, message="备注长度必须介于 0 和 64 之间")
	public String getHcFormBz() {
		return hcFormBz;
	}

	public void setHcFormBz(String hcFormBz) {
		this.hcFormBz = hcFormBz;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	public String getHcFormZhuangtai() {
		return hcFormZhuangtai;
	}

	public void setHcFormZhuangtai(String hcFormZhuangtai) {
		this.hcFormZhuangtai = hcFormZhuangtai;
	}
	
}