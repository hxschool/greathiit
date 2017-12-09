package com.thinkgem.jeesite.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RandomListUtils {
	public static <T> List<T> createRandomList(List<T> list, int n) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<T> listNew = new ArrayList<T>();
		if (list.size() <= n) {
			return list;
		} else {
			while (map.size() < n) {
				int random = (int) (Math.random() * list.size());
				if (!map.containsKey(random)) {
					map.put(random, "");
					listNew.add(list.get(random));
				}
			}
			return listNew;
		}
	}
}
