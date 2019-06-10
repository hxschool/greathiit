/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教师信息Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class Teacher extends DataEntity<Teacher> {
	
	private static final long serialVersionUID = 1L;
	private Date tchrAttendDate;		// 任职
	private Date tchrBirthday;		// 生日
	private String tchrDegree;		// 学位
	private String tchrEmail;		// email
	private String tchrFax;		// 邮政编码
	private String tchrName;		// 姓名
	private String tchrIdcard;		// 身份证号
	private String tchrGender;		// 性别
	private String tchrNation;		// 民族
	private String tchrPolitical;		// 政治面貌
	private String tchrMajor;		// 专业
	private String tchrGraduateSchool;		// 毕业院校
	private String tchrOfficeAddr;		// 办公地点
	private String tchrPhone;		// 联系电话
	private String tchrSchool;		// 政治面貌
	private String tchrSelfIntroduce;		// 自我介绍
	private String tchrEngSelfIntroduce;		// 英文自我介绍
	private String tchrTitle;		// 职称
	private User user;		// 教师号
	private String teacherNumber;//教师号
	private String info;
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Teacher() {
		super();
	}

	public Teacher(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTchrAttendDate() {
		return tchrAttendDate;
	}

	public void setTchrAttendDate(Date tchrAttendDate) {
		this.tchrAttendDate = tchrAttendDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTchrBirthday() {
		return tchrBirthday;
	}

	public void setTchrBirthday(Date tchrBirthday) {
		this.tchrBirthday = tchrBirthday;
	}
	
	@Length(min=0, max=255, message="学位长度必须介于 0 和 255 之间")
	public String getTchrDegree() {
		return tchrDegree;
	}

	public void setTchrDegree(String tchrDegree) {
		this.tchrDegree = tchrDegree;
	}
	
	@Length(min=0, max=255, message="email长度必须介于 0 和 255 之间")
	public String getTchrEmail() {
		return tchrEmail;
	}

	public void setTchrEmail(String tchrEmail) {
		this.tchrEmail = tchrEmail;
	}
	
	@Length(min=0, max=255, message="邮政编码长度必须介于 0 和 255 之间")
	public String getTchrFax() {
		return tchrFax;
	}

	public void setTchrFax(String tchrFax) {
		this.tchrFax = tchrFax;
	}
	
	@Length(min=0, max=255, message="姓名长度必须介于 0 和 255 之间")
	public String getTchrName() {
		return tchrName;
	}

	public void setTchrName(String tchrName) {
		this.tchrName = tchrName;
	}
	
	@Length(min=0, max=255, message="身份证号长度必须介于 0 和 255 之间")
	public String getTchrIdcard() {
		return tchrIdcard;
	}

	public void setTchrIdcard(String tchrIdcard) {
		this.tchrIdcard = tchrIdcard;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getTchrGender() {
		return tchrGender;
	}

	public void setTchrGender(String tchrGender) {
		this.tchrGender = tchrGender;
	}
	
	@Length(min=0, max=10, message="民族长度必须介于 0 和 10 之间")
	public String getTchrNation() {
		return tchrNation;
	}

	public void setTchrNation(String tchrNation) {
		this.tchrNation = tchrNation;
	}
	
	@Length(min=0, max=10, message="政治面貌长度必须介于 0 和 10 之间")
	public String getTchrPolitical() {
		return tchrPolitical;
	}

	public void setTchrPolitical(String tchrPolitical) {
		this.tchrPolitical = tchrPolitical;
	}
	
	@Length(min=0, max=255, message="专业长度必须介于 0 和 255 之间")
	public String getTchrMajor() {
		return tchrMajor;
	}

	public void setTchrMajor(String tchrMajor) {
		this.tchrMajor = tchrMajor;
	}
	
	@Length(min=0, max=255, message="毕业院校长度必须介于 0 和 255 之间")
	public String getTchrGraduateSchool() {
		return tchrGraduateSchool;
	}

	public void setTchrGraduateSchool(String tchrGraduateSchool) {
		this.tchrGraduateSchool = tchrGraduateSchool;
	}
	
	@Length(min=0, max=255, message="办公地点长度必须介于 0 和 255 之间")
	public String getTchrOfficeAddr() {
		return tchrOfficeAddr;
	}

	public void setTchrOfficeAddr(String tchrOfficeAddr) {
		this.tchrOfficeAddr = tchrOfficeAddr;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getTchrPhone() {
		return tchrPhone;
	}

	public void setTchrPhone(String tchrPhone) {
		this.tchrPhone = tchrPhone;
	}
	
	@Length(min=0, max=10, message="政治面貌长度必须介于 0 和 10 之间")
	public String getTchrSchool() {
		return tchrSchool;
	}

	public void setTchrSchool(String tchrSchool) {
		this.tchrSchool = tchrSchool;
	}
	
	public String getTchrSelfIntroduce() {
		return tchrSelfIntroduce;
	}

	public void setTchrSelfIntroduce(String tchrSelfIntroduce) {
		this.tchrSelfIntroduce = tchrSelfIntroduce;
	}
	
	public String getTchrEngSelfIntroduce() {
		return tchrEngSelfIntroduce;
	}

	public void setTchrEngSelfIntroduce(String tchrEngSelfIntroduce) {
		this.tchrEngSelfIntroduce = tchrEngSelfIntroduce;
	}
	
	@Length(min=0, max=64, message="职称长度必须介于 0 和 64 之间")
	public String getTchrTitle() {
		return tchrTitle;
	}

	public void setTchrTitle(String tchrTitle) {
		this.tchrTitle = tchrTitle;
	}
	@NotNull(message="教师号不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	@Override
	public String toString() {
		return tchrName;
	}

	
}