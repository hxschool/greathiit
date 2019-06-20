package com.thinkgem.jeesite.common.utils.excel;

public class ImportResult<E> {
	private int failureNum = 0;
	private int successNum = 0;
	private StringBuilder failureMsg;
	private E object;
	public int getFailureNum() {
		return failureNum;
	}
	public void setFailureNum(int failureNum) {
		this.failureNum = failureNum;
	}
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

	public StringBuilder getFailureMsg() {
		return failureMsg;
	}
	public void setFailureMsg(StringBuilder failureMsg) {
		this.failureMsg = failureMsg;
	}
	public E getObject() {
		return object;
	}
	public void setObject(E object) {
		this.object = object;
	}
	
}
