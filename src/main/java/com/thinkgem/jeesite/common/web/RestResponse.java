package com.thinkgem.jeesite.common.web;

import java.util.List;

public class RestResponse<T> {
	private Integer code;
	private long count;
	private String msg;
	private List<T> data;
	public synchronized Integer getCode() {
		return code;
	}
	public synchronized void setCode(Integer code) {
		this.code = code;
	}
	public synchronized long getCount() {
		return count;
	}
	public synchronized void setCount(long count) {
		this.count = count;
	}
	public synchronized String getMsg() {
		return msg;
	}
	public synchronized void setMsg(String msg) {
		this.msg = msg;
	}
	public synchronized List<T> getData() {
		return data;
	}
	public synchronized void setData(List<T> data) {
		this.data = data;
	}
    public RestResponse(){
    	 
    }
    public static RestResponse ok(){
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(ResultCode.OK);
        return restResponse;
    }
    public static RestResponse succuess(){
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(ResultCode.SUCCESS);
        return restResponse;
    }
 
    public static RestResponse succuess(List data){
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(ResultCode.SUCCESS);
        restResponse.setData(data);
        return restResponse;
    }
 
    public static RestResponse fail() {
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(ResultCode.FAIL);
        return restResponse;
    }
 
 
    public static RestResponse fail(ResultCode resultCode) {
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(resultCode);
 
        return restResponse;
    }
 
    public static RestResponse fail(String msg) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(ResultCode.FAIL.code());
        restResponse.setMsg(msg);
 
        return restResponse;
    }
 
    public static RestResponse fail(Integer code, String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(code);
        restResponse.setMsg(message);
 
        return restResponse;
    }
 
    public static RestResponse fail(ResultCode resultCode, List data) {
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(resultCode);
        restResponse.setData(data);
 
        return restResponse;
    }
    
    private void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }
}
