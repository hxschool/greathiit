/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 报修管理Entity
 * @author 赵俊飞
 * @version 2018-03-21
 */
public class UcDormRepair extends DataEntity<UcDormRepair> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// user_id
	private String operationId;		// operation_id
	private UcDorm dorm;		// 寝室信息
	private String repairPhone;		// 联系电话
	private String repairTitle;		// 报修描述
	private String repairContent;		// 报修描述
	private String repairReplace;		// 维修完结描述
	private String repairType;		// 报修类型
	private String repairState;		// 报修状态
	
	public UcDormRepair() {
		super();
	}

	public UcDormRepair(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public UcDorm getDorm() {
		return dorm;
	}

	public void setDorm(UcDorm dorm) {
		this.dorm = dorm;
	}

	@Length(min=0, max=200, message="报修主题长度必须介于 0 和 200 之间")
	public String getRepairTitle() {
		return repairTitle;
	}

	public void setRepairTitle(String repairTitle) {
		this.repairTitle = repairTitle;
	}

	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	public String getRepairPhone() {
		return repairPhone;
	}

	public void setRepairPhone(String repairPhone) {
		this.repairPhone = repairPhone;
	}
	
	@Length(min=0, max=2000, message="报修描述长度必须介于 0 和 2000 之间")
	public String getRepairContent() {
		return repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	
	@Length(min=0, max=2000, message="维修完结描述长度必须介于 0 和 2000 之间")
	public String getRepairReplace() {
		return repairReplace;
	}

	public void setRepairReplace(String repairReplace) {
		this.repairReplace = repairReplace;
	}
	
	@Length(min=0, max=11, message="报修类型长度必须介于 0 和 11 之间")
	public String getRepairType() {
		return repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	
	@Length(min=0, max=11, message="报修状态长度必须介于 0 和 11 之间")
	public String getRepairState() {
		return repairState;
	}

	public void setRepairState(String repairState) {
		this.repairState = repairState;
	}
	
}