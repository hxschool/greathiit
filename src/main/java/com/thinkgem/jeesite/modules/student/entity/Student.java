/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 学生信息Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class Student extends DataEntity<Student> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String idCard;		// 身份证号
	private Date birthday;		// 生日
	private String gender;		// 性别
	private String nation;		// 民族
	private String political;		// 政治面貌
	private String phone;		// 联系电话
	private String address;		// 联系地址
	private String mail;		// email
	private String longGoal;		// 长期目标
	private String midGoal;		// 中期目标
	private String shortGoal;		// 短期目标
	private String selfEngIntroduce;		// 英文简介
	private String selfIntroduce;		// 中文简介
	private String edu;		// 学历
	private String nativePlace;		// 户口所在地
	private String studentLength;		// 学制
	private User student;		// 用户号
	private Office clazz;		// 班级编号
	
	private List<String> clazzNumbers;//数组查询添加
	
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

	public Office getClazz() {
		return clazz;
	}

	public void setClazz(Office clazz) {
		this.clazz = clazz;
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
	
	@NotNull(message="用户号不能为空")
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}
	
}