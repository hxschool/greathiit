/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.student.entity;

import org.hibernate.validator.constraints.Length;


import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 学籍信息Entity
 * @author 赵俊飞
 * @version 2017-09-29
 */
public class UcStudent extends DataEntity<UcStudent> {
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title="考试号", align=2, sort=1)
	private String exaNumber;		// 考试号
	@ExcelField(title="生源所在地", align=2, sort=2)
	private String location;		// 生源所在地
	@ExcelField(title="学号", align=2, sort=3)
	private String studentNumber;		// 学号
	@ExcelField(title="真实姓名", align=2, sort=4)
	private String username;		// 真实姓名
	@ExcelField(title="性别", align=2, sort=5,dictType="sex")
	private String gender;		// 性别
	@ExcelField(title="出生日期", align=2, sort=6)
	private String birthday;		// 出生日期
	@ExcelField(title="身份证号码", align=2, sort=7)
	private String idCard;		// 身份证号码
	@ExcelField(title="政治面貌", align=2, sort=8)
	private String political;		// 政治面貌
	@ExcelField(title="民族", align=2, sort=9)
	private String nation;		// 民族
	@ExcelField(title="学院标注代码", align=2, sort=10)
	private String departmentCode;		// 标注代码
	@ExcelField(title="学院代码", align=2, sort=11)
	private String departmentId;		// 学院代码
	@ExcelField(title="学院名称", align=2, sort=12)
	private String departmentName;		// 学院名称
	@ExcelField(title="专业标注代码", align=2, sort=13)
	private String majorCode;		// 标注代码
	@ExcelField(title="专业代码", align=2, sort=14)
	private String majorId;		// 专业代码
	@ExcelField(title="专业名称", align=2, sort=15)
	private String majorName;		// 专业名称
	@ExcelField(title="班号", align=2, sort=16)
	private String classNumber;		// 班号
	@ExcelField(title="学历", align=2, sort=17,dictType="student_edu")
	private String edu;		// 学历
	@ExcelField(title="学制", align=2, sort=18,dictType="student_school_system")
	private String schoolSystem;		// 学制
	@ExcelField(title="学习形式", align=2, sort=19,dictType="student_learning")
	private String learning;		// 学习形式
	@ExcelField(title="入学日期", align=2, sort=20)
	private String startDate;		// 入学日期
	@ExcelField(title="当前所在年级", align=2, sort=21)
	private String currentLevel;		// 当前所在年级
	@ExcelField(title="结业日期(预计毕业日期)", align=2, sort=22)
	private String overDate;		// 结业日期(预计毕业日期)
	@ExcelField(title="学籍状态", align=2, sort=23,dictType="student_status")
	private String status;		// 状态
	@ExcelField(title="身份所在城市代码", align=2, sort=24)
	private String regionCode;		// 身份所在城市代码
	@ExcelField(title="身份所在城市信息", align=2, sort=25)
	private String regionName;		// 身份所在城市信息
	
	private String state;   //状态
	private String exaCategory;		// 考生类别
	private String hokouAddress;		// 户口所在地区
	private String homeAddress;		// 家庭地址
	private String zipCode;		// 家庭地址
	private String addressee;		// 收件人
	private String noticeNumber;		// 录取通知书编号
	private String phone;		// 联系电话
	private String isChange;		// 是否服从定向调剂
	private String yuwen;		// 语文
	private String shuxue;		// 数学
	private String waiyu;		// 外语
	private String zonghe;		// 综合
	private String zongfen;		// 总分
	private String techang;		// 特长
	
	private String description;//修改状态描述信息
	
	public UcStudent() {
		super();
	}

	public UcStudent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="考试号长度必须介于 1 和 64 之间")
	public String getExaNumber() {
		return exaNumber;
	}

	public void setExaNumber(String exaNumber) {
		this.exaNumber = exaNumber;
	}
	
	@Length(min=0, max=64, message="生源所在地长度必须介于 0 和 64 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	@Length(min=1, max=64, message="真实姓名长度必须介于 1 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=64, message="性别长度必须介于 1 和 64 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=14, message="出生日期长度必须介于 0 和 14 之间")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=1, max=18, message="身份证号码长度必须介于 1 和 18 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=18, message="政治面貌长度必须介于 0 和 18 之间")
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}
	
	@Length(min=0, max=18, message="民族长度必须介于 0 和 18 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=1, max=64, message="标注代码长度必须介于 1 和 64 之间")
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	
	@Length(min=0, max=64, message="学院代码长度必须介于 0 和 64 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Length(min=1, max=64, message="学院名称长度必须介于 1 和 64 之间")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Length(min=1, max=64, message="标注代码长度必须介于 1 和 64 之间")
	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}
	
	@Length(min=1, max=64, message="专业代码长度必须介于 1 和 64 之间")
	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	
	@Length(min=1, max=64, message="专业名称长度必须介于 1 和 64 之间")
	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	@Length(min=1, max=64, message="班号长度必须介于 1 和 64 之间")
	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	
	@Length(min=0, max=64, message="学历长度必须介于 0 和 64 之间")
	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}
	
	@Length(min=0, max=64, message="学制长度必须介于 0 和 64 之间")
	public String getSchoolSystem() {
		return schoolSystem;
	}

	public void setSchoolSystem(String schoolSystem) {
		this.schoolSystem = schoolSystem;
	}
	
	@Length(min=0, max=64, message="学习形式长度必须介于 0 和 64 之间")
	public String getLearning() {
		return learning;
	}

	public void setLearning(String learning) {
		this.learning = learning;
	}
	
	@Length(min=0, max=64, message="入学日期长度必须介于 0 和 64 之间")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@Length(min=1, max=64, message="当前所在年级长度必须介于 1 和 64 之间")
	public String getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	@Length(min=0, max=64, message="结业日期(预计毕业日期)长度必须介于 0 和 64 之间")
	public String getOverDate() {
		return overDate;
	}

	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="身份所在城市代码长度必须介于 0 和 64 之间")
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	@Length(min=0, max=64, message="身份所在城市信息长度必须介于 0 和 64 之间")
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExaCategory() {
		return exaCategory;
	}

	public void setExaCategory(String exaCategory) {
		this.exaCategory = exaCategory;
	}

	public String getHokouAddress() {
		return hokouAddress;
	}

	public void setHokouAddress(String hokouAddress) {
		this.hokouAddress = hokouAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getNoticeNumber() {
		return noticeNumber;
	}

	public void setNoticeNumber(String noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIsChange() {
		return isChange;
	}

	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}

	public String getYuwen() {
		return yuwen;
	}

	public void setYuwen(String yuwen) {
		this.yuwen = yuwen;
	}

	public String getShuxue() {
		return shuxue;
	}

	public void setShuxue(String shuxue) {
		this.shuxue = shuxue;
	}

	public String getWaiyu() {
		return waiyu;
	}

	public void setWaiyu(String waiyu) {
		this.waiyu = waiyu;
	}

	public String getZonghe() {
		return zonghe;
	}

	public void setZonghe(String zonghe) {
		this.zonghe = zonghe;
	}

	public String getZongfen() {
		return zongfen;
	}

	public void setZongfen(String zongfen) {
		this.zongfen = zongfen;
	}

	public String getTechang() {
		return techang;
	}

	public void setTechang(String techang) {
		this.techang = techang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}