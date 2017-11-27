package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;

public class TreeLink {
	private String name;
	private String value;
	private List<TreeLink> sub;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<TreeLink> getSub() {
		return sub;
	}
	public void setSub(List<TreeLink> sub) {
		this.sub = sub;
	}
	
}
