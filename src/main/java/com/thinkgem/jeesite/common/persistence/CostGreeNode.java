package com.thinkgem.jeesite.common.persistence;

import java.util.Collections;
import java.util.List;

import org.springframework.util.StringUtils;


public class CostGreeNode extends GreeNode implements Comparable<CostGreeNode>{

    private int position;
    private int employeeCount;


	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}

	@Override
    public boolean isRoot(String parentId) {
        if (StringUtils.isEmpty(parentId)||parentId.equals("0")) {
            return true;
        }
        return false;
    }
 
    @Override
    public void sortChildren(List children) {
        Collections.sort(children);
    }
 
    // getter setter
 
    @Override
    public int compareTo(CostGreeNode o) {
        // 按照position升序排列
        if (this.position > o.getPosition()) {
            return 1;
        } else {
            return -1;
        }
    }
}
