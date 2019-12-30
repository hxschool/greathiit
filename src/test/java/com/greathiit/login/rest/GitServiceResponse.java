package com.greathiit.login.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serviceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GitServiceResponse {
	@XmlElement(name = "authenticationFailure")
	private GitAuthenticationFailure authenticationFailure;
	@XmlElement(name = "authenticationSuccess")
	private GitAuthenticationSuccess authenticationSuccess;
	public GitAuthenticationFailure getAuthenticationFailure() {
		return authenticationFailure;
	}
	public void setAuthenticationFailure(GitAuthenticationFailure authenticationFailure) {
		this.authenticationFailure = authenticationFailure;
	}
	public GitAuthenticationSuccess getAuthenticationSuccess() {
		return authenticationSuccess;
	}
	public void setAuthenticationSuccess(GitAuthenticationSuccess authenticationSuccess) {
		this.authenticationSuccess = authenticationSuccess;
	}
	@Override
	public String toString() {
		return "GitServiceResponse [authenticationFailure=" + authenticationFailure + ", authenticationSuccess="
				+ authenticationSuccess + "]";
	}
	
	
}
