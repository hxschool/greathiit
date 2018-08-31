/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户操作日志Entity
 * @author 赵俊飞
 * @version 2018-08-24
 */
public class UserOperationLog extends DataEntity<UserOperationLog> {
	
	private static final long serialVersionUID = 1L;
	private String module;		// 模块
	private String moduleId;		// 模块编号
	private String userNumber;		// 用户号
	private String userType;		// 用户类型
	private String status;		// 状态
	private String remoteAddr;		// 操作IP地址
	private String userAgent;		// 用户代理
	private String requestUri;		// 请求URI
	
	public UserOperationLog() {
		super();
	}

	public UserOperationLog(String id){
		super(id);
	}

	@Length(min=1, max=64, message="模块长度必须介于 1 和 64 之间")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	@Length(min=1, max=64, message="模块编号长度必须介于 1 和 64 之间")
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	@Length(min=1, max=64, message="用户号长度必须介于 1 和 64 之间")
	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	
	@Length(min=1, max=64, message="用户类型长度必须介于 1 和 64 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=1, max=64, message="状态长度必须介于 1 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="操作IP地址长度必须介于 0 和 255 之间")
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	@Length(min=0, max=255, message="用户代理长度必须介于 0 和 255 之间")
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	@Length(min=0, max=255, message="请求URI长度必须介于 0 和 255 之间")
	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
}