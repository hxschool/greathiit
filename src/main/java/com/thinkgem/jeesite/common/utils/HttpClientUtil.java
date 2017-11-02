package com.thinkgem.jeesite.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;  
public class HttpClientUtil {
	private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	public static String postJson(String url, String jsonString) {
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity se = new StringEntity(jsonString, "UTF-8");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
			httpPost.setEntity(se);
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new TimeoutException("连接核心异常,请求超时");
			}
			HttpEntity entity = response.getEntity();
			br = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
			String valueString = null;
			while ((valueString = br.readLine()) != null) {
				result.append(valueString);
			}
		} catch (Exception e) {
			logger.error("连接核心异常,错误信息:{}" + e.getMessage());
			throw new RuntimeException("连接核心异常");
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}
	
	public static String get(String url) {
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new TimeoutException("连接核心异常,请求超时");
			}
			HttpEntity entity = response.getEntity();
			br = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
			String valueString = null;
			while ((valueString = br.readLine()) != null) {
				result.append(valueString);
			}
		} catch (Exception e) {
			logger.error("连接核心异常,错误信息:{}" + e.getMessage());
			throw new RuntimeException("连接核心异常");
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}
	
	public static void main(String[] args) throws ParseException, IOException{
		
		String url = "http://book.greathiit.com/api/getBook?bookname=房屋租赁案件";
		
		String result = get(url);
		Gson gson = new Gson();
		List<Map<String,String>> booklist = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
		Set<String> set = new HashSet<String>();
		
		for(Map<String,String> map:booklist){
			for (Map.Entry<String, String> entry : map.entrySet()) {  
				set.add(entry.getKey()); 
			}
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("header", set);
		resultMap.put("body", booklist);
	}
}
