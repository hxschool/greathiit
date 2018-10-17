package com.thinkgem.jeesite.common.persistence;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.common.echart.ItemStyle;

public abstract class GreeNode {
	private String id;
	private String parentId;
	private String name;
    private Integer value;
	private List<GreeNode> children;
	private ItemStyle itemStyle;
    public abstract boolean isRoot(String parentId);
    public abstract void sortChildren(List<GreeNode> children);
    public void addChildNode(GreeNode treeNode) {
        initChildList();
        children.add(treeNode);
    }
 
    public void initChildList() {
        if (children == null)
            children = new ArrayList<>();
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<GreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<GreeNode> children) {
		this.children = children;
	}
	public ItemStyle getItemStyle() {
		return itemStyle;
	}
	public void setItemStyle(ItemStyle itemStyle) {
		this.itemStyle = itemStyle;
	}
	
}
