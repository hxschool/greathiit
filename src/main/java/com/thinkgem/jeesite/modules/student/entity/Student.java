/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.student.export.StudentAction;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 学生信息Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class Student extends DataEntity<Student> implements Comparable<Student> {
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title="考生号", align=2, sort=1)
	private String exaNumber;		// 考生号
	@ExcelField(title="学号", align=2, sort=2)
	private String studentNumber;//返回json处理~
	@ExcelField(title="姓名", align=2, sort=3)
	private String name;		// 姓名
	@ExcelField(title="身份证号码", align=2, sort=4)
	private String idCard;		// 身份证号
	@ExcelField(title="性别", align=2, sort=5,dictType="sex")
	private String gender;		// 性别
	private Date birthday;		// 生日
	
	@ExcelField(title="民族", align=2, sort=6,dictType="political")
	private String nation;		// 民族
	@ExcelField(title="政治面貌", align=2, sort=7,dictType="political")
	private String political;		// 政治面貌

	private String  dormNumber;		// 寝室号
	@ExcelField(title="生源所在地", align=2, sort=18)
	private String location;		// 家庭地址
	@ExcelField(title="联系电话", align=2, sort=20)
	private String phone;		// 联系电话
	@ExcelField(title="家庭地址", align=2, sort=21)
	private String address;		// 联系地址
	private String nativePlace;		// 户口所在地
	@ExcelField(title="email", align=2, sort=22)
	private String mail;		// email
	
	@ExcelField(title="学院", align=2, sort=8)
	private String xy;		// 学院
	
	@ExcelField(title="专业", align=2, sort=9)
	private String zy;		// 专业
	@ExcelField(title="年级", align=2, sort=10)
	private String year;//年级
	//原
	private String clazzName;
	
	@ExcelField(title="班级", align=2, sort=11)
	private Office clazz;		// 班级编号
	
	@ExcelField(title="学制", align=2, sort=12,dictType="student_school_system")
	private String studentLength;		// 学制
	
	private String master;		// 班主任
	@ExcelField(title="导员", align=2, sort=13)
	private String instructor;		// 导员
	private String edu;		// 学历

	@ExcelField(title="入学日期", align=2, sort=25)
	private String startDate;		// 入学日期
	@ExcelField(title="结业日期(预计毕业日期)", align=2, sort=26)
	private String overDate;		// 结业日期(预计毕业日期)
	
	private String longGoal;		// 长期目标
	private String midGoal;		// 中期目标
	private String shortGoal;		// 短期目标
	private String selfEngIntroduce;		// 英文简介
	private String selfIntroduce;		// 中文简介
	
	private String classno;//返回json处理~
	private String  fatherName;//父亲职业
	@ExcelField(title="父亲职业", align=2, sort=14)
	private String  fatherWorks;//父亲职业
	@ExcelField(title="父亲联系方式", align=2, sort=15)
	private String  fatherPhone;//父亲职业
	private String  motherName;//母亲姓名
	@ExcelField(title="母亲职业", align=2, sort=16)
	private String  motherWorks;//母亲职业
	@ExcelField(title="母亲联系方式", align=2, sort=17)
	private String  motherPhone;//父亲职业
	@ExcelField(title="QQ", align=2, sort=23)
	private String  qq;
	@ExcelField(title="微信", align=2, sort=24)
	private String  wechat;
	private String  face;//相片

	
	private List<String> clazzNumbers;//数组查询添加
	
	private String isClass;
	
	
	private String status;
	private String description;
	//操作类型
	private StudentAction studentAction; 
	//
	public Student() {
		super();
	}

	public Student(String id){
		super(id);
	}
	

	public List<String> getClazzNumbers() {
		return clazzNumbers;
	}

	public void setClazzNumbers(List<String> clazzNumbers) {
		this.clazzNumbers = clazzNumbers;
	}

	

	@Length(min=0, max=255, message="姓名长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="身份证号长度必须介于 0 和 255 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=10, message="性别长度必须介于 0 和 10 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=10, message="民族长度必须介于 0 和 10 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=10, message="政治面貌长度必须介于 0 和 10 之间")
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=255, message="联系地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="email长度必须介于 0 和 255 之间")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Length(min=0, max=255, message="长期目标长度必须介于 0 和 255 之间")
	public String getLongGoal() {
		return longGoal;
	}

	public void setLongGoal(String longGoal) {
		this.longGoal = longGoal;
	}
	
	@Length(min=0, max=255, message="中期目标长度必须介于 0 和 255 之间")
	public String getMidGoal() {
		return midGoal;
	}

	public void setMidGoal(String midGoal) {
		this.midGoal = midGoal;
	}
	
	@Length(min=0, max=255, message="短期目标长度必须介于 0 和 255 之间")
	public String getShortGoal() {
		return shortGoal;
	}

	public void setShortGoal(String shortGoal) {
		this.shortGoal = shortGoal;
	}
	
	public String getSelfEngIntroduce() {
		return selfEngIntroduce;
	}

	public void setSelfEngIntroduce(String selfEngIntroduce) {
		this.selfEngIntroduce = selfEngIntroduce;
	}
	
	public String getSelfIntroduce() {
		return selfIntroduce;
	}

	public void setSelfIntroduce(String selfIntroduce) {
		this.selfIntroduce = selfIntroduce;
	}
	
	@Length(min=0, max=64, message="学历长度必须介于 0 和 64 之间")
	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}
	
	@Length(min=0, max=255, message="户口所在地长度必须介于 0 和 255 之间")
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Length(min=0, max=11, message="学制长度必须介于 0 和 11 之间")
	public String getStudentLength() {
		return studentLength;
	}

	public void setStudentLength(String studentLength) {
		this.studentLength = studentLength;
	}
	


	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getExaNumber() {
		return exaNumber;
	}

	public void setExaNumber(String exaNumber) {
		this.exaNumber = exaNumber;
	}

	public String getFatherWorks() {
		return fatherWorks;
	}

	public void setFatherWorks(String fatherWorks) {
		this.fatherWorks = fatherWorks;
	}

	public String getFatherPhone() {
		return fatherPhone;
	}

	public void setFatherPhone(String fatherPhone) {
		this.fatherPhone = fatherPhone;
	}

	public String getMotherWorks() {
		return motherWorks;
	}

	public void setMotherWorks(String motherWorks) {
		this.motherWorks = motherWorks;
	}

	public String getMotherPhone() {
		return motherPhone;
	}

	public void setMotherPhone(String motherPhone) {
		this.motherPhone = motherPhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getOverDate() {
		return overDate;
	}

	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}



	public String getDormNumber() {
		return dormNumber;
	}

	public void setDormNumber(String dormNumber) {
		this.dormNumber = dormNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}


	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public Office getClazz() {
		return clazz;
	}

	public void setClazz(Office clazz) {
		this.clazz = clazz;
	}



	public String getIsClass() {
		return isClass;
	}

	public void setIsClass(String isClass) {
		this.isClass = isClass;
	}

	public StudentAction getStudentAction() {
		return studentAction;
	}

	public void setStudentAction(StudentAction studentAction) {
		this.studentAction = studentAction;
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	@Override
	public int compareTo(Student student) {
		BigInteger a = new BigInteger(student.getStudentNumber());
		BigInteger b = new BigInteger(getStudentNumber());
		return a.compareTo(b);
	}
}