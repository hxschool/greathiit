package com.thinkgem.jeesite.common.utils;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.GreeNode;

public class GreeHelper {

	public static <T extends GreeNode> void generateTree(Map<String, T> nodeMap) throws Exception {
		// 建立父子关系
		for (GreeNode node : nodeMap.values()) {
			String parentId = node.getParentId();

			if (node.isRoot(parentId)) {
				continue;
			}
			if (nodeMap.containsKey(parentId)) {
				GreeNode parentNode = nodeMap.get(parentId);
				if (parentNode == null) {
					throw new Exception("生成树失败");
				} else {
					parentNode.addChildNode(node);
				}
			}
		}

		// sort
		for (T t : nodeMap.values()) {
			List children = t.getChildren();
			if (children != null && children.size() > 0) {
				t.sortChildren(children);
				t.setValue(children.size());
			}
		}

	}
	public static <T extends GreeNode> void generateTreeRemoveParentValue(Map<String, T> nodeMap) throws Exception {
		// 建立父子关系
		for (GreeNode node : nodeMap.values()) {
			String parentId = node.getParentId();

			if (node.isRoot(parentId)) {
				continue;
			}
			if (nodeMap.containsKey(parentId)) {
				GreeNode parentNode = nodeMap.get(parentId);
				if (parentNode == null) {
					throw new Exception("生成树失败");
				} else {
					parentNode.addChildNode(node);
				}
			}
		}

		// sort
		for (T t : nodeMap.values()) {
			List children = t.getChildren();
			if (children != null && children.size() > 0) {
				t.sortChildren(children);
			}
		}

	}
}