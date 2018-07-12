/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 楼宇Entity
 * @author 赵俊飞
 * @version 2017-12-15
 */
public class SchoolRoot extends DataEntity<SchoolRoot> {
	
	private static final long serialVersionUID = 1L;
	private String value;		// 数据值
	private String label;		// 标签名
	private String type;		// 类型
	private String code;
	private int total;
	private String description;		// description
	private String sort;		// 排序（升序）
	private SchoolRoot parent;		// 父级编号
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public SchoolRoot() {
		super();
	}

	public SchoolRoot(String id){
		super(id);
	}

	@Length(min=1, max=100, message="数据值长度必须介于 1 和 100 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=1, max=100, message="标签名长度必须介于 1 和 100 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=100, message="类型长度必须介于 1 和 100 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@JsonBackReference
	public SchoolRoot getParent() {
		return parent;
	}

	public void setParent(SchoolRoot parent) {
		this.parent = parent;
	}
	
}