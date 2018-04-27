package com.thinkgem.jeesite.modules.wechat.config;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class WechatConfig implements Serializable {

	private String wechatAppid;
	private String wechatUrl;
	private String wechatSecret;
	private String organizationNo;
	public String getWechatAppid() {
		return wechatAppid;
	}
	public void setWechatAppid(String wechatAppid) {
		this.wechatAppid = wechatAppid;
	}
	public String getWechatUrl() {
		return wechatUrl;
	}
	public void setWechatUrl(String wechatUrl) {
		this.wechatUrl = wechatUrl;
	}
	public String getWechatSecret() {
		return wechatSecret;
	}
	public void setWechatSecret(String wechatSecret) {
		this.wechatSecret = wechatSecret;
	}
	public String getOrganizationNo() {
		return organizationNo;
	}
	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
}
