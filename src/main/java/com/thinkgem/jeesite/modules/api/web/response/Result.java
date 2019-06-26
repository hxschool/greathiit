package com.thinkgem.jeesite.modules.api.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="返回数据")
public class Result<T> {
	@ApiModelProperty(value="响应码",required=true)
	private int code;
	@ApiModelProperty(value="描述信息",required=true)
	private String msg;
	@ApiModelProperty(value="数据")
	private T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
