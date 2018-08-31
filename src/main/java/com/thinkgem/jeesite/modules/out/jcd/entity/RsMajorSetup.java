/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 招生计划Entity
 * @author 赵俊飞
 * @version 2018-01-09
 */
public class RsMajorSetup extends DataEntity<RsMajorSetup> {
	
	private static final long serialVersionUID = 1L;
	private String majorId;		// 专业编码
	private String majorName;		// 专业名称
	private String majorCount;		// 已招人数
	private String majorTotal;		// 计划人数
	private String status;		// 状态
	
	public RsMajorSetup() {
		super();
	}

	public RsMajorSetup(String id){
		super(id);
	}

	@Length(min=0, max=64, message="专业编码长度必须介于 0 和 64 之间")
	@ExcelField(title="序号", align=2, sort=1)
	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	
	@Length(min=0, max=64, message="专业名称长度必须介于 0 和 64 之间")
	@ExcelField(title="招生专业", align=2, sort=2)
	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	@Length(min=0, max=11, message="已招人数长度必须介于 0 和 11 之间")
	@ExcelField(title="已招人数",type=1, align=2, sort=4)
	public String getMajorCount() {
		return majorCount;
	}

	public void setMajorCount(String majorCount) {
		this.majorCount = majorCount;
	}
	
	@Length(min=0, max=11, message="计划人数长度必须介于 0 和 11 之间")
	@ExcelField(title="单招计划", align=2, sort=3)
	public String getMajorTotal() {
		return majorTotal;
	}

	public void setMajorTotal(String majorTotal) {
		this.majorTotal = majorTotal;
	}
	
	@Length(min=0, max=4, message="状态长度必须介于 0 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}