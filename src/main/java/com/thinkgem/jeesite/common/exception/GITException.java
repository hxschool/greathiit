package com.thinkgem.jeesite.common.exception;

public class GITException extends RuntimeException {

	private String code;
	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GITException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public GITException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
