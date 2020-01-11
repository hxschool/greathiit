package com.thinkgem.jeesite.common.web;

public enum ResultCode {
    /* 成功状态码 */
	OK(200, "Request is successful"),
    SUCCESS(0, "Request is successful"),
    FAIL(1, "Request is failed"),
    TOKEN_INVALID(40001, "Token is null or invalid"),
    ACCESS_DENIED(40003, "Access denied"),
    FAIL4DELETE(50001, "Delete failed"),
    FAIL4UPDATE(50002, "Update failed");
 
    private Integer code;
    private String msg;
    ResultCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
 
    public Integer code(){
        return this.code;
    }
 
    public String msg(){
        return this.msg;
    }
}