/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 系统秘钥Entity
 * @author 赵俊飞
 * @version 2017-12-28
 */
public class SysAppconfig extends DataEntity<SysAppconfig> {
	
	private static final long serialVersionUID = 1L;
	private String appid;		// 应用编号
	private String name;		// 应用名称
	private String privatekey;		// 私钥
	private String publickey;		// 公钥
	private String contactperson;		// 联系人
	private String email;		// email
	private String phone;		// 联系电话
	private String mobile;		// 联系手机号
	private String status;		// 状态
	
	public SysAppconfig() {
		super();
	}

	public SysAppconfig(String id){
		super(id);
	}

	@Length(min=1, max=30, message="应用编号长度必须介于 1 和 30 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=1, max=100, message="应用名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=2048, message="私钥长度必须介于 1 和 2048 之间")
	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}
	
	@Length(min=1, max=2048, message="公钥长度必须介于 1 和 2048 之间")
	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	
	@Length(min=0, max=60, message="联系人长度必须介于 0 和 60 之间")
	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	
	@Length(min=0, max=60, message="email长度必须介于 0 和 60 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=30, message="联系电话长度必须介于 0 和 30 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=20, message="联系手机号长度必须介于 0 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}