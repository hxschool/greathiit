package com.thinkgem.jeesite.common.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
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

	public static String upload(String url,String name,File file) {
		String result = null;
		//HttpClient client = new DefaultHttpClient();
        CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		FileBody fileBody = new FileBody(file);
		StringBody nameBody = new StringBody(name, ContentType.MULTIPART_FORM_DATA);
		// 
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("file", fileBody);
		builder.addPart("name", nameBody);
		HttpEntity entity = builder.build();
		//
		post.setEntity(entity);
		try {
			HttpResponse response = client.execute(post);
			if(response.getStatusLine().getStatusCode()==200){
				result = IOUtils.toString(response.getEntity().getContent());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
