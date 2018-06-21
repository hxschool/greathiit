package com.thinkgem.jeesite.modules.recruit.entity.student;

import java.io.Serializable;

public class RecruitTotalMajorClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int majorCnt;
	private String majorId;
	private String majorName;
	private int grilCnt;
	private String grilTotal;
	private int boyCnt;
	private String boyTotal;
	private String flagShow = "0";//是否显示,0:显示,1:已分配不显示
	private String flagStatus = "0";//是否自动 1:自动 2:手动
	public int getMajorCnt() {
		return majorCnt;
	}
	public void setMajorCnt(int majorCnt) {
		this.majorCnt = majorCnt;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public int getGrilCnt() {
		return grilCnt;
	}
	public void setGrilCnt(int grilCnt) {
		this.grilCnt = grilCnt;
	}
	public String getGrilTotal() {
		return grilTotal;
	}
	public void setGrilTotal(String grilTotal) {
		this.grilTotal = grilTotal;
	}
	public int getBoyCnt() {
		return boyCnt;
	}
	public void setBoyCnt(int boyCnt) {
		this.boyCnt = boyCnt;
	}
	public String getBoyTotal() {
		return boyTotal;
	}
	public void setBoyTotal(String boyTotal) {
		this.boyTotal = boyTotal;
	}
	public String getFlagShow() {
		return flagShow;
	}
	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}
	public String getFlagStatus() {
		return flagStatus;
	}
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}
	
	
}
