/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 获奖信息Entity
 * @author 赵俊飞
 * @version 2017-12-30
 */
public class StudentItem extends DataEntity<StudentItem> {
	
	private static final long serialVersionUID = 1L;
	private String itemName;		// 项目名称
	private String itemNum;		// 项目编号
	private String itemPrizeObject;		// 项目表彰对象
	private String itemRole;		// 项目参与角色
	private String itemScore;		// 审核得分
	private String itemState;		// 状态
	private Date itemSubmitDate;		// 提交时间
	private String itemUnit;		// 主办单位
	private String note;		// 审核意见
	private String itemEvaPoint;		// 项目指标点
	private String itemEvaScore;		// 项目等级
	private String itemEvaType;		// 项目类别
	private User student;		// 学号
	
	public StudentItem() {
		super();
	}

	public StudentItem(String id){
		super(id);
	}

	@Length(min=0, max=255, message="项目名称长度必须介于 0 和 255 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=255, message="项目编号长度必须介于 0 和 255 之间")
	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	
	@Length(min=0, max=255, message="项目表彰对象长度必须介于 0 和 255 之间")
	public String getItemPrizeObject() {
		return itemPrizeObject;
	}

	public void setItemPrizeObject(String itemPrizeObject) {
		this.itemPrizeObject = itemPrizeObject;
	}
	
	@Length(min=0, max=255, message="项目参与角色长度必须介于 0 和 255 之间")
	public String getItemRole() {
		return itemRole;
	}

	public void setItemRole(String itemRole) {
		this.itemRole = itemRole;
	}
	
	@Length(min=0, max=255, message="审核得分长度必须介于 0 和 255 之间")
	public String getItemScore() {
		return itemScore;
	}

	public void setItemScore(String itemScore) {
		this.itemScore = itemScore;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getItemSubmitDate() {
		return itemSubmitDate;
	}

	public void setItemSubmitDate(Date itemSubmitDate) {
		this.itemSubmitDate = itemSubmitDate;
	}
	
	@Length(min=0, max=255, message="主办单位长度必须介于 0 和 255 之间")
	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	@Length(min=0, max=255, message="审核意见长度必须介于 0 和 255 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Length(min=0, max=64, message="项目指标点长度必须介于 0 和 64 之间")
	public String getItemEvaPoint() {
		return itemEvaPoint;
	}

	public void setItemEvaPoint(String itemEvaPoint) {
		this.itemEvaPoint = itemEvaPoint;
	}
	
	@Length(min=0, max=64, message="项目等级长度必须介于 0 和 64 之间")
	public String getItemEvaScore() {
		return itemEvaScore;
	}

	public void setItemEvaScore(String itemEvaScore) {
		this.itemEvaScore = itemEvaScore;
	}
	
	@Length(min=0, max=64, message="项目类别长度必须介于 0 和 64 之间")
	public String getItemEvaType() {
		return itemEvaType;
	}

	public void setItemEvaType(String itemEvaType) {
		this.itemEvaType = itemEvaType;
	}
	
	@NotNull(message="学号不能为空")
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}
	
}