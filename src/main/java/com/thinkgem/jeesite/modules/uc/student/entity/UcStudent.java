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
	@ExcelField(title="性别", align=2, sort=5)
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
	@ExcelField(title="学历", align=2, sort=17)
	private String edu;		// 学历
	@ExcelField(title="学制", align=2, sort=18)
	private String schoolSystem;		// 学制
	@ExcelField(title="学习形式", align=2, sort=19)
	private String learning;		// 学习形式
	@ExcelField(title="入学日期", align=2, sort=20)
	private String startDate;		// 入学日期
	@ExcelField(title="当前所在年级", align=2, sort=21)
	private String currentLevel;		// 当前所在年级
	@ExcelField(title="结业日期(预计毕业日期)", align=2, sort=22)
	private String overDate;		// 结业日期(预计毕业日期)
	@ExcelField(title="状态", align=2, sort=23)
	private String status;		// 状态
	@ExcelField(title="身份所在城市代码", align=2, sort=24)
	private String regionCode;		// 身份所在城市代码
	@ExcelField(title="身份所在城市信息", align=2, sort=25)
	private String regionName;		// 身份所在城市信息
	
	
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
	
}