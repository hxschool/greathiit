package com.thinkgem.jeesite.modules.api.web.adapter;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

public class BigDecimalDefault0Adapter implements JsonSerializer<BigDecimal>, JsonDeserializer<BigDecimal> {
	@Override
	public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		try {
			if (json.getAsString().equals("") || json.getAsString().equals("null")) {// 定义为double类型,如果后台返回""或者null,则返回0.00
				return BigDecimal.ZERO;
			}
		} catch (Exception ignore) {
		}
		try {
			return json.getAsBigDecimal();
		} catch (NumberFormatException e) {
			throw new JsonSyntaxException(e);
		}
	}

	@Override
	public JsonElement serialize(BigDecimal src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src);
	}
}