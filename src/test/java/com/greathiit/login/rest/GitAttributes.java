package com.greathiit.login.rest;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class GitAttributes {
	@XmlElement(name = "no")
    private String no;
	@XmlElement(name = "idCard")
    private String idCard;
	@XmlElement(name = "roleId")
    private String roleId;
	@XmlElement(name = "mobile")
    private String mobile;
	@XmlElement(name = "loginDate")
    private Date loginDate;
	@XmlElement(name = "companyId")
    private String companyId;
	@XmlElement(name = "login_name")
    private String login_name;
	@XmlElement(name = "officeId")
    private String officeId;
	@XmlElement(name = "phone")
    private String phone;
	@XmlElement(name = "loginIp")
    private String loginIp;
	@XmlElement(name = "name")
    private String name;
	@XmlElement(name = "id")
    private String id;
	@XmlElement(name = "email")
    private String email;
	@XmlElement(name = "remarks")
    private String remarks;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
}
