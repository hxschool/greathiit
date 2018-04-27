package com.thinkgem.jeesite.modules.api.ems;

import java.util.List;

public class KdniaoTrackResponse {
	private String EBusinessID;
	private String OrderCode;
	private String ShipperCode;
	private String LogisticCode;
	private String CallBack;
	private String Success;
	private String Reason;
	private String State;
	private List<KdniaoTrackTraces> Traces;
	public String getEBusinessID() {
		return EBusinessID;
	}
	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}
	public String getOrderCode() {
		return OrderCode;
	}
	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}
	public String getShipperCode() {
		return ShipperCode;
	}
	public void setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
	}
	public String getLogisticCode() {
		return LogisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}
	public String getCallBack() {
		return CallBack;
	}
	public void setCallBack(String callBack) {
		CallBack = callBack;
	}
	public String getSuccess() {
		return Success;
	}
	public void setSuccess(String success) {
		Success = success;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public List<KdniaoTrackTraces> getTraces() {
		return Traces;
	}
	public void setTraces(List<KdniaoTrackTraces> traces) {
		Traces = traces;
	}

	
}
