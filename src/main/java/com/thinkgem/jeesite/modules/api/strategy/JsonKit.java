package com.thinkgem.jeesite.modules.api.strategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class JsonKit implements ExclusionStrategy {
	String[] keys;
 
	public JsonKit(String[] keys) {
		this.keys = keys;
	}
 
	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}
 
	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		for (String key : keys) {
			if (key.equals(arg0.getName())) {
				return true;
			}
		}
		return false;
	}
 
}
