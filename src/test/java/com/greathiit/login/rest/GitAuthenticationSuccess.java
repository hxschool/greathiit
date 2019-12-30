package com.greathiit.login.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GitAuthenticationSuccess {
	@XmlElement(name = "user")
	private String user;
    @XmlElement(name = "attributes")
	private GitAttributes attributes;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public GitAttributes getAttributes() {
		return attributes;
	}
	public void setAttributes(GitAttributes attributes) {
		this.attributes = attributes;
	}
	
}
