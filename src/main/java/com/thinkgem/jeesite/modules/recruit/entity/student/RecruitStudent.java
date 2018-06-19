/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.recruit.entity.student;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 统招数据Entity
 * @author 赵俊飞
 * @version 2018-06-04
 */
public class RecruitStudent extends DataEntity<RecruitStudent> {
	
	private static final long serialVersionUID = 1L;
	private String exaNumber;		// 考试号
	private String middleSchool;		// 毕业中学名称
	private String location;		// 生源所在地
	private String noticeNumber;		// 录取通知书编号
	private String province;		// 省份
	private String leven;		// 层次(本科/高职单招)
	private Office department;		// 院系编码

	private Office major;		// 专业编码
	private String username;		// 真实姓名
	private String gender;		// 性别
	private String birthday;		// 出生日期
	private String idCard;		// 身份证号码
	private String political;		// 政治面貌
	private String nation;		// 民族
	private String exaCategory;		// 考生类别
	private String hokouAddress;		// 户口所在地区
	private String homeAddress;		// 家庭地址
	private String zipCode;		// 家庭地址
	private String phone;		// 联系电话
	private String addressee;		// 收件人
	private String isChange;		// 是否服从定向调剂
	private String yuwen;		// 语文
	private String shuxue;		// 数学
	private String waiyu;		// 外语
	private String zonghe;		// 综合
	private String techang;		// 特长
	private String status;		// 状态 00:初始化数据导入 01:完成院校报名 11:交易处理中 20:成功 30:失败
	
	public RecruitStudent() {
		super();
	}

	public RecruitStudent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="考试号长度必须介于 1 和 64 之间")
	@ExcelField(title="考试号", align=2, sort=1)
	public String getExaNumber() {
		return exaNumber;
	}

	public void setExaNumber(String exaNumber) {
		this.exaNumber = exaNumber;
	}
	
	@Length(min=0, max=64, message="毕业中学名称长度必须介于 0 和 64 之间")
	@ExcelField(title="毕业中学", align=2, sort=2)
	public String getMiddleSchool() {
		return middleSchool;
	}

	public void setMiddleSchool(String middleSchool) {
		this.middleSchool = middleSchool;
	}
	
	@Length(min=0, max=64, message="生源所在地长度必须介于 0 和 64 之间")
	@ExcelField(title="生源所在地", align=2, sort=3)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=64, message="录取通知书编号长度必须介于 0 和 64 之间")
	@ExcelField(title="录取通知书编号", align=2, sort=4)
	public String getNoticeNumber() {
		return noticeNumber;
	}

	public void setNoticeNumber(String noticeNumber) {
		this.noticeNumber = noticeNumber;
	}
	
	@Length(min=0, max=64, message="省份长度必须介于 0 和 64 之间")
	@ExcelField(title="省份", align=2, sort=5)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=64, message="层次(本科/高职单招)长度必须介于 0 和 64 之间")
	@ExcelField(title="层次", align=2, sort=6)
	public String getLeven() {
		return leven;
	}

	public void setLeven(String leven) {
		this.leven = leven;
	}
	

	public Office getDepartment() {
		return department;
	}

	public void setDepartment(Office department) {
		this.department = department;
	}

	public Office getMajor() {
		return major;
	}

	public void setMajor(Office major) {
		this.major = major;
	}

	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	@ExcelField(title="姓名", align=2, sort=9)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=64, message="性别长度必须介于 1 和 64 之间")
	@ExcelField(title="性别", align=2, sort=10)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=14, message="出生日期长度必须介于 0 和 14 之间")
	@ExcelField(title="出生日期", align=2, sort=11)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=1, max=18, message="身份证号长度必须介于 1 和 18 之间")
	@ExcelField(title="身份证号", align=2, sort=12)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=18, message="政治面貌长度必须介于 0 和 18 之间")
	@ExcelField(title="政治面貌", align=2, sort=13)
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}
	
	@Length(min=0, max=18, message="民族长度必须介于 0 和 18 之间")
	@ExcelField(title="民族", align=2, sort=14)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=18, message="考生类别长度必须介于 0 和 18 之间")
	@ExcelField(title="考生类别", align=2, sort=15)
	public String getExaCategory() {
		return exaCategory;
	}

	public void setExaCategory(String exaCategory) {
		this.exaCategory = exaCategory;
	}
	
	@Length(min=0, max=200, message="户口所在地区长度必须介于 0 和 200 之间")
	@ExcelField(title="户口所在地区", align=2, sort=16)
	public String getHokouAddress() {
		return hokouAddress;
	}

	public void setHokouAddress(String hokouAddress) {
		this.hokouAddress = hokouAddress;
	}
	
	@Length(min=0, max=200, message="家庭地址长度必须介于 0 和 200 之间")
	@ExcelField(title="家庭地址", align=2, sort=17)
	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	@Length(min=0, max=10, message="邮政编码长度必须介于 0 和 10 之间")
	@ExcelField(title="邮政编码", align=2, sort=18)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	@ExcelField(title="联系电话", align=2, sort=19)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=20, message="收件人长度必须介于 0 和 20 之间")
	@ExcelField(title="收件人", align=2, sort=20)
	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	
	@Length(min=0, max=20, message="是否服从定向调剂长度必须介于 0 和 20 之间")
	@ExcelField(title="是否服从定向调剂", align=2, sort=21)
	public String getIsChange() {
		return isChange;
	}

	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}
	@ExcelField(title="语文", align=2, sort=22)
	public String getYuwen() {
		return yuwen;
	}

	public void setYuwen(String yuwen) {
		this.yuwen = yuwen;
	}
	@ExcelField(title="数学", align=2, sort=23)
	public String getShuxue() {
		return shuxue;
	}

	public void setShuxue(String shuxue) {
		this.shuxue = shuxue;
	}
	@ExcelField(title="外语", align=2, sort=24)
	public String getWaiyu() {
		return waiyu;
	}

	public void setWaiyu(String waiyu) {
		this.waiyu = waiyu;
	}
	@ExcelField(title="综合", align=2, sort=25)
	public String getZonghe() {
		return zonghe;
	}

	public void setZonghe(String zonghe) {
		this.zonghe = zonghe;
	}
	@ExcelField(title="美术术科", align=2, sort=26)
	public String getTechang() {
		return techang;
	}

	public void setTechang(String techang) {
		this.techang = techang;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}